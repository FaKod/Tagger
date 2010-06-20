package eu.linesofcode.taggerbot.activity;

import java.text.DecimalFormat;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.R;
import eu.linesofcode.taggerbot.client.data.ELocationTag;
import eu.linesofcode.taggerbot.client.data.LocationType;
import eu.linesofcode.taggerbot.client.data.Tlocation;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;

public class CreateTag extends Activity {

    public static final String EXTRA_LOCATION = "CreateTag.EXTRA_LOCATION";

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
        Tlocation tagLocation = new Tlocation();
        tagLocation.setLocationType(LocationType.LocationTag.toString());
        tagLocation.setLatitude(location.getLatitude());
        tagLocation.setLongitude(location.getLongitude());

        Tlocationtag tag = new Tlocationtag();
        tag.setName(editName.getText().toString());
        tag.setInfotext(editText.getText().toString());
        tag.setPoint(tagLocation);

        final ELocationTag data = new ELocationTag();
        data.setLocationTag(tag);

        if (ClientState.getState().isConnected()) {
            // TODO create tag on server
//            worker.submit(new Runnable() {
//
//                @Override
//                public void run() {
//                    ClientTask task = new ClientTask() {
//
//                        @Override
//                        public boolean run(TaggerClient client) {
//                            Tuser me = client.user().get();
//                            Tlocationtag tag = client.tags().create(data,
//                                    Integer.parseInt(me.getId()));
//                            List<Tlocationtag> added = new ArrayList<Tlocationtag>();
//                            added.add(tag);
//                            ClientState.getState().modifyOwnTags(added,
//                                    new ArrayList<Tlocationtag>());
//                            return true;
//                        }
//                    };
//                    ClientState.getState().doTask(task);
//                }
//            });
        } else {
            Toast.makeText(this, R.string.showmap_toast_nonetwork,
                    Toast.LENGTH_LONG).show();
        }
    }

}
