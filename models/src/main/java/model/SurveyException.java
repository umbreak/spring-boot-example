package model;

public class SurveyException extends RuntimeException {
    private final ErrorResponse.Error error;

    public SurveyException(String message, ErrorResponse.Error error) {
        super(message);
        this.error = error;
    }

    public ErrorResponse.Error getError() {
        return error;
    }
}
