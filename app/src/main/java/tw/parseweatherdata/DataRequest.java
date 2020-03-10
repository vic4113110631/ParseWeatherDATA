package tw.parseweatherdata;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.parseweatherdata.Model.MinT;

public class DataRequest {

    // 中央氣象局API網址 - 一般天氣預報-今明 36 小時天氣預報
    private final static String URL = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001";

    // 氣象開放資料平台會員授權碼
    private final static String PARAMS_AUTHORIZATION = "CWB-F0E004EB-24C0-448A-A84D-4B0620BA97E1";

    // 縣市 - 臺北市
    public final static String PARAMS_LOCATION = "臺北市";

    private DataCallBack callback;
    private Context context;

    public DataRequest(DataCallBack callback, Context context){
        this.callback = callback;
        this.context = context;
    }

    public void getData() {
        RequestQueue queue = QueueSingleton.getInstance(context).getRequestQueue();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL + "?Authorization=" + PARAMS_AUTHORIZATION + "&locationName=" + PARAMS_LOCATION,

                response -> {
                    ArrayList<MinT> minTList = new ArrayList<>();

                    Gson gson = new Gson();
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray minT =  object.getJSONObject("records")
                                                .getJSONArray("location")
                                                .getJSONObject(0)
                                                .getJSONArray("weatherElement")
                                                .getJSONObject(2)
                                                .getJSONArray("time");

                        Type type = new TypeToken<List<MinT>>() {}.getType();

                        minTList = gson.fromJson(minT.toString(), type);
                        callback.notifyData(minTList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> {
                    error.printStackTrace();
                }) {
        };

        queue.add(request);
    }


}
