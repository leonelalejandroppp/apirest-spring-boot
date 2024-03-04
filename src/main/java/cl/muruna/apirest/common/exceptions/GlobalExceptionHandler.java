package cl.muruna.apirest.common.exceptions;

import cl.muruna.apirest.common.classes.CommonResponse;
import cl.muruna.apirest.users.exceptions.EmailExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleExceptionMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        System.out.println(ex.getCause());
        System.out.println(ex.getMessage());
        FieldError error = (FieldError) ex.getBindingResult().getAllErrors().get(0);
        CommonResponse commonResponse = new CommonResponse(error.getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleExceptionEmailExistsException (EmailExistsException ex) {
        CommonResponse commonResponse = new CommonResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<CommonResponse> handleAccessDeniedException(ForbiddenException ex) {
        CommonResponse commonResponse = new CommonResponse("Token inválido");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(commonResponse);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<CommonResponse> handleJWTExpired (ExpiredJwtException ex) {
        CommonResponse commonResponse = new CommonResponse("Token inválido");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(commonResponse);
    }
}
