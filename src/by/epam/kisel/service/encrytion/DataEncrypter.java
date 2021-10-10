package by.epam.kisel.service.encrytion;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.validation.Validator;

public class DataEncrypter {
	
	private static final String ENCRYPT_ALGORITHM = "AES";
	private static final String ENCODING = "UTF-8";
	
	public static final int CREATE_NEW_KEY = 1;
	
	private SecretKeySpec skeySpec;
	
	private DataEncrypter() {
	}
	
	public DataEncrypter(String alias) throws ServiceException {
//		KeyStoreLoader.loadKeyStore();
		skeySpec = KeyStoreLoader.takeKey(alias);
	}
	
	public DataEncrypter(String alias, int createKeyOrNot) throws ServiceException {
		genrateKey();
//		KeyStoreLoader.loadKeyStore();
//		KeyStoreLoader.storeKey(skeySpec, alias);
	}
	
	private void genrateKey() throws ServiceException {
		KeyGenerator kgen;
		try {
			kgen = KeyGenerator.getInstance(ENCRYPT_ALGORITHM);
			
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e.getMessage());
		}
		
		kgen.init(128);
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		skeySpec = new SecretKeySpec(raw, ENCRYPT_ALGORITHM);
	}
	
	public byte[] encrypt(String strToEncrypt) throws ServiceException {
		if(Validator.isNull(strToEncrypt)) {
			return new byte[MinValues.MIN_ARRAY_SIZE];
		}
		
		return encrypt(strToEncrypt, skeySpec);
	}
	
	public byte[] encryptWithKey(String strToEncrypt) throws ServiceException {
		
		if(Validator.isNull(strToEncrypt)) {
			return new byte[MinValues.MIN_ARRAY_SIZE];
		}
			
		return encrypt(strToEncrypt, skeySpec);
	}
	
	//TODO: change name or write javadoc
	private byte[] encrypt(String strToEncrypt, SecretKey secretKey) throws ServiceException {
		
		Cipher cipher = CipherMaker.makeCipher();
	
		byte[] encrypted;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encoded = strToEncrypt.getBytes(ENCODING);
			encrypted = cipher.doFinal(encoded);
		} catch (InvalidKeyException e) {
			throw new ServiceException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new ServiceException(e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new ServiceException(e.getMessage());
		} 
		
		return encrypted;
	}
}
	

