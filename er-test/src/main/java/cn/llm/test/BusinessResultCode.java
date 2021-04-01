package cn.llm.test;

/**
 * 顶级父项目experiment-report
 * er-business-->er-
 * er-web
 * er-file
 * er-eureka
 * er-api
 * https://wangsong.blog.csdn.net/article/details/101266588?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control&dist_request_id=1328767.161.16172694701562469&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control
 */
public class BusinessResultCode extends ResultCode{

    public static final ResultCode PLAT_BUSINESS_XXXX =new BusinessResultCode("51001","平台业务流编码已存在");

    public BusinessResultCode(String code,String msg){super(code,msg);}
}
