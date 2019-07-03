package com.sap.itservices.copilot;

import com.sap.itservices.copilot.smalltalk.MainApplication;
import com.sap.itservices.copilot.smalltalk.config.SkillProcessorServiceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.helpers.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = WSLoggerConfig.class)
//@SpringBootTest(classes = MainApplication.class)
public class TestLogger {



    



    @Test
    public void testGetCaller(){
        hello();
    }


    private void hello(){
//        StackTraceElement[] s = Thread.currentThread().getStackTrace();
//        for (StackTraceElement stackTraceElement : s) {
//            System.out.println(stackTraceElement);
//        }

//        String method_name = Thread.currentThread().getStackTrace()[2].getMethodName();
//        System.out.println(method_name);
//


//        System.out.println(Util.getCallingClass());


        LLogger ll = new LLogger();
        ll.debug("Hello");
    }
}
