package neutralspacestudios.airband;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;


public class AirBand extends Activity implements SensorEventListener{

    // How hard we have to strum in the downward direction to register a sound.
    private final float STRUMTHRESH = 10;
    // On a guitar instrument, prevents us from strumming multiple times.
    private boolean canStrum;
    // On a wind instrument, calculates if we are blowing last turn.
    private double lastAmplitude;
    // Instrument ID. Should have enum'd but too late for that
    private byte instrument = -1;
    // 1 for Piano
    // 2 for Guitar
    // 3 for Bass
    // 4 for Synthesizer
    // 5 for Sax
    // 6 for Tpt
    // 7 for Flute
    // The last registered accelromteres spots, needed for the saxophone.
    private float nx,ny,nz;

    // Flips from button view to next view
    ViewFlipper vf;

    @Override
    public void onAccuracyChanged(Sensor s,int foo)
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_band);
        SensorManager senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
                    ((TextView)findViewById(R.id.instrumentv)).setText("Piano\nTouch anywhere on the screen to play a chord.");
                    instrument = 1;
                    vf.showNext();
                }
            }
        });

        ((Button)findViewById(R.id.synth)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    ((TextView)findViewById(R.id.instrumentv)).setText("Synthesizer\nTouch anywhere on the screen to get a note. The higher on the screen you touch, the higher the note.");
                    instrument = 4;
                    vf.showNext();
                }
            }
        });
        ((Button)findViewById(R.id.trumpet)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    instrument = 6;
                    ((TextView)findViewById(R.id.instrumentv)).setText("Violin\nHold your device screen-side down as if it were a bow. Touch your screen to play the violin while moving your phone gently.");
                    vf.showNext();
                }
            }
        });
        ((Button)findViewById(R.id.flute)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(fetchPortAndIP()) {
                    ((TextView)findViewById(R.id.instrumentv)).setText("Flute\nHold your phone like a flute and blow into your microphone. Tilt up and down for different pitches.");
                    instrument = 7;
                    vf.showNext();
                }
            }
        });


        (findViewById(R.id.strumView)).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int a = event.getAction();
                if(a == event.ACTION_DOWN && (instrument == 1 || instrument == 4 || instrument == 6)) {
                    if(instrument == 6)
                        new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, SendStrumGram.VIOLIN_ON);
                    if(instrument == 1)
                        new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, SendStrumGram.PIANO_PLAY);
                    else {
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);
                        int h = dm.heightPixels; // etc...
                        int y = (int) event.getY();
                        double pos = ((double) y / h);
                        byte note = (byte) (1 + SendStrumGram.SYNTH_HIGH - (((SendStrumGram.SYNTH_HIGH + 1) - SendStrumGram.SYNTH_LOW) * (pos)));
                        Log.v("NeutalDe", "Played a" + note);
                        new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, note);

                    }
                    return true;
                }

                if(a == event.ACTION_UP && (instrument == 6)) {
                   Log.v("NeutralDe","AY!");
                    new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, SendStrumGram.VIOLIN_OFF);
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
            nx = sensorEvent.values[0];
            ny = sensorEvent.values[1];
            nz = sensorEvent.values[2];


                // Don't strum unless we return to normal
                if (nx + ny <= 11f) {
                    canStrum = true;
                }

                //gtr/bass
                if (canStrum && (nx + ny > (11f + STRUMTHRESH) && (instrument == 2 || instrument == 3))) {
                    canStrum = false;
                    byte msg;
                    if (instrument == 2)
                        msg = SendStrumGram.GUITAR_STRUM;
                    else
                        msg = SendStrumGram.BASS_STRUM;
                    // gtr = 2, bass = 3
                    new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Byte(msg));
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
                double amp =  m.getAmplitude();
                Log.v("Neutralde", "T" + amp);

                if(lastAmplitude < 30000 && amp >= 30000) {
                    Log.v("Neutralde", "SEND!");
                    Log.v("Neutralde",nx + " " + ny + " " + nz);
                    //is todo
                    byte msg = 0;
                    msg = (byte)(SendStrumGram.SAX_LOW + ((SendStrumGram.SAX_HIGH - SendStrumGram.SAX_LOW)*((ny + 9.0) / 18.0)));
                    new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, msg);
                }
                if(lastAmplitude > 30000 && amp < 30000)
                {
                    byte msg = 0;
                    if(instrument == 5)
                        msg = SendStrumGram.SAX_OFF;
                    new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, msg);
                }
                lastAmplitude = amp;
            }
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new RecorderTask(), 0, 10);
    }
}
