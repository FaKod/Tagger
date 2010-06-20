package eu.linesofcode.taggerbot.activity;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.R;

public class CreateTag extends Activity {

    public static final String EXTRA_LOCATION = "CreateTag.EXTRA_LOCATION";
    public static final String EXTRA_NAME = "CreateTag.EXTRA_NAME";
    public static final String EXTRA_TEXT = "CreateTag.EXTRA_TEXT";

    private Location location;
    private EditText editName;
    private EditText editText;
    private EditText editLatitude;
    private EditText editLongitude;
    private Button buttonCancel;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_tag);
        editLatitude = (EditText) findViewById(R.id.createtag_location_latitude);
        editLongitude = (EditText) findViewById(R.id.createtag_location_longitude);
        editName = (EditText) findViewById(R.id.createtag_name);
        editText = (EditText) findViewById(R.id.createtag_text);
        buttonCancel = (Button) findViewById(R.id.createtag_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        buttonCreate = (Button) findViewById(R.id.createtag_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                createTag();
            }
        });

        location = (Location) getIntent().getExtras().get(EXTRA_LOCATION);
        editLatitude.setText(formatDegree(location.getLatitude(), "N", "S"));
        editLongitude.setText(formatDegree(location.getLongitude(), "E", "W"));
    }

    private String formatDegree(double value, String positiveHemi,
            String negativeHemi) {
        DecimalFormat fmt = (DecimalFormat) DecimalFormat.getInstance();
        fmt.applyPattern("0.00000");
        String hemi = value > 0 ? positiveHemi : negativeHemi;
        return fmt.format(value) + " \u00b0" + hemi;
    }

    private void createTag() {
        if (ClientState.getState().isConnected()) {
            Intent returnValue = new Intent();
            returnValue.putExtra(EXTRA_LOCATION, location);
            returnValue.putExtra(EXTRA_NAME, editName.getText().toString());
            returnValue.putExtra(EXTRA_TEXT, editText.getText().toString());
            setResult(RESULT_OK, returnValue);
            finish();
        } else {
            Toast.makeText(this, R.string.showmap_toast_nonetwork,
                    Toast.LENGTH_LONG).show();
        }
    }

}
