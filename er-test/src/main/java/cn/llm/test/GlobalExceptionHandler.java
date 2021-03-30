package cn.llm.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.logging.Logger;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String FEIGN_ERROR="000S1";
    private static final String DATABASE_ERROR="000D1";

    public GlobalExceptionHandler(){}

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error("参数校验错误：{}",ex.getMessage(),ex);
        ObjectError objectError=(ObjectError) ex.getBindingResult().getAllErrors().get(0);
        return Result.failed(ResultCode.PARAM_VALID_FAIL.getCode(),objectError.getDefaultMessage(),objectError.getDefaultMessage());
    }

    @ExceptionHandler({ApiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleApiException(ApiException ex){
        log.error("业务错误信息：{}",ex.getMsg(),ex);
        return Result.failed(ex.getCode(),ex.getMsg(),(Object) null);
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleSQLException(SQLException ex){
        log.error("数据库操作异常，请稍后再试",ex);
        return Result.failed("000D1","数据库操作异常",(Object) null);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleFeignTraceException(Exception ex){
        if (ex instanceof BaseException){
            BaseException baseException=(BaseException) ex;
            if (baseException.getCode()==null){
                return Result.failed(baseException.getMsg());
            }
            return Result.failed(baseException.getCode(),baseException.getMsg());
        }else{
            return Result.failed(ex.getMessage());
        }
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleAllException(Exception ex){
        if (ex instanceof BaseException){
            BaseException bex=(BaseException) ex;
            log.error("业务错误信息:{}",bex.getMsg(),bex);
            return Result.failed(bex.getCode(),bex.getMsg(),(Object) null);
        }else {
            log.error("系统异常，请稍后再试",ex);
            return Result.failed(ex.getMessage());
        }
    }
}
