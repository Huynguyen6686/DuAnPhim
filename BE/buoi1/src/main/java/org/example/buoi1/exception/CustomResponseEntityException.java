package org.example.buoi1.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {
    //quản lý chung
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomErrorDetails> handleAllhandleAllException(Exception ex, WebRequest request) {
        CustomErrorDetails errorDetails = new CustomErrorDetails(LocalDateTime.now()
                , ex.getMessage()
                , request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //quản lý riêng, hiển thị lỗi chi tiết
    @ExceptionHandler(CustomResourceNotFoundException.class)
    public final ResponseEntity<CustomErrorDetails> handleResponseNotFoundException(Exception ex, WebRequest request) {
        CustomErrorDetails errorDetails = new CustomErrorDetails(LocalDateTime.now()
                , ex.getMessage()
                , request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    //khi mà k vali đc thì chuyền vào, các tham số lỗi thì sẽ hiển thị

        @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode statusCode,
                                                               WebRequest request) {

        String messages = ex.getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        CustomErrorDetails errorDetails = new CustomErrorDetails(LocalDateTime.now(), messages, request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
