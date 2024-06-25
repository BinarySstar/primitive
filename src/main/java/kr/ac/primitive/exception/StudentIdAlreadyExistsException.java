package kr.ac.primitive.exception;

public class StudentIdAlreadyExistsException extends RuntimeException{
    public StudentIdAlreadyExistsException(String message) {
        super(message);
    }
}
