package by.epam.kisel.service.encrytion;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.validation.Validator;

public class DataDecrypter {
	
	private SecretKeySpec skeySpec;
	
	public DataDecrypter(String alias) throws ServiceException {
		KeyStoreLoader.loadKeyStore();
		skeySpec = KeyStoreLoader.takeKey(alias);
	}

	
	public String decrypt(byte[] encryptedByteArray) throws ServiceException {
		if(Validator.isNull(encryptedByteArray)) {
			return MinValues.EMPTY_STRING;
		}
		
		Cipher cipher = CipherMaker.makeCipher();
		
		byte[] decryptedByteArray;
		try {
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			decryptedByteArray = cipher.doFinal(encryptedByteArray);
		} catch (InvalidKeyException e) {
			throw new ServiceException(e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new ServiceException(e.getMessage());
		} 
		String decrypted = new String(decryptedByteArray);
		return decrypted;
	}
}
