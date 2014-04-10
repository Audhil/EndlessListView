package com.wordpress.smdaudhilbe.endlesslistview_10.custom_component;

import java.util.List;

import com.wordpress.smdaudhilbe.endlesslistview_10.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EndLessAdapter extends ArrayAdapter<String> {
	
	private Context context;
	private List<String> items;
	private int layoutId;

	public EndLessAdapter(Context context, List<String> items, int LayoutId) {
		super(context,LayoutId,items);
		this.context = context;
		this.items = items;
		this.layoutId = LayoutId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
			convertView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId,parent,false);
		
		TextView tView = (TextView)convertView.findViewById(R.id.txt);
		tView.setText(items.get(position));
	
		return convertView;
	}
}