package ssfassessment.latestnews2.exception;

public class NewsAPIException extends RuntimeException {
    private final int code;
    private final String message;

    NewsAPIException(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
