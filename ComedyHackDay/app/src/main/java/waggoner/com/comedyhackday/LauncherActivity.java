package waggoner.com.comedyhackday;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.appevents.AppEventsLogger;

import butterknife.ButterKnife;
import waggoner.com.comedyhackday.locations.Location;
import waggoner.com.comedyhackday.locations.LocationList;
import waggoner.com.comedyhackday.questions.QuestionsList;

public class LauncherActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentManager.enableDebugLogging(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        fragmentManager = getFragmentManager();
        // neither toolbar or fab are supported by butterknife apparently =/
        Toolbar apptoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(apptoolbar);

        LocationList ll = LocationList.createInstance();
        ll.setOnLocationSelectedListener(new LocationList.LocationSelectedListener() {
            public void onLocationSelected(Location location) {
                QuestionsList fragment = QuestionsList.createInstance(location);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_display, fragment).addToBackStack(null).commit();
            }
        });

        FragmentTransaction fragT = fragmentManager.beginTransaction();
        fragT.add(R.id.fragment_display, ll).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
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

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else  {
            getFragmentManager().popBackStack();
        }
    }
}
