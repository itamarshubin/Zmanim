package com.example.tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.zmanim.ComplexZmanimCalendar;
import net.sourceforge.zmanim.util.GeoLocation;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class Main3Activity extends AppCompatActivity {




    //     AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

    TextView tv2;
    SeekBar sb;
    TextView tv;
    // static String test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        final String title = intent.getStringExtra(EXTRA_MESSAGE);

        tv2 = findViewById(R.id.title);

        String locationName = "Tel Aviv";
        double latitude = 32.085300; //Lakewood, NJ
        double longitude = 34.781769; //Lakewood, NJ
        double elevation = 0; //optional elevation
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tel_Aviv");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        ComplexZmanimCalendar czc = new ComplexZmanimCalendar(location);

        final Date time;
        switch (title)
        {
            case "Sunrise: ":
                time=czc.getSunrise();
                break;
            case "Sunset: " :
                time = czc.getSunset();
                break;
            case "Alos Hashachar: " :
                time=czc.getAlosHashachar();
                break;
            case "Sof Zman Kriat Shma: ":
                time =czc.getSofZmanShma(czc.getSunrise(),czc.getSunset());
                break;
            case "Chatzot(day/night): ":
                time = czc.getChatzos();
                break;
            case "Sof Zman Tfila: ":
                time =  czc.getSofZmanTfila(czc.getSunrise(),czc.getSunset());
                break;
            case "Mincha Gedola: ":
                time = czc.getMinchaGedola();
                break;
            case "Sof Zman Mincha Ketana: ":
                time = czc.getMinchaKetana();
                break;
            case "Plag Hamincha: ":
                time = czc.getPlagHamincha();
                break;
            default:
                time = czc.getSunrise();

        }

        tv = (TextView)findViewById(R.id.textView);
        // tv.setText("i will tell you 5 minuets before "+title+" at " + time(time));
//        sb=(SeekBar) findViewById(R.id.seekBar);
//        sb.setMax(60);
//        sb.setProgress(5);
//        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                //tv.setText("i will tell you "+i +" minuets before "+title+" at " + time(time));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        NumberPicker np = findViewById(R.id.minutesPicker);

        np.setMinValue(0);
        np.setMaxValue(59);


        //  Button setAlaram = findViewById(R.id.setAlarm);
        // setAlaram.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //     public void onClick(View view) {
        //scheduleNotification(getNotification("10 second delay"), 10000);

        //       }
        //   });
    }


    public String time(Date n)
    {
        return fix(n.getHours())+":"+fix(n.getMinutes())+":"+fix(n.getSeconds())+"\n";
    }
    public String fix(int n) {
        if (n > 9)
            return ""+n;
        return "0"+n;
    }

   /* private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        return builder.build();
    }
*/
}
