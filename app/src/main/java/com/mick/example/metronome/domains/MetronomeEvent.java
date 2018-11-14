package com.mick.example.metronome.domains;

public class MetronomeEvent {
    private String session;
    private int count;
    private int currentBPM;
    private String trueTime;

    public MetronomeEvent(String session, int count, int currentBPM, String trueTime) {
        this.session = session;
        this.count = count;
        this.currentBPM = currentBPM;
        this.trueTime = trueTime;
    }

    public String toString() {
        return "Session: " + getSession() + "; Count: " + getCount() + "; BPM: " + getCurrentBPM() + "; Timestamp: " + trueTime;
    }

    public String toStorageString() {
        return getSession() + "," + getCount() + "," + getCurrentBPM() + "," + trueTime;
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

    public String getTrueTime() {
        return trueTime;
    }

    public void setTrueTime(String trueTime) {
        this.trueTime = trueTime;
    }
}
