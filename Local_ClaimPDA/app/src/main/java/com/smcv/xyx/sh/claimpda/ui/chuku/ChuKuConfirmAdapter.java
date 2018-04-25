package com.smcv.xyx.sh.claimpda.ui.chuku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.model.ChuKuNewBean;

import java.util.ArrayList;

/**
 * Created by SummerChen on 2018/3/27.
 */

public class ChuKuConfirmAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ChuKuNewBean> chuKuNewBeens;

    public ChuKuConfirmAdapter(Context context, ArrayList<ChuKuNewBean> chuKuNewBeens) {
        this.context = context;
        this.chuKuNewBeens = chuKuNewBeens;
    }

    @Override
    public int getCount() {
        return null == chuKuNewBeens ? 0 : chuKuNewBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return chuKuNewBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_chukuconfirm, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_claim_code.setText(chuKuNewBeens.get(position).getPartsReturnPartCode());
        viewHolder.tv_claim_name.setText(chuKuNewBeens.get(position).getPartsReturnPartName());
        return convertView;
    }

    class ViewHolder {
        TextView tv_claim_code;
        TextView tv_claim_name;

        ViewHolder(View view) {
            tv_claim_code = (TextView) view.findViewById(R.id.tv_claim_code);
            tv_claim_name = (TextView) view.findViewById(R.id.tv_claim_name);
        }
    }
}
