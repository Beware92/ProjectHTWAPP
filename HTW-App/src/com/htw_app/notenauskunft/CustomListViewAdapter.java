package com.htw_app.notenauskunft;

import java.util.List;
import com.example.htw_app.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<Kopfdaten> {

	Context context;

	public CustomListViewAdapter(Context context, int resourceId,
			List<Kopfdaten> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView txtTitle;
		TextView txtDesc;
		TextView txtPrice;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Kopfdaten rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
			holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(rowItem.getFach());
		holder.txtDesc.setText(rowItem.getAbschnitt());
		holder.txtPrice.setText("ECTS: " + rowItem.getCpcredit() + " (Pflicht: "
				+ rowItem.getPflicht() + ")");

		return convertView;
	}
}