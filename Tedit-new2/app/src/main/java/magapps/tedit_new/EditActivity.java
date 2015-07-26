package magapps.tedit_new;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;


public class EditActivity extends ActionBarActivity {
    public ImageView MainImage;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF31556C")));
        setContentView(R.layout.activity_edit);
        MainImage = (ImageView) findViewById(R.id.main_image);
        MainImage.setOnLongClickListener(new View.OnLongClickListener() {
            // Get intent, action and MIME type
            Intent intent = getIntent();
            String action = intent.getAction();
            String type = intent.getType();


            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                longC();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
        this.finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        super.onBackPressed();
    }

    public void image_onC(View view) {
        Toast.makeText(getApplicationContext(), "You have to long click the image to change it.", Toast.LENGTH_LONG).show();
    }

    protected void longC() {
        Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(90);
        new AlertDialog.Builder(this)
                .setTitle("Image")
                .setMessage("Choose an image from your gallery or take one.")
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivityForResult(intent, 7);
                    }
                })
                .setNeutralButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent Galli = new Intent();
                        // Show only images, no videos or anything else
                        Galli.setType("image/*");
                        Galli.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)

                        startActivityForResult(Intent.createChooser(Galli, "Select Picture"), PICK_IMAGE_REQUEST);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })


                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 7) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            MainImage.setImageBitmap(image);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Uri selectedImageUri = data.getData();
            MainImage.setImageURI(selectedImageUri);
        }
    }
}
