package neutralspacestudios.airband;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AirBand extends Activity implements SensorEventListener {
    private float STRUMTHRESH = 10;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private byte id = 1;
    private boolean canStrum;
    TextView x,y,z;
    TextView counter;


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
        canStrum = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_air_band, menu);
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
                new SendStrumGram().execute(this);
            }
        }
    }

    public byte getID()
    {
        return id;
    }

}
