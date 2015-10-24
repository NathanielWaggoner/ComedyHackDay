package waggoner.com.comedyhackday;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.appevents.AppEventsLogger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LauncherActivity extends AppCompatActivity {

    //CallbackManager callbackManager;
    //@InjectView(R.id.login_button)
    //LoginButton loginButton;
    @Bind(R.id.fragment_display)
    ViewGroup fragmentDisplay;
    FragmentManager fragmentManager;

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        fragmentManager = getFragmentManager();
        // neither toolbar or fab are supported by butterknife apparently =/
        Toolbar apptoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(apptoolbar);
        FloatingActionButton fab = ButterKnife.findById(this, R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuestionsList fragment = new QuestionsList();
                fragmentTransaction.add(R.id.fragment_display, fragment);
                fragmentTransaction.commit();
                // add a new question and answer!
                // need a question and answer dialog here.
            }
        });
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
}
