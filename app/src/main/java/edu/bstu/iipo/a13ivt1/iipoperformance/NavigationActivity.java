package edu.bstu.iipo.a13ivt1.iipoperformance;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.bstu.iipo.a13ivt1.iipoperformance.CreditFragments.FragmentCredit;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
           return true;
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
