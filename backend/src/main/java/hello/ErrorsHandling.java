package hello;

import model.ErrorResponse;
import model.SurveyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorsHandling {

    @ResponseBody
    @ExceptionHandler(SurveyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse genericSurveyExceptionHandler(SurveyException ex) {
        return new ErrorResponse(ex.getError(),ex.getMessage());
    }

}

