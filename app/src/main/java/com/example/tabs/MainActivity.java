package com.example.tabs;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.sourceforge.zmanim.ComplexZmanimCalendar;
import net.sourceforge.zmanim.util.GeoLocation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements zmanClickListener{


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<cell> times;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //String[] s = java.util.TimeZone.getAvailableIDs();
        //String j = String.join(", ", s);

        String locationName = "Tel Aviv";
        double latitude = 32.085300; //Lakewood, NJ
        double longitude = 34.781769; //Lakewood, NJ
        double elevation = 0; //optional elevation
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tel_Aviv");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        ComplexZmanimCalendar czc = new ComplexZmanimCalendar(location);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        TextView timesTv = findViewById(R.id.times);
        timesTv.setText("Times for "+locationName+" on "+dateFormat.format(date)+":");


        Date sunrise = czc.getSunrise();
        Date sunset = czc.getSunset();
        Date alos = czc.getAlosHashachar();
        Date endKS = czc.getSofZmanShma(sunrise,sunset);
        Date mid = czc.getChatzos();
        Date endTfila = czc.getSofZmanTfila(sunrise,sunset);
        Date minchaGedola = czc.getMinchaGedola();
        Date minchaKetana = czc.getMinchaKetana();
        Date plag = czc.getPlagHamincha();


        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        cell sunriseC = new cell("Sunrise: ",time(sunrise));
        cell sunsetC = new cell("Sunset: ",time(sunset));
        cell alosC = new cell("Alos Hashachar: ",time(alos));
        cell endKSC = new cell("Sof Zman Kriat Shma: ",time(endKS));
        cell midC = new cell("Chatzot(day/night): ",time(mid));
        cell endTfilaC = new cell("Sof Zman Tfila: ",time(endTfila));
        cell minchaGedolaC = new cell("Mincha Gedola: ",time(minchaGedola));
        cell minchaKetanaC = new cell("Sof Zman Mincha Ketana: ",time(minchaKetana));
        cell plagC = new cell("Plag Hamincha: ",time(plag));
        times = new ArrayList<>();
        times.add(alosC);
        times.add(sunriseC);
        times.add(endKSC);
        times.add(endTfilaC);
        times.add(midC);
        times.add(minchaGedolaC);
        times.add(minchaKetanaC);
        times.add(plagC);
        times.add(sunsetC);

        recyclerView.setAdapter(new ZmanimListAdapter(times, this));












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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void zmanClicked(int pos) {
        String title = times.get(pos).getTitle();
        String time = times.get(pos).getTime();

        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra(EXTRA_MESSAGE,title);

        startActivity(intent);

    }
}
