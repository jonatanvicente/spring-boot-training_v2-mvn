package com.training.springboottravelagency.bo.config;

public class Range {
    private final int start;
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(int value) {
        return value >= start && value <= end;
    }
}
