package com.wigo.voc.kspo.aop;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;

@Intercepts({@Signature(type = Executor.class, method="", args= {MappedStatement.class, Object.class})})
@Aspect
@Component("VocComnAspect")
@Slf4j
public class VocComnAspect {

    // parameter 중 Map 또는 EzMap에 현재 접속 유저정보를 loginUsr로 넣는다.
    @SuppressWarnings("unchecked")
    @Before(value = "execution(* com.wigo.voc.kspo..*Dao.*(..))")
    public void beforeSetLoginUsr(JoinPoint jp){
        log.debug("*** beforeDao crud method put loginUsr ***");
        Object[] args = jp.getArgs();
        for(Object arg : args){
            if(arg instanceof EzMap){
                ((EzMap) arg).put("loginUsr", Utilities.getLoginUserCd());
            } else if(arg instanceof Map){
                ((Map<String, Object>) arg).put("loginUsr", Utilities.getLoginUserCd());
            }
        }
    }
}
