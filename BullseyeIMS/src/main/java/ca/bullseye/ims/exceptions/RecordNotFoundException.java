package ca.bullseye.ims.exceptions;

public class RecordNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String message) {
		super("Record Not Found: id= " + message);
	}
}
