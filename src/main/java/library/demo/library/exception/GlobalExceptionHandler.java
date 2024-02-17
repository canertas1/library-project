package library.demo.library.exception;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MalformedJwtException.class)
    ResponseEntity<String> malformedJwtException(MalformedJwtException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> illegalArgumentException(IllegalArgumentException exception){

        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }



}
