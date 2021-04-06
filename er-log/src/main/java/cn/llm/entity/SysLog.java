package cn.llm.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = -6561249966457001664L;

    private Long id;
    private String ipAddress;
    private String moduleName;
    private String requestUrl;
    private String requestType;
    private String method;
    private Long userId;
    private Date operationTime;
    private Long durationTime;
    private String returnData;
}
