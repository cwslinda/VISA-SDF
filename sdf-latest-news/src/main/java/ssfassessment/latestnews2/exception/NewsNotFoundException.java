package ssfassessment.latestnews2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends NewsAPIException {

    public NewsNotFoundException(String id) {
        super(404, "Cannot find news article " + id);
    }
    
}


