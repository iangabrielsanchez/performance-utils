package io.github.iangabrielsanchez.performance.util;

public class TimeRecord {
    private long totalTime;
    private long executionCount;
    private long averageTime;
    private long bestTime;
    private long worstTime;

    public TimeRecord() {
        this.totalTime = 0L;
        this.executionCount = 0L;
        this.averageTime  = 0L;
        this.worstTime = 0L;
        this.bestTime = Long.MAX_VALUE;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public long getExecutionCount() {
        return executionCount;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public long getBestTime() {
        return bestTime;
    }

    public long getWorstTime() {
        return worstTime;
    }

    public void addTime(long time){
        this.totalTime += time;
        this.executionCount += 1;
        this.averageTime = this.totalTime / this.executionCount;
        this.worstTime = Math.max(this.worstTime, time);
        this.bestTime = Math.min(this.bestTime, time);
    }
}
