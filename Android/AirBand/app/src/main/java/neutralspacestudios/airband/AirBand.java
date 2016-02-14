package neutralspacestudios.airband;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;


public class AirBand extends Activity implements SensorEventListener{

    private final float STRUMTHRESH = 10;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private boolean canStrum;
    private byte instrument = -1;
    // 1 for Piano
    // 2 for Guitar
    // 3 for Bass
    // 4 for Synthesizer
    // 5 for Sax
    // 6 for Tpt
    // 7 for Flute
    ViewFlipper vf;

    @Override
    public void onAccuracyChanged(Sensor s,int foo)
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_band);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        vf = (ViewFlipper) findViewById( R.id.viewFlipper );
        canStrum = true;

        ((Button)findViewById(R.id.bassButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    instrument = 3;
                    ((TextView)findViewById(R.id.instrumentv)).setText("Bass Guitar\nStrum up and down with your phone.");
                    vf.showNext();
                }
            }
        });

        ((Button)findViewById(R.id.gtrButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (fetchPortAndIP()) {
                    instrument = 2;
                    ((TextView)findViewById(R.id.instrumentv)).setText("Guitar\nStrum up and down with your phone.");
                    vf.showNext();
                }
            }
        });

        ((Button)findViewById(R.id.saxButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    instrument = 5;
                    ((TextView)findViewById(R.id.instrumentv)).setText("Saxophone\nBlow into your phone's mic, and angle your phone up or down.");
                    vf.showNext();
                    addWindsListener();
                }
            }
        });

        ((Button)findViewById(R.id.piano)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    ((TextView)findViewById(R.id.instrumentv)).setText("Piano\nTouch anywhere on the screen.");
                    instrument = 1;
                    vf.showNext();
                }
            }
        });

        ((Button)findViewById(R.id.synth)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    ((TextView)findViewById(R.id.instrumentv)).setText("Synthesizer\nTouch anywhere on the screen.");
                    instrument = 4;
                    vf.showNext();
                }
            }
        });
        ((Button)findViewById(R.id.trumpet)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    instrument = 6;
                    ((TextView)findViewById(R.id.instrumentv)).setText("Trumpet\nBlow into your microphone.");
                    vf.showNext();
                    addWindsListener();
                }
            }
        });
        ((Button)findViewById(R.id.flute)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    ((TextView)findViewById(R.id.instrumentv)).setText("Flute\nBlow into your microphone.");
                    instrument = 7;
                    vf.showNext();
                    addWindsListener();
                }
            }
        });


        (findViewById(R.id.strumView)).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == event.ACTION_DOWN && (instrument == 1 || instrument == 4)) {
                    new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (AirBand.this));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_air_band, menu);
        return true;
    }

    public boolean fetchPortAndIP()
    {
        try {

            SendStrumGram.PORT = Integer.parseInt(((EditText) findViewById(R.id.port)).getText().toString());
            SendStrumGram.ip = ((EditText) findViewById(R.id.ip)).getText().toString();
            Log.v("Neutralde", SendStrumGram.ip + " " + SendStrumGram.PORT);
        } catch (Exception e)
        {
            Toast.makeText(this,"Set a valid IP and port number",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float nx = sensorEvent.values[0];
            float ny = sensorEvent.values[1];
            float nz = sensorEvent.values[2];

            // Don't strum unless we return to normal
            if(nx + ny <= 11f) {
                canStrum = true;
            }

            //gtr/bass
            if(canStrum && (nx + ny > (11f  + STRUMTHRESH) && (instrument == 2 || instrument == 3)))
            {
                canStrum = false;
                new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (this));
            }
        }
    }


    public void addWindsListener()
    {

        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        class RecorderTask extends TimerTask {

            public void run() {
              SoundMeter m = new SoundMeter();
                m.start();
                m.stop();
                Log.v("Neutralde", "T" + m.getAmplitude());
                if(m.getAmplitude() > 30000) {
                    new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (AirBand.this));
                }
            }
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new RecorderTask(), 0, 10);
    }

    public byte getID()
    {
        return instrument;
    }

}
