package com.mick.example.metronome.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.mick.example.metronome.R;
import com.mick.example.metronome.domains.MetronomeEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Metronome {
    private Context mContext;
    private final String TAG = "metronome";
    private final int MILLIS_IN_MINUTE = 60000;

    private int bpm = 30;
    private String session;
    private int count;

    private MediaPlayer mp;
    private boolean isFirst = true;
    private Timer mainTimer;
    private MyTimerTask mainTimerTask;
    private String status;
    private ServerNTP mServerNTP;
    private String fileName = "/metronome.txt";
    private ArrayList<MetronomeEvent> MetronomeEventsList;

    public Metronome(Context mContext, String session) {
        this.mContext = mContext;
        this.session = session;
        status = "";
        mServerNTP = new ServerNTP(this.mContext);
        MetronomeEventsList = new ArrayList<>();
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
            String trueTime = mServerNTP.getTrueTime().toString();
            mp.start();

            count++;
            MetronomeEvent event = new MetronomeEvent(session, count, bpm, trueTime);
            Log.i(TAG, event.toString());
            appendToFile(event.toStorageString(), mContext);
//            readFile(mContext);
        }
    }


    private void appendToFile(String data, Context context) {
        File file = new File(context.getFilesDir() + fileName);
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file, true);
            outputStream.write(data.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> readFile(Context context) {
        BufferedReader reader = null;
        String filename = context.getFilesDir() + fileName;
        File file = new File(filename);
        ArrayList<String> lineList = new ArrayList<>();

        try {

            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        file.delete();
        return lineList;
    }

    public ArrayList<MetronomeEvent> getAllMetronomeEvents() {
        ArrayList<String> lineList = readFile(mContext);
        for (int i = 0; i < lineList.size(); i++) {
            String[] buf = lineList.get(i).split(",");
            if (buf.length == 4) {
                MetronomeEventsList.add(new MetronomeEvent(buf[0], Integer.parseInt(buf[1]), Integer.parseInt(buf[2]), buf[2]));
            }
        }

        return MetronomeEventsList;
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
