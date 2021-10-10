package by.epam.kisel.service.encrytion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.validation.Validator;

public class KeyStoreLoader {
	
	private static final String STORING_FORMAT_OF_KEY_STORE = "JCEKS";
	private static final String KEY_STORE_PASSWORD = "changeit";
	private static final String KEY_STORE_FILENAME = "C:\\Program Files\\Java\\jdk-11.0.12\\lib\\security\\cacerts";
	private static final char[] KEY_PASSWORD = "oOfWPXwIbkGjbda".toCharArray();

	private static KeyStore keyStore;
	
	private KeyStoreLoader() {
		
	}
	public static boolean loadKeyStore() throws ServiceException {
		boolean isLoaded = true;
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] password = KEY_STORE_PASSWORD.toCharArray();
			try(InputStream keyStoreData = KeyStoreLoader.class.getResourceAsStream(KEY_STORE_FILENAME)){
			    keyStore.load(keyStoreData, password);
			} catch (FileNotFoundException | NoSuchAlgorithmException | CertificateException e) {
				isLoaded = false;
				throw new ServiceException(e.getMessage());
			} 
		} catch (KeyStoreException e) {
			isLoaded = false;
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			isLoaded = false;
			throw new ServiceException(e.getMessage());
		}
			
		return isLoaded;
	}
	
	public static boolean storeKey(SecretKey secretKey, String alias) throws ServiceException {
		if(Validator.isNull(secretKey) || Validator.isNull(alias)) {
			return false;
		}
		
		boolean isStored = true;
		KeyStore.ProtectionParameter entryData = new KeyStore.PasswordProtection(KEY_PASSWORD);
		KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
		try {
			keyStore.setEntry(alias, secretKeyEntry, entryData);
			
		} catch (KeyStoreException e) {
			isStored = false;
			throw new ServiceException(e.getMessage());
		}
		isStored &= storeKeyStore();
		return isStored;
	}
	
	private static boolean storeKeyStore() throws ServiceException {
		
		boolean isStored = false;
		try (FileOutputStream keyStoreOutputStream = new FileOutputStream(KEY_STORE_FILENAME)) {
		    keyStore.store(keyStoreOutputStream, KEY_STORE_PASSWORD.toCharArray());
		    isStored = true;
		} catch (FileNotFoundException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		} catch (KeyStoreException e) {
			throw new ServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e.getMessage());
		} catch (CertificateException e) {
			throw new ServiceException(e.getMessage());
		} 
		return isStored;
	}
	
	public static SecretKeySpec takeKey(String alias) throws ServiceException {
		SecretKeySpec secretKey = null;
		try {
			secretKey = (SecretKeySpec) keyStore.getKey(alias, KEY_PASSWORD);
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return secretKey;
	}
	
}
