package com.project.tour.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 에러 발생시, 객체형태로 리턴
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandler
{
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex)
    {
        log.error(String.format("%s : %s", "ExceptionHandler Message", ex.getMessage()));
        log.error("Detail log ...", ex);

        return new ErrorResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "INTERNAL_SERVER_ERROR");
    }
}
