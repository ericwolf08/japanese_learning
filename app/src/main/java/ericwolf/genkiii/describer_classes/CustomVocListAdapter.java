package ericwolf.genkiii.describer_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ericwolf.genkiii.R;

/**
 * Created by Eric on 09.08.2016.
 */
public class CustomVocListAdapter extends BaseAdapter {
    private ArrayList<VocListEntry> listData;
    private LayoutInflater layoutInflater;

    public CustomVocListAdapter(Context aContext, ArrayList<VocListEntry> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.voclist_item, null);
            holder = new ViewHolder();
            holder.headlineView = (TextView) convertView.findViewById(R.id.english);
            holder.reporterNameView = (TextView) convertView.findViewById(R.id.filler);
            holder.reportedDateView = (TextView) convertView.findViewById(R.id.japanese);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headlineView.setText(listData.get(position).getEnglish());
        holder.reporterNameView.setText(listData.get(position).getFiller());
        holder.reportedDateView.setText(listData.get(position).getJapanese());
        return convertView;
    }

    static class ViewHolder {
        TextView headlineView;
        TextView reporterNameView;
        TextView reportedDateView;
    }
}
