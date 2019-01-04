package com.project.tour.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 에러 발생시, 객체형태로 리턴
 */
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException
{
    // TODO 필요할까?
    @Deprecated
    public InternalServerException(Exception e)
    {
        StringBuilder sb = new StringBuilder(8192);
        for (StackTraceElement stackTraceElement : e.getStackTrace())
        {
            sb.append(stackTraceElement);
            sb.append("\n\r");
        }
        log.error(sb.toString());
    }

    public InternalServerException()
    {
    }
}
