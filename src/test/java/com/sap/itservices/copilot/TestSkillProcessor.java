package com.sap.itservices.copilot;

import com.sap.itservices.copilot.smalltalk.MainApplication;
import com.sap.itservices.copilot.smalltalk.config.SkillProcessorServiceConfig;
import com.sap.itservices.copilot.smalltalk.services.SkillProcessorDispatcher;
import com.sap.itservices.copilot.smalltalk.skillprocessors.MessageReplyProcessor;
import com.sap.itservices.copilot.smalltalk.skillprocessors.SkillProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SkillProcessorServiceConfig.class)
@SpringBootTest(classes = MainApplication.class)
public class TestSkillProcessor {
    
    @Autowired
    SkillProcessorDispatcher processorDispatcher;

    @Autowired
    SkillProcessorDispatcher processorDispatcherAgain;

    @Autowired
    AutowireCapableBeanFactory factory;
    

    @Test
    public void testInjection(){
        assertNotNull(processorDispatcher);
        assertEquals(processorDispatcher, processorDispatcherAgain);
    }

    @Test
    public void testSingleProcessor(){
        assertEquals(3, processorDispatcher.sizeOfSkills());
        Class<SkillProcessor> processorClass = processorDispatcher.getProcessor("small-talk");
        assertNotNull(processorClass);
        Class<SkillProcessor> lunchProcessorClass = processorDispatcher.getProcessor("ask-lunch");
        assertNotNull(lunchProcessorClass);
        Set<Class<SkillProcessor>> processors = processorDispatcher.getUniqueSkillProcessorsSet();
        assertEquals(2, processors.size());
    }


    @Test
    public void testMultipleProcessors(){
        Class<SkillProcessor> smalltalkProcessor = processorDispatcher.getProcessor("small-talk");
        Class<SkillProcessor> greetingsProcessor = processorDispatcher.getProcessor("greetings");
        assertEquals(smalltalkProcessor, greetingsProcessor);
    }


    @Test
    public void testSpringInjection() throws IllegalAccessException, InstantiationException {

        assertNotNull(factory);

        Class<SkillProcessor> smalltalkProcessor = processorDispatcher.getProcessor("small-talk");
        SkillProcessor processor = smalltalkProcessor.newInstance();
        factory.autowireBean(processor);
        MessageReplyProcessor messageReplyProcessor = (MessageReplyProcessor) processor;
        assertNotNull(messageReplyProcessor.imageFetcherService);

    }

    

}
