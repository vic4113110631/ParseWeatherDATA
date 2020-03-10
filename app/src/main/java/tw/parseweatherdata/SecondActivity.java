package tw.parseweatherdata;

import androidx.appcompat.app.AppCompatActivity;
import tw.parseweatherdata.Model.MinT;
import tw.parseweatherdata.Model.Parameter;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    private static final String ARG_MINT = "MinT";
    private MinT mMinT = new MinT();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            mMinT = (MinT) bundle.getSerializable(ARG_MINT);

        initializeView();
    }

    private void initializeView() {
        TextView text_startTime = findViewById(R.id.text_start_time);
        TextView text_endTime = findViewById(R.id.text_end_time);
        TextView text_temp = findViewById(R.id.text_temp);

        text_startTime.setText(mMinT.getStartTime());
        text_endTime.setText(mMinT.getEndTime());
        Parameter parameter = mMinT.getParameter();
        text_temp.setText(parameter.getTemp().concat(parameter.getUnit()));
    }

}
