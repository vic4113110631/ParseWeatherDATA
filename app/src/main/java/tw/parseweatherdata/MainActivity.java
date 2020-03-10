package tw.parseweatherdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tw.parseweatherdata.Model.MinT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataCallBack mDataCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetching 「天氣資料」
        initializeCallBack();
        new DataRequest(mDataCallBack, getApplicationContext()).getData();

        // 判斷是否為「第二次之後開啟」
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean EXPERIENCE = sp.getBoolean("EXPERIENCE",false);

        if(EXPERIENCE){
            Toast.makeText(getApplicationContext(), "歡迎回來" , Toast.LENGTH_LONG).show();
        }

        if(!EXPERIENCE) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("EXPERIENCE", true);
            editor.apply();
        }
    }

    private void initializeCallBack() {
        mDataCallBack = new DataCallBack() {
            // 接受「天氣資料」，並做處理呈現
            @Override
            public void notifyData(ArrayList<MinT> minTList) {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                DataAdapter adapter = new DataAdapter(getApplicationContext(), mDataCallBack, minTList);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

        };
    }

}
