package com.sap.itservices.copilot.smalltalk.services;

import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.fiori.copilot.agentmodel.AIResponse;
import com.sap.itservices.copilot.smalltalk.execption.SkillNotFoundException;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import com.sap.itservices.copilot.smalltalk.skillprocessors.RecastSkill;
import com.sap.itservices.copilot.smalltalk.skillprocessors.SkillProcessor;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SkillProcessorDispatcher {
    

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillProcessorDispatcher.class);

    private Map<String, Class<SkillProcessor>> processorsMap = new ConcurrentHashMap<>();

    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    


    public Class<SkillProcessor> getProcessor(String skillName){
        return processorsMap.get(skillName);
    }
    
    
    public void dispatch(RecastDialogResponse recastResp, AIRequest aiRequest, AIResponse aiResponse) throws SkillNotFoundException {
        String skillName = recastResp.getConversation().getSkill();

        Class<SkillProcessor> skillProcessorClass = getProcessor(skillName);

        if (skillProcessorClass == null){
            LOGGER.debug("{} skill not found! ", skillName);
            throw new SkillNotFoundException(skillName + " Not found");
        }

        try {

            SkillProcessor skillProcessor = getSkillProcessorInstance(skillName);
            skillProcessor.process(aiRequest,aiResponse,recastResp);
            
        } catch (InstantiationException e) {
            LOGGER.error("Cannot instantiate", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException",e);
        }
    }

    public int sizeOfSkills(){
        return processorsMap.size();
    }



    public Set<String> skillNames(){
        return processorsMap.keySet();
    }


    
    public Set<Class<SkillProcessor>> getUniqueSkillProcessorsSet(){
        Set<Class<SkillProcessor>> processorsSet = new HashSet<>();
        processorsSet.addAll(processorsMap.values());
        return processorsSet;
    }


    
    private SkillProcessorDispatcher(){}
    



    
    private SkillProcessor getSkillProcessorInstance(String skillName)
            throws SkillNotFoundException, IllegalAccessException, InstantiationException {
        Class<SkillProcessor> skillProcessorClass = getProcessor(skillName);
        if (skillProcessorClass == null){
            LOGGER.debug("{} skill not found! ", skillName);
            throw new SkillNotFoundException(skillName + " Not found");
        }
        SkillProcessor skillProcessor = skillProcessorClass.newInstance();
        beanFactory.autowireBean(skillProcessor);
        return skillProcessor;
    }


    public SkillProcessorDispatcher(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotatedProcessors = reflections.getTypesAnnotatedWith(RecastSkill.class);
        annotatedProcessors.stream().forEach(clazz -> {
            if (SkillProcessor.class.isAssignableFrom(clazz)){
                String[] skillNames = clazz.getAnnotation(RecastSkill.class).value();
                Arrays.stream(skillNames).forEach(skillName ->{
                    processorsMap.put(skillName,(Class<SkillProcessor>) clazz);
                });
            }
        });

        LOGGER.debug("SkillProcessorDispatcher loads {} processors from {}", sizeOfSkills(), packageName);
    }

    
}
