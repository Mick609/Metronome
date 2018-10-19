package com.mick.example.metronome.domains;

public class MetronomeEvent {
    private String session;
    private int count;
    private int currentBPM;
    private long timestamp;

    public MetronomeEvent(String session, int count, int currentBPM, long timestamp) {
        this.session = session;
        this.count = count;
        this.currentBPM = currentBPM;
        this.timestamp = timestamp;
    }

    public String toString() {
        return "Session: " + getSession() + "; Count: " + getCount() + "; BPM: " + getCurrentBPM() + "; Timestamp: " + timestamp;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentBPM() {
        return currentBPM;
    }

    public void setCurrentBPM(int currentBPM) {
        this.currentBPM = currentBPM;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
