package tw.parseweatherdata;

import java.util.ArrayList;

import tw.parseweatherdata.Model.MinT;

interface DataCallBack {
    void notifyData(ArrayList<MinT> minTList);
    void onItemClick(MinT minT);
}
