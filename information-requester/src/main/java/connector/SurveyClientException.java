package connector;

import model.ErrorResponse;
import model.SurveyException;
import org.springframework.http.HttpStatus;


public class SurveyClientException extends SurveyException{
    private final HttpStatus status;

    public SurveyClientException(String message, ErrorResponse.Error error, HttpStatus status) {
        super(message, error);
        this.status = status;
    }
}
