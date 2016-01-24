package com.example.rk.beadrk;
import android.content.Intent;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Vibrator;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends Activity {

    Button btnBeall,btnStart, btnStop;
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    int count=0;
    EditText futidotekszt,pihidotekszt;
    int pihido,futido;
    int mp;
    double kovetkezo;
    MediaPlayer mysound;


    CounterClass timer;



    TextView textViewTime;
    protected void onClick(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void info(View view) {
        Intent intent = new Intent(this, infoActivity.class);
        EditText editText = (EditText) findViewById(R.id.nev);
        String nev = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, nev);
        startActivity(intent);
    }
    public void highscore(View view) {
        Intent intent = new Intent(this, asyncActivity.class);
        startActivity(intent);
    }
    public void playmusic(View view)
    {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.sonyericsson.music");
        startActivity(intent);

    }
    public void startol(View view)
    {
        timer.cancel();
        pihidotekszt = (EditText)findViewById(R.id.pihidotext);
        futidotekszt = (EditText)findViewById(R.id.futidotext);
        pihido=Integer.parseInt(pihidotekszt.getText().toString());
        futido=Integer.parseInt(futidotekszt.getText().toString());
        mp=(pihido*1000*20)+(futido*1000*20)+2000;
        kovetkezo=(pihido*1000*20)+(futido*1000*20);
        timer = new CounterClass(mp, 1000);
        timer.start();

    }
    public void leallit(View view)
    {
        timer.cancel();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pihidotekszt = (EditText)findViewById(R.id.pihidotext);
        futidotekszt = (EditText)findViewById(R.id.futidotext);
        mysound - MediaPlayer.create(this,R.music.wake);

        pihido=Integer.parseInt(pihidotekszt.getText().toString());
        futido=Integer.parseInt(futidotekszt.getText().toString());
        mp=(pihido*1000*20)+(futido*1000*20);

        btnBeall = (Button) findViewById(R.id.startol);

        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText(getResources().getString(R.string.starttxt));

        timer = new CounterClass(mp, 1000);



    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
            double secs = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                    + 60 * (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));



            if (kovetkezo==secs*1000) {
                if (count%2==0)
                {
                    vb.vibrate(400);
                    kovetkezo=(secs*1000)-(futido*1000);
                    count++;
                    mysound.start();
                }
                else
                {
                    vb.vibrate(400);
                    kovetkezo=(secs*1000)-(pihido*1000);
                    count++;
                    mysound.start();
                }
            }





        }

        @Override
        public void onFinish() {
            textViewTime.setText(getResources().getString(R.string.endtxt));
            Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vb.vibrate(400);
            Intent intent2 = new Intent(MainActivity.this, sikerActivity.class);
            EditText editText = (EditText) findViewById(R.id.nev);
            String nev = editText.getText().toString();
            intent2.putExtra(EXTRA_MESSAGE, nev);
            startActivity(intent2);

        }




    }
}
