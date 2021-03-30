package cn.llm.test;

public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 5008550301535541620L;

    private String code;
    private String msg;

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public BaseException(String code,String format,Object... arguments){
        super(StringUtils.formatTemplate(format,arguments));
    }
    public String getCode(){return code;}
    public String getMsg(){return getMessage();}
}