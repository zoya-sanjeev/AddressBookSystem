package main.com.bridgelabz.addressbooksystem;

public class AddressBookException extends RuntimeException {
	enum ExceptionType{
		DB_CONNECTION_FAILED,
		INCORRECT_QUERY, 
		UPDATE_FAIL,
		INSERT_FAIL
	}
	ExceptionType type;
	public AddressBookException(ExceptionType exceptionType, String message) {
        super(message);
        this.type = exceptionType;
    }
}
