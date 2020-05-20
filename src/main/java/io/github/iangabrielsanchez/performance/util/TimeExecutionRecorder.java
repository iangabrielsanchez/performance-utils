package io.github.iangabrielsanchez.performance.util;

import io.github.iangabrielsanchez.performance.exception.TagNotFoundException;
import io.github.iangabrielsanchez.performance.exception.TimeNotEndedException;
import io.github.iangabrielsanchez.performance.exception.TimeNotStartedException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TimeExecutionRecorder {
    private static Map<String, TimeExecutionSampler> samplerMap;

    static{
        samplerMap = new HashMap<String, TimeExecutionSampler>();
    }

    public static void startAddingSample(String tag){
        TimeExecutionSampler record;
        if(samplerMap.containsKey(tag)){
            record = samplerMap.get(tag);
        }
        else{
            record = new TimeExecutionSampler();
            samplerMap.put(tag, record);
        }
        try {
            record.startSample();
        } catch (TimeNotEndedException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void stopAddingSample(String tag, boolean forced){
        if(forced){
            try {
                stopAddingSample(tag);
            } catch (TagNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void stopAddingSample(String tag) throws TagNotFoundException {
        TimeExecutionSampler record;
        if(samplerMap.containsKey(tag)){
            record = samplerMap.get(tag);
            try {
                record.endSample();
            } catch (TimeNotStartedException e) {
                log.error(e.getMessage(), e);
            }
        }
        else{
            throw new TagNotFoundException("The tag '"+ tag +"' was not found in the records." +
                    " Make sure to call TitmeExecutionRecorder#startAddingSample(String) first");
        }
    }

    public static TimeRecord getTimeRecord(String tag, boolean forced){
        if(forced){
            try{
                return getTimeRecord(tag);
            } catch (TagNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public static TimeRecord getTimeRecord(String tag) throws TagNotFoundException{
        if(samplerMap.containsKey(tag)){
            return samplerMap.get(tag).getTimeRecord();
        }
        else{
            throw new TagNotFoundException("The tag '"+ tag +"' was not found in the records.");
        }
    }
}
