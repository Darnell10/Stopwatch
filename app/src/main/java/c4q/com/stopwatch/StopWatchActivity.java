package c4q.com.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    /** Use variable seconds to record number of seconds that pass
     *  Use variable running to see if Stopwatch is running*/
    private int seconds =0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        //Separate method to update the stopwatch. We’re starting it when the activity is created.
        runTimer();
    }

    //Starts the stopwatch running whe Start is clicked.
    public void onClickStart (View view){
        //Start the stop watch.
        running = true;
    }

    //Stops the stopwatch from running when Stop button is clicked.
    public void onClickStop(View view){
        //Stop the stopwatch running.
        running = false;
    }

    // Resets stopWatch when Reset button is clicked
    public void onClickReset(View view){
        //Stop the stopwatch running and set the seconds to 0.
        running =false;
        seconds=0;
    }

    private void runTimer(){
        //gets the views from the layout
        final TextView timeView = (TextView)findViewById(R.id.time_view);

        // creates new Handler
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                // Next 4 lines format the seconds into hours, minutes ans seconds.
                int hours = seconds/3600;
                int minutes= (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);

                //Set the Textview text
                timeView.setText(time);

                // if program is running iterate numbers
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000); //Post the code again with a delay of 1 second.
            }
        });
    }




}
