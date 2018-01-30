package app.edu.quinnipiac.ser210.intentexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;
    public static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.result);
    }

    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.inputforintent);
        // used later
        String value = text.getText().toString();
        // TODO 1 create new Intent(context, class)
        Intent i = new Intent(this,ResultActivity.class);
        i.putExtra("key", value);


        // TODO 2 start second activity with
        startActivityForResult(i, REQUEST_CODE);


    }

    public void pickImage(View View) {
        Intent intent = new Intent();
        intent.setType("image/*");  //only choose images no videos
        intent.setAction(Intent.ACTION_GET_CONTENT);  //allows the user to create the data as it runs ..used when you specify the data type
        intent.addCategory(Intent.CATEGORY_OPENABLE); //set if you only accept data that opens as a stream.
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    // TODO 3 Implement this method
    // assumes that "returnkey" is used as key to return the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {


        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            if (intent.hasExtra("returnKey")) {
                String result = intent.getExtras().getString("returnKey");
                if (result != null && result.length() > 0) {
                    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                }
            }
        }
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            InputStream stream = null;
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(intent.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                if (imageView == null)
                    Log.e("MainActivity","imageView null");
                else
                    imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}

