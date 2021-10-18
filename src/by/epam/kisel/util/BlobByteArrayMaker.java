package by.epam.kisel.util;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.kisel.util.validation.Validator;

public class BlobByteArrayMaker {
	
	private static final int BLOB_BEGINING = 1;
	
	public static byte[] makeByteArray(ResultSet resultSet, String coloumn) throws SQLException {
		Blob blob = resultSet.getBlob(coloumn);
		if(Validator.isNull(blob)) {
			return new byte[MinValues.MIN_ARRAY_SIZE];
		}
		int length = (int) blob.length();  
		byte[] blobAsBytes = blob.getBytes(BLOB_BEGINING, length);

		return blobAsBytes;
	}
}
