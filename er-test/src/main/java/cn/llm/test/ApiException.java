package cn.llm.test;

public class ApiException extends BaseException{
    private static final long serialVersionUID = -7935576167341116787L;

    public ApiException(ResultCode resultCode){
        this(resultCode.getCode(),resultCode.getMsg());
    }
    public ApiException(ResultCode resultCode,Throwable cause) {
        super(resultCode.getCode(), resultCode.getMsg(),cause);
    }
    public ApiException(ResultCode resultCode,Object... arguments){
        this(resultCode.getCode(),resultCode.getMsg(),arguments);
    }
    public ApiException(String code,String message,Object... arguments){
        super(code,message,arguments);
    }
    public ApiException(String code,String message,Throwable cause){
        super(code,message,cause);
    }
}
