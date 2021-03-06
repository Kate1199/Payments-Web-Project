package by.epam.payment.service.hashing;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import by.epam.payment.exception.ServiceException;
import by.epam.payment.util.MinValue;
import by.epam.payment.util.validation.Validator;

public class Hasher {
	
	private static final int SALT_INDEX = 16;
	private static final int NUMBER_OF_ITERATIONS = 1000;
	private static final int HASH_BYTES = 128;
	private static final String HASH_ALGIRYTHM = "PBKDF2WithHmacSHA1";
	
	private Hasher() {
		
	}
	
	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_INDEX];
		random.nextBytes(salt);
		return salt;
	}
	
	public static byte[] hashData(char[] dataToHash, byte[] salt) throws ServiceException {
		if(Validator.isNull(dataToHash) || Validator.isEmptyCharArray(dataToHash)) {
			return new byte[MinValue.MIN_ARRAY_SIZE];
		}
		KeySpec spec = new PBEKeySpec(dataToHash, salt, NUMBER_OF_ITERATIONS, HASH_BYTES);
		byte[] hashedData = null;
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGIRYTHM);
			hashedData = factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			throw new ServiceException(e.getMessage());
		}
		
		if(Validator.isNull(hashedData)) {
			hashedData = new byte[MinValue.MIN_ARRAY_SIZE];
		}
		return hashedData;
	}

	public static boolean compareEncryptedData(byte[] hashedData, char[] enteredData, byte[] salt) throws ServiceException {
		boolean compare;
		if(Validator.isNull(hashedData) || Validator.isNull(enteredData) || Validator.isNull(salt)
				|| Validator.isEmptyByteArray(hashedData) || Validator.isEmptyCharArray(enteredData)
				|| Validator.isEmptyByteArray(salt)) {
			compare = false;
		} else {
			byte[] hashedEnteredData = hashData(enteredData, salt);
			compare = Arrays.equals(hashedData, hashedEnteredData);
		}
		return compare;
	}
}
