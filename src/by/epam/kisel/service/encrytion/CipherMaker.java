package by.epam.kisel.service.encrytion;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import by.epam.kisel.exception.ServiceException;

public class CipherMaker {

	private CipherMaker() {

	}

	public static Cipher makeCipher() throws ServiceException {

		Cipher cipher;

		try {
			cipher = Cipher.getInstance(EncryptionConstants.ENCRYPTION_TYPE);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new ServiceException(e.getMessage());
		}
		return cipher;
	}
}
