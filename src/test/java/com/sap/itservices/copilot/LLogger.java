package com.sap.itservices.copilot;

import org.slf4j.helpers.Util;

public class LLogger {

    public void debug(String str){
        

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];

        System.out.println(stackTraceElement.getClassName() +"." +stackTraceElement.getMethodName());


        System.out.println(Util.getCallingClass());

    }
}
