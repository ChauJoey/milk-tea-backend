package org.JoeyZ.advice;

import org.JoeyZ.ApiResponse;
import org.JoeyZ.ErrorCode;
import org.JoeyZ.MtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MtException.class)
    public ApiResponse<String> handleMtException(MtException e) {
        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> handleIllegalArg(Exception e) {
        return ApiResponse.error(ErrorCode.PARAMETER_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleOther(Exception e) {
        return ApiResponse.error(ErrorCode.SERVER_ERROR, "Internal server error: " + e.getMessage());
    }
}
