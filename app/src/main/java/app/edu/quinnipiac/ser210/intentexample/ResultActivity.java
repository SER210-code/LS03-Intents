package app.edu.quinnipiac.ser210.intentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String value = getIntent().getStringExtra("key");

        TextView returnVal = (TextView) findViewById(R.id.displayintentextra);

        returnVal.setText(value);


    }
    @Override
    public  void finish(){
        // Prepare data intent

        EditText returnVal = (EditText) findViewById(R.id.returnValue);

        Intent data = new Intent();
        data.putExtra("returnKey", returnVal.getText().toString());

        // Activity finished ok, return the data
        setResult(RESULT_OK, data);

        super.finish();
    }
}
