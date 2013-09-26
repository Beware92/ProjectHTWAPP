package com.gebaeudeplan;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.htw_app.R;

public class SearchResultAdapter extends ArrayAdapter<SearchResultElement>   {
	
	Context context;

    
    public SearchResultAdapter(Context context, int resourceId, List<SearchResultElement> items) {
    	super(context, resourceId, items);
        this.context = context;

    }

    private class ViewHolder {
        TextView nameView;
        TextView preNameView;
        TextView telView;
        TextView roomView;
        TextView titleView;
       
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        SearchResultElement item = getItem(position);
        
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listviewelement_searchroom, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.textViewName);
            holder.preNameView = (TextView) convertView.findViewById(R.id.textViewPreName);
            holder.telView = (TextView) convertView.findViewById(R.id.textViewTel);
            holder.roomView = (TextView) convertView.findViewById(R.id.textViewRoomNumber);
            holder.titleView = (TextView) convertView.findViewById(R.id.textViewTitle);
            
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.nameView.setText(item.getName());
        holder.preNameView.setText(item.getPrename());
        holder.telView.setText(item.getTel());
        
        holder.roomView.setText(item.getRoom());
        holder.titleView.setText(item.getTitle());
        
        //holder.reporterNameView.setText("By, " + listData.get(position).getReporterName());
        //holder.reportedDateView.setText(listData.get(position).getDate());
 
        return convertView;
    }
    

}
