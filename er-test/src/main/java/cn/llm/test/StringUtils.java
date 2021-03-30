package cn.llm.test;

public class StringUtils {
    private static final String HOLDER_STR="{}";
    private static final int LEN=HOLDER_STR.length();

    public static String formatTemplate(String template,Object... arguments){
        StringBuilder ret=new StringBuilder();
        int repCount=0;
        int lastIndex=-LEN;
        String temp=template;
        while (temp.length()>lastIndex+LEN){
            int idx=temp.indexOf(HOLDER_STR);
            if (idx>=0&&arguments.length>repCount){
                if (arguments[repCount]!=null){
                    ret.append(temp,0,idx);
                    ret.append(arguments[repCount].toString());
                    temp=temp.substring(idx+LEN);
                }
                repCount++;
            }else {
                break;
            }
            lastIndex=idx;
        }
        ret.append(temp);
        return ret.toString();
    }
}
