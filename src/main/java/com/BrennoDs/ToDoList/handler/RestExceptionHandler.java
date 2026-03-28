package com.BrennoDs.ToDoList.handler;

import com.BrennoDs.ToDoList.controller.TodoController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.BrennoDs.ToDoList.exception.BadRequestException;
import com.BrennoDs.ToDoList.exception.BadRequestExceptionDetails;
import com.BrennoDs.ToDoList.exception.ExceptionDetails;
import com.BrennoDs.ToDoList.exception.ValidationExceptionDetails;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre){
        return new ResponseEntity<>(
            BadRequestExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(bre.getMessage())
                .title("Bad Request Exception")
                .developerMessage(bre.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        return new ResponseEntity<>(
            ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(manve.getMessage())
                .title("Bad Request Exception")
                .developerMessage(manve.getClass().getName())
                .field(fields)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override    
    protected ResponseEntity<Object>    handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        
            ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .status(statusCode.value())
                    .details(ex.getMessage())
                    .title(ex.getCause().getMessage())
                    .developerMessage(ex.getClass().getName())
                    .build();
            return new ResponseEntity<>(exceptionDetails, headers, statusCode);
    }
}
