package com.mick.example.metronome.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.mick.example.metronome.R;
import com.mick.example.metronome.domains.MetronomeEvent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class Metronome {
    private Context mContext;
    private final int MILLIS_IN_MINUTE = 60000;

    private int bpm = 30;
    private String session;
    private int count;

    private MediaPlayer mp;
    private boolean isFirst = true;
    private Timer mainTimer;
    private MyTimerTask mainTimerTask;
    private String status;

    public Metronome(Context mContext, String session) {
        this.mContext = mContext;
        this.session = session;
        status = "";
    }


    public void start() {
        stop();
        mp = MediaPlayer.create(mContext, R.raw.beat);
        mainTimer = new Timer();
        mainTimerTask = new MyTimerTask();
        mainTimer.scheduleAtFixedRate(mainTimerTask, 0, MILLIS_IN_MINUTE / bpm);
        setStatus("Playing in " + getBpm() + " BPM.");

        count = 0;
    }

    public void stop() {
        if (mainTimer != null) {
            mainTimer.cancel();
            setStatus("Not Playing");
            isFirst = true;
        }
    }

    private void playSound() {
        if (isFirst) {
            isFirst = false;
        } else {
            Date date = new Date();
            long currentTimestamp = date.getTime();
            mp.start();

            count++;
            MetronomeEvent event = new MetronomeEvent(session, count, bpm, currentTimestamp);
            Log.i(TAG, event.toString());
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBpm() {
        return bpm;
    }

    public boolean setBpm(int newBpm) {
        if (newBpm <= 0) {
            stop();
            Log.e(TAG, "Error: The value of BPM cannot be smaller than or equal to 0.");
            return false;
        } else {
            bpm = newBpm;
            return true;
        }
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            playSound();
        }
    }
}
