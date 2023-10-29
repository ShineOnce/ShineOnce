package com.axkea.review.review.common.logger;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author welsir
 * @date 2023/10/26 21:59
 **/
@Component
public class CommonLogger {

    @Resource(name="${review.logger.handler}")
    private LoggerHandler loggerHandler;

    public void info(String msg,Object... args){
        loggerHandler.info(msg,args);
    }

    public void error(String msg,Object... args){
        loggerHandler.error(msg,args);
    }

    public void warn(String msg,Object... args){
        loggerHandler.warn(msg,args);
    }

    public void debug(String msg,Object... args){
        loggerHandler.debug(msg,args);
    }
}
