package co.com.bancolombia.jpa.exception;

public class DataSourceCreationException extends RuntimeException {
    public DataSourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}