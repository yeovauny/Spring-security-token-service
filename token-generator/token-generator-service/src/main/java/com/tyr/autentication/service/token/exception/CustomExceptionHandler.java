package com.tyr.autentication.service.token.exception;


import com.tyr.autentication.service.token.exception.error.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<Object> handleEventTokenExpiredException(Exception e, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error= new ErrorResponse("Token expired ", details);
        return new ResponseEntity<>(error , HttpStatus.valueOf(401));
    }


    @ExceptionHandler(SignatureException.class)
    public final ResponseEntity<Object> handleEventTokenSignatureException(Exception e, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error= new ErrorResponse("Token Signature Exception ", details);
        return new ResponseEntity<>(error , HttpStatus.valueOf(401));
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleEventException(Exception e, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error= new ErrorResponse("There is a Error ", details);
        return new ResponseEntity<>(error , HttpStatus.valueOf(500));
    }

}
