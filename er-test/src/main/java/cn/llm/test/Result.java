package cn.llm.test;

public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public Result(){
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> Result<T> succeed(){
        return succeed(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),null);
    }
    public static <T> Result<T> succeed(T data){
        return succeed(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),data);
    }
    public static <T> Result<T> succeed(String msg,T data){
        return succeed(ResultCode.SUCCESS.getCode(),msg,data);
    }
    public static <T> Result<T> succeed(String code,String msg,T data){
        return new Result<>(code,msg,data);
    }
    public static <T> Result<T> failed(){
        return failed(ResultCode.INTERNAL_SERVER_ERROR.getCode(),ResultCode.INTERNAL_SERVER_ERROR.getMsg(),null);
    }
    public static <T> Result<T> failed(String msg){
        return failed(ResultCode.INTERNAL_SERVER_ERROR.getCode(),msg,null);
    }
    public static <T> Result<T> failed(String msg,T data){
        return failed(ResultCode.INTERNAL_SERVER_ERROR.getCode(),msg,data);
    }
    public static <T> Result<T> failed(String code,String msg,T data){
        return new Result<>(code,msg,data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
