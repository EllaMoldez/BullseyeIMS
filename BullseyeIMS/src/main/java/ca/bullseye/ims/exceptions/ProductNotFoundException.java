package ca.bullseye.ims.exceptions;

public class ProductNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String message) {
		super("Product Not Found: id=" + message);
	}
}
