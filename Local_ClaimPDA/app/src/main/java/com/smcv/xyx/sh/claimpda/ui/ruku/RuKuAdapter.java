package com.smcv.xyx.sh.claimpda.ui.ruku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.model.RuKuNewBean;

import java.util.ArrayList;

/**
 * Created by wangxin on 16/04/2018.
 */

public class RuKuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RuKuNewBean> ruKuNewBeans;
    public RuKuAdapter(Context context,ArrayList<RuKuNewBean> ruKuNewBeans) {
        this.context = context;
        this.ruKuNewBeans = ruKuNewBeans;
    }

    @Override
    public int getCount() {
        return null == ruKuNewBeans? 0 : ruKuNewBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return ruKuNewBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ruku,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_parts_code.setText(ruKuNewBeans.get(position).getPartsId());
        viewHolder.tv_parts_name.setText(ruKuNewBeans.get(position).getReasonDesc());
        viewHolder.tv_ruku_type.setText(ruKuNewBeans.get(position).getReasonType());
        return convertView;
    }
    class ViewHolder{

        TextView tv_parts_code;
        TextView tv_parts_name;
        TextView tv_ruku_type;

        ViewHolder(View view) {
            tv_parts_code = (TextView)view.findViewById(R.id.tv_parts_code);
            tv_parts_name = (TextView)view.findViewById(R.id.tv_parts_name);
            tv_ruku_type = (TextView)view.findViewById(R.id.tv_ruku_type);
        }
    }
}
