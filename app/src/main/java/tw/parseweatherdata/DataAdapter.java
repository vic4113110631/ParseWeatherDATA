package tw.parseweatherdata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tw.parseweatherdata.Model.MinT;
import tw.parseweatherdata.Model.Parameter;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static enum ITEM_TYPE {
        ITEM_TYPE_MINT,
        ITEM_TYPE_IMAGE
    }

    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private DataCallBack mCallBack;

    private List<MinT> mMinTList;

    public DataAdapter(Context context, DataCallBack callBack, List<MinT> minTList) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mCallBack = callBack;
        this.mMinTList = minTList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_MINT.ordinal()) {
            View view = mLayoutInflater.inflate(R.layout.item_type_a, viewGroup, false);
            return new TypeA_ViewHolder(view);
        } else {
            View view =  mLayoutInflater.inflate(R.layout.item_type_b, viewGroup, false);
            return new TypeB_ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        position /= 2;

        // 「天氣資訊」的Item
        if (holder.getItemViewType() == ITEM_TYPE.ITEM_TYPE_MINT.ordinal()) {
            TypeA_ViewHolder typeA_viewHolder = (TypeA_ViewHolder) holder;

            MinT minT = mMinTList.get(position);
            typeA_viewHolder.text_start_time.setText(minT.getStartTime());
            typeA_viewHolder.text_end_time.setText(minT.getEndTime());

            Parameter parameter = minT.getParameter();
            typeA_viewHolder.text_temp.setText(parameter.getTemp().concat(parameter.getUnit()));

            //  跳至下一頁
            typeA_viewHolder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, SecondActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Bundle bundle = new Bundle();
                bundle.putSerializable("MinT", minT);
                intent.putExtras(bundle);

                mContext.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_MINT.ordinal() : ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
    }

    @Override
    public int getItemCount() {
        return mMinTList.size() * 2 - 1;
    }

    class TypeA_ViewHolder extends RecyclerView.ViewHolder{

        TextView text_start_time;
        TextView text_end_time;
        TextView text_temp;

        TypeA_ViewHolder(@NonNull View view) {
            super(view);

            text_start_time = view.findViewById(R.id.text_start_time);
            text_end_time = view.findViewById(R.id.text_end_time);
            text_temp = view.findViewById(R.id.text_temp);
        }
    }

    class TypeB_ViewHolder extends RecyclerView.ViewHolder{

        TypeB_ViewHolder(@NonNull View view) {
            super(view);

        }
    }

}
