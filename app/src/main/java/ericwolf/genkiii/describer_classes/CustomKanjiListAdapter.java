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
 * Created by Eric on 11.08.2016.
 */
public class CustomKanjiListAdapter extends BaseAdapter {
    private ArrayList<KanjiListEntry> listData;
    private LayoutInflater layoutInflater;

    public CustomKanjiListAdapter(Context aContext, ArrayList<KanjiListEntry> listData) {
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

    public boolean hasStableIds () { return true; }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.kanjilist_item, null);
            holder = new ViewHolder();
            holder.head = (TextView) convertView.findViewById(R.id.kanji);
            holder.filler = (TextView) convertView.findViewById(R.id.yomi);
            holder.right = (TextView) convertView.findViewById(R.id.meaning);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.head.setText(listData.get(position).getKanji());

        String yomi = "";
        if(listData.get(position).getOnyomi().trim().length() >= 1){
            yomi += "On-yomi: " + listData.get(position).getOnyomi() + " ";
        }
        if(listData.get(position).getKunyomi().trim().length() >= 1){
            yomi += "Kun-yomi: " + listData.get(position).getKunyomi();
        }

        holder.filler.setText(yomi);
        holder.right.setText(listData.get(position).getMeaning());

        return convertView;
    }

    static class ViewHolder {
        TextView head;
        TextView filler;
        TextView right;
    }
}
