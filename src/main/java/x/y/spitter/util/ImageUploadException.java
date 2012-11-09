package x.y.spitter.util;

import java.io.IOException;

public class ImageUploadException extends Exception {

	private static final long serialVersionUID = 1L;

	public ImageUploadException(String message) {
		super(message);
	}

	public ImageUploadException(String message, IOException cause) {
		super(message, cause);
	}

}
