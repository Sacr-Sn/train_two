package com.sn.demo02.exception;

import com.sn.demo02.domain.vo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 捕捉所有异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        e.printStackTrace();
        return Result.err(500, StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败！");
    }
}
