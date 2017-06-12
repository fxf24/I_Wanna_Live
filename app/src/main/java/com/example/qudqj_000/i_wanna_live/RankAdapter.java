package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class RankAdapter extends BaseAdapter {
    Context context;
    ArrayList<RankData> datas = new ArrayList<>();

    public RankAdapter(Context context, ArrayList<RankData> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.rank_dialog_items,null );
        TextView tv1 = (TextView)convertView.findViewById(R.id.rank_num);
        TextView tv2 = (TextView)convertView.findViewById(R.id.ranker_name);
        TextView tv3 = (TextView)convertView.findViewById(R.id.score_txt);
        tv1.setText(datas.get(position).getRank_num());
        tv2.setText(datas.get(position).getName());
        tv3.setText(datas.get(position).getScore());

        return convertView;
    }
}
