package edu.bstu.iipo.a13ivt1.iipoperformance;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import edu.bstu.iipo.a13ivt1.iipoperformance.CreditFragments.FragmentCredit;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.Students;
import edu.bstu.iipo.a13ivt1.iipoperformance.ExamFragments.FragmentExam;
import edu.bstu.iipo.a13ivt1.iipoperformance.LectureFragments.FragmentLecture;
import edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments.FragmentLaboratory;
import edu.bstu.iipo.a13ivt1.iipoperformance.SeminarFragments.FragmentSeminar;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentLaboratory fragmentLaboratory;
    FragmentLecture fragmentLecture;
    FragmentSeminar fragmentSeminar;
    FragmentCredit fragmentCredit;
    FragmentExam fragmentExam;
    FragmentTransaction fragmentTransaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentLaboratory = new FragmentLaboratory();
        fragmentLecture = new FragmentLecture();
        fragmentSeminar = new FragmentSeminar();
        fragmentCredit = new FragmentCredit();
        fragmentExam = new FragmentExam();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_navigation,fragmentLaboratory);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Отчистка БД
        if (id == R.id.clearDB) {
            List<Students> studentses = new Select().from(Students.class).where().queryList();
            for (int i = 0; i < studentses.size(); i++) {
                studentses.get(i).delete(); //Удаление всех записей из таблицы
            }
        } else if(id ==R.id.downloadDB){
            new ParseTask().execute();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //
        if (id == R.id.lecture) {
            fragmentTransaction.replace(R.id.content_navigation,fragmentLecture);
            fragmentTransaction.addToBackStack(null);

        } else if (id == R.id.laboratory) {
            fragmentTransaction.replace(R.id.content_navigation,fragmentLaboratory);
            fragmentTransaction.addToBackStack(null);



        } else if (id == R.id.seminar) {
            fragmentTransaction.replace(R.id.content_navigation,fragmentSeminar);
            fragmentTransaction.addToBackStack(null);

        } else if (id == R.id.credit) {
            fragmentTransaction.replace(R.id.content_navigation,fragmentCredit);
            fragmentTransaction.addToBackStack(null);

        } else if (id == R.id.exam) {
            fragmentTransaction.replace(R.id.content_navigation,fragmentExam);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

class ParseTask extends AsyncTask<Void, Void, String>{

    private String resultJson = "";

    @Override
    protected String  doInBackground(Void... urls) {

        try {
            URL url = new URL("http://82.179.88.27:8280/core/v1/people");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        Log.d("JSON", strJson);

        JSONObject dataJsonObj;

        try {
            dataJsonObj = new JSONObject(strJson);
            JSONObject embedded = dataJsonObj.getJSONObject("_embedded");
            JSONArray peopleJsonArray = embedded.getJSONArray("people");
            for (int i = 0; i < peopleJsonArray.length(); i++) {
                if (i==619||i==824||i==462||i==209||i==532){
                    Students students = new Students();
                    students.setName(peopleJsonArray.getJSONObject(i).getString("givenName"));
                    students.setSurname(peopleJsonArray.getJSONObject(i).getString("sn"));
                    students.setPatronymic(peopleJsonArray.getJSONObject(i).getString("initials"));
                    students.save();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}