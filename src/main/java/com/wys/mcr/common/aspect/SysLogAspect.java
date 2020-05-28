

package com.wys.mcr.common.aspect;

import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.utils.HttpContextUtils;
import com.wys.mcr.common.utils.IPUtils;
import com.wys.mcr.common.utils.ServletUtils;
import com.wys.mcr.common.utils.StringUtils;
import com.wys.mcr.entity.Log;
import com.wys.mcr.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private LogService LogService;

    @Pointcut("@annotation(com.wys.mcr.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log sysLog = new Log();
        SysLog syslog = method.getAnnotation(SysLog.class);

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        try {
            String body = ServletUtils.getBodyMsg(ServletUtils.getRequest());
            sysLog.setParams(body);
        } catch (Exception e) {

        }
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
            if (StringUtils.NO.equals(syslog.isSaveParam())) {
                sysLog.setParams("");
            }
        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用户名
        String username = ContextHolder.getUserName();
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateDate(LocalDateTime.now());
        //保存系统日志
        LogService.save(sysLog);
    }
}
