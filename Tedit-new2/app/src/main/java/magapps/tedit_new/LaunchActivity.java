package magapps.tedit_new;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class LaunchActivity extends ActionBarActivity {
    public View start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        start = (View) findViewById(R.id.start_button);

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
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

    public void start_on_click(View view) {
        Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(90);
        Intent startIntent = new Intent(LaunchActivity.this, EditActivity.class);
        startActivity(startIntent);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        super.onBackPressed();
    }
}
