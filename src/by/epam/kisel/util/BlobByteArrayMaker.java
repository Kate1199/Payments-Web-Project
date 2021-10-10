package by.epam.kisel.util;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobByteArrayMaker {
	
	private static final int BLOB_BEGINING = 1;
	
	public static byte[] makeByteArray(ResultSet resultSet, int coloumn) throws SQLException {
		Blob blob = resultSet.getBlob(coloumn);
		int length = (int) blob.length();  
		byte[] blobAsBytes = blob.getBytes(BLOB_BEGINING, length);

		return blobAsBytes;
	}
}
