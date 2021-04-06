package cn.llm.aop;

import cn.llm.annotation.SysLogger;
import cn.llm.entity.SysLog;
import cn.llm.service.LogService;
import cn.llm.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private LogService logService;

    @Before("@annotation(cn.llm.annotation.SysLogger)")
    public void saveSysLog(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();

        SysLog sysLog=new SysLog();
        SysLogger sysLogger=method.getAnnotation(SysLogger.class);
        if (sysLogger!=null){
            sysLog.setModuleName(sysLogger.value());
        }
        //请求方法名
        String className=joinPoint.getTarget().getClass().getName();
        String methodName=signature.getName();
        sysLog.setMethod(className+"."+methodName+"()");
        //请求的参数
        Object[] args=joinPoint.getArgs();
        if (args.length!=0){
            sysLog.setParams(Arrays.toString(args));
        }
        sysLog.setIpAddress(IpUtils.getIpAddress());
    }
}
