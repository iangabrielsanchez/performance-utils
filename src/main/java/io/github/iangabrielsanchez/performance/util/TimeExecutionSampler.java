package io.github.iangabrielsanchez.performance.util;

import io.github.iangabrielsanchez.performance.exception.TimeNotEndedException;
import io.github.iangabrielsanchez.performance.exception.TimeNotStartedException;

public class TimeExecutionSampler {
    private TimeRecord timeRecord;
    private long startTime;
    private long stopTime;
    private boolean started;

    public TimeExecutionSampler(){
        this.started = false;
        this.timeRecord = new TimeRecord();
    }

    public void startSample() throws TimeNotEndedException {
        if(this.started){
            throw new TimeNotEndedException("Time has NOT BEEN ENDED for the current sample");
        }
        else{
            this.started = true;
            this.startTime = System.nanoTime();
        }
    }

    public void endSample() throws TimeNotStartedException {
        if(this.started){
            this.stopTime = System.nanoTime();
            long executionTime = this.stopTime - this.startTime;
            this.timeRecord.addTime(executionTime);
            this.started = false;
        }
        else{
            throw new TimeNotStartedException("Time has not been started for the current sample.");
        }
    }

    public TimeRecord getTimeRecord() {
        return timeRecord;
    }
}
