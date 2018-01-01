package c4q.com.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    /**
     * Use variable seconds to record number of seconds that pass
     * Use variable running to see if Stopwatch is running
     * wasRunning, to record whether the stopwatch was running before the onStop() method was
     * called so that we know whether to set it running again when the activity becomes visible again.
     */
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        if (savedInstanceState != null) {
            //Retrieves the values of the seconds and running variables from the Bundle.
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        //Separate method to update the stopwatch. We’re starting it when the activity is created.
        runTimer();
    }

    // Record whether the stopwatch was running when the onStop() method was called.
    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    //implement the onStart() method. If the stopwatch was running, set it running again.
    @Override
    public void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
       wasRunning =running;
       running = false;
        }


    @Override
    public void onResume(){
        super.onResume();
        if (wasRunning){
            running = true;
        }
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Saves the values of the seconds ,running and wasRunning variables to the Bundle.
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    //Starts the stopwatch running when Start is clicked.
    public void onClickStart(View view) {
        //Start the stop watch.
        running = true;
    }

    //Stops the stopwatch from running when Stop button is clicked.
    public void onClickStop(View view) {
        //Stop the stopwatch running.
        running = false;
    }

    // Resets stopWatch when Reset button is clicked
    public void onClickReset(View view) {
        //Stop the stopwatch running and set the seconds to 0.
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        //gets the views from the layout
        final TextView timeView = (TextView) findViewById(R.id.time_view);

        // creates new Handler
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                // Next 4 lines format the seconds into hours, minutes ans seconds.
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                //Set the Textview text
                timeView.setText(time);

                // if program is running iterate numbers
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000); //Post the code again with a delay of 1 second.
            }
        });
    }


}
