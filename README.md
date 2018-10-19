# Metronome
The basic metronome application that can play peiodic sounds based on the settings.

1. public class Metronome (com.mick.example.metronome.utilities.Metronome)
  The class Metronome has 10 variables, 1 constructor, 7 methods, and 1 sub-class for a modyfied timertask object.
  
  Variables:
    1. private Context mContext: 
      The Context object refer to the main application.
      
    2. private final int MILLIS_IN_MINUTE = 60000: 
      The int constant int for the miliseconds in one minute.
      
    3. private int bpm = 30: 
      The int for BPM of the metrononme, default set to 30, can be changed using method.
      
    4. private String session:
      The session code.
      
    5. private int count;
      The count of the metronome hit since the start of this session.
      
    6. private MediaPlayer mp;
      The media player for the metronome sound effect.
      
    7. private boolean isFirst = true;
      The boolean value indicateds that if the metronome event is the first one of the session. 
      
    8. private Timer mainTimer;
      The timer for the scheduling of the metronome event.
      
    9. private MyTimerTask mainTimerTask;
      The Timetask object for the scheduling of the metronome event.
      
    10. private String status;
      The current status of the metronome application (is playing or not).
