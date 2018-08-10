package com.appspring.handle;

import com.appspring.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ResultUtil;

@ControllerAdvice
public class ErrorHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        return ResultUtil.error(e);
    }

}
