# Metronome

The basic metronome application that can play peiodic sounds based on the settings.

## public class Metronome (com.mick.example.metronome.utilities.Metronome)

The class Metronome has 10 variables, 1 constructor, 7 methods, and 1 sub-class for a modyfied timertask object.

### Variables:

#### private Context mContext: 

The Context object refer to the main application.

#### private final int MILLIS_IN_MINUTE = 60000: 

The int constant int for the miliseconds in one minute.

#### private int bpm = 30: 

The int for BPM of the metrononme, default set to 30, can be changed using method.

#### private String session:

The session code.

#### private int count;

The count of the metronome hit since the start of this session.

#### private MediaPlayer mp;

he media player for the metronome sound effect.

#### private boolean isFirst = true;

The boolean value indicateds that if the metronome event is the first one of the session. 

#### private Timer mainTimer;

The timer for the scheduling of the metronome event.

#### private MyTimerTask mainTimerTask;

The Timetask object for the scheduling of the metronome event.

#### private String status;

The current status of the metronome application (is playing or not).
