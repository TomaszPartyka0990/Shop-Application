package pl.partyka.shopapplication.exceptions;

public class CategoryDoesNotExistsException extends RuntimeException {
    public CategoryDoesNotExistsException(String message) {
        super(message);
    }
}
