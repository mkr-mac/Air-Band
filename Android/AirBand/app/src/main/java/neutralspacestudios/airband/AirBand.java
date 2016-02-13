package neutralspacestudios.airband;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.ViewFlipper;


public class AirBand extends Activity implements SensorEventListener{
    private float STRUMTHRESH = 10;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private boolean canStrum;
    TextView x,y,z;
    Button  bass,gtr,lead;
    TextView counter;
    private byte instrument;
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
        x = (TextView)findViewById(R.id.textView);
        y = (TextView)findViewById(R.id.textView2);
        z = (TextView)findViewById(R.id.textView3);
        counter = (TextView)findViewById(R.id.count);
        vf = (ViewFlipper) findViewById( R.id.viewFlipper );
        canStrum = true;


        ((Button)findViewById(R.id.bassButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 0;
                fetchPortAndIP();
                vf.showNext();
            }
        });

        ((Button)findViewById(R.id.gtrButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 1;
                fetchPortAndIP();
                vf.showNext();
            }
        });
        ((Button)findViewById(R.id.leadButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 2;
                fetchPortAndIP();
                vf.showNext();
            }
        });

        ((Button)findViewById(R.id.piano)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 3;
                fetchPortAndIP();
                vf.showNext();
            }
        });

        ((Button)findViewById(R.id.synth)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 4;
                fetchPortAndIP();
                vf.showNext();
            }
        });
        ((Button)findViewById(R.id.trumpet)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 5;
                fetchPortAndIP();
                vf.showNext();
            }
        });
        ((Button)findViewById(R.id.flute)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instrument = 6;
                fetchPortAndIP();
                vf.showNext();
            }
        });

        (findViewById(R.id.strumView)).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == event.ACTION_DOWN) {
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

    public void fetchPortAndIP()
    {
        SendStrumGram.PORT = Integer.parseInt( ((EditText)findViewById(R.id.port)).getText().toString());
        SendStrumGram.ip = ((EditText)findViewById(R.id.ip)).getText().toString();
        Log.v("Neutralde",SendStrumGram.ip + " " + SendStrumGram.PORT);
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
            x.setText("X: " + (int)nx);
            y.setText("Y: " + (int)ny);
            z.setText("Z: " + (int)nz);


            // Don't strum unless we return to normal
            if(nx + ny <= 11f)
            {
                canStrum = true;
            }




            if(canStrum && (nx + ny > (11f  + STRUMTHRESH)))
            {
                canStrum = false;
                new SendStrumGram().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (this));
            }
        }
    }



    public byte getID()
    {
        return instrument;
    }

}
