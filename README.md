# Metronome

The basic metronome application that can play peiodic sounds based on the settings.

## Table of Contents
* [Sample Code](https://github.com/Mick609/Metronome/blob/master/README.md#sample-code)

* [Metronome](https://github.com/Mick609/Metronome/blob/master/README.md#public-class-metronome-commickexamplemetronomeutilitiesmetronome)

* [MetronomeEvent](https://github.com/Mick609/Metronome/blob/master/README.md#public-class-metronomeevent-commickexamplemetronomedomainsmetronomeevent)

## Sample Code:

```
//Init the variable
Metronome metronome = new Metronome(this, "Test Session");

//set bpm, (ret == false) if setBpm(newBpm) fails
boolean ret = metronome.setBpm(60);

//Start playing
metronome.start();

//Stop playing
metronome.stop();

//Get all the Metronome events record
ArrayList<MetronomeEvent> eventsList = getAllMetronomeEvents();

//Display all the recorded metronome events
For(int i = 0;i < eventsList.size();i++;){
  System.out.println(eventsList.get(i).toString);
}
```

## public class Metronome (com.mick.example.metronome.utilities.Metronome)

The class Metronome has 13 variables, 1 constructor, 10 methods, and 1 sub-class for a modyfied timertask object.

### Variables:

1. private Context mContext: The Context object refer to the main application.

2. private final int MILLIS_IN_MINUTE = 60000: The int constant int for the miliseconds in one minute.

3. private int bpm = 30: The int for BPM of the metrononme, default set to 30, can be changed using method.

4. private String session: The session code.

5. private int count: The count of the metronome hit since the start of this session.

6. private MediaPlayer mp: he media player for the metronome sound effect.

7. private boolean isFirst = true: The boolean value indicateds that if the metronome event is the first one of the session. 

8. private Timer mainTimer: The timer for the scheduling of the metronome event.

9. private MyTimerTask mainTimerTask: The Timetask object for the scheduling of the metronome event.

10.private ServerNTP mServerNTP: The object for the usage of NTP service.

11.private String fileName: the name of the file stored in the system storage.

12.ArrayList<MetronomeEvent>: The ArrayList that contains all the metronome events stored in the system.

### Constructor:
Initialise the Context, session, and status variable.

### Methods

1. public void start(): Stop current metronome event if there is any, then init the MediaPlayer, status, count, Timer, and TimerTask, then schedule the metronome event at a fixed rate, that means the event only happens based on the first one (No drafting effect).

2. public void stop(): If the metronome is playing, stop the playback.

3. private void playSound(): If this is not the first time of the metronome session to play sound, then play sound. The reason of skiping the first playback is that the it tends to be delayed significantly.

4. public String getStatus(): Returns the String value of variable status.

5. public void setStatus(String status): Set the status variable to the new value.

6. public int getBpm(): Returns the current bpm variable.

7. private void appendToFile(String data, Context context): append the recent metronome event to the file for storage.

8. private ArrayList<String> readFile(Context context): read the file that contains the metonome events records.

9. public ArrayList<MetronomeEvent> getAllMetronomeEvents(): returns all the metrononme events stored in the file system.

### Sub-class

class MyTimerTask extends TimerTask: Contains an Override method public void run() that calls the playsound() method.

## public class MetronomeEvent (com.mick.example.metronome.domains.MetronomeEvent)

The class has 4 variables, 1 constructor, and 10 methods.

### Variables

1. private String session: The session code of this MetronomeEvent.

2. private int count: The count code of this MetronomeEvent.

3. private int currentBPM: The current bpm of this MetronomeEvent.

4. private long timestamp: The timestamp when dropping this MetronomeEvent.

### Constructor:

Initialise the session, count, currentBPM, and timestamp variables.

### Methods:

1. public String toString(): Returns the information of this event in String.
1. public String toStorageString(): Returns the information of this event in String for the file storage.

and Getters and Setters for the other variables...
