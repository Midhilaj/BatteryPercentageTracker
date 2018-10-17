package com.midhilaj.cocoalabs.cocoalabs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by midhilaj on 10/16/18.
 */
public class TimeBattryWatcher extends RecyclerView.Adapter<TimeBattryWatcher.MyViewHolder> {
    private final Context mContext;
    private List<TimeDetails> mData;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time,battery;
        public MyViewHolder(View itemView) {
            super(itemView);
            time=(TextView)itemView.findViewById(   R.id.time_txt);
            battery=(TextView)itemView.findViewById(R.id.battery_txt);
        }
    }

    public TimeBattryWatcher(List<TimeDetails> mData, Context context) {
        this.mData = mData;
        this.mContext = context;
    }

    View view;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statues_itenm_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.time.setText(mData.get(position).getTime_()+"");
        holder.battery.setText(mData.get(position).getBatteryP()+"");
        //TODO Fill in your logic for binding the view.
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }
}