package com.example.luximetro;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private  Sensor sensor;
    private SensorEventListener sensorEventListener;
    static float maiorValor;
    static float menorValor = 400000;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensor == null) {
            Toast.makeText(this, "O dispositivo não possui sensor de luz!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                float value = sensorEvent.values[0];
                if (value < menorValor){
                    menorValor = value;
                } else  if (value > maiorValor){
                    maiorValor = value;
                }

                TextView txtResultadoProg = (TextView)
                        findViewById(R.id.txtResultado);
                txtResultadoProg.setText("Luminosidade: " + value + " lx");

                TextView txtMenorProg = (TextView)
                        findViewById(R.id.txtMenorLum);
                txtMenorProg.setText("luminosidade menor:" + menorValor + "1x");

                TextView txtMaiorProg = (TextView)
                        findViewById(R.id.txtMaiorLum);
                txtMaiorProg.setText("luminosidade maior" + maiorValor + "1x");

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, sensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
















}