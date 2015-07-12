package com.example.test;

import java.util.List;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.networking.NetworkingService;
import com.example.test.networking.Photo;
import com.example.test.networking.PhotoGetRequest;
import com.example.test.networking.PhotoGetResponse;

public class MainActivity extends BaseActivity {
	
	private SpiceManager spiceManager = new SpiceManager(NetworkingService.class);
	private List<Photo> list;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				Intent i = new Intent(MainActivity.this, DetailsActivity.class);
				i.putExtra("pic", list.get(position).getLrgpic());
				startActivity(i);
			}
		});
	}
	
	@Override
	protected void onStart() {
		if (!spiceManager.isStarted()) {
			spiceManager.start(this);
		}
		
		spiceManager.execute(new PhotoGetRequest(), new RequestListener<PhotoGetResponse>(){

			@Override
			public void onRequestFailure(SpiceException exc) {
				showError(exc.getMessage());
			}

			@Override
			public void onRequestSuccess(PhotoGetResponse response) {
				
				if (TextUtils.isEmpty(response.getErrorInfo().getErrorMessage())){
					list = response.getResponse().getResponse();
					lv.setAdapter(new ListViewAdapter());
				} else {
					showError(response.getErrorInfo().getErrorMessage());
				}
			}
		});
		super.onStart();
	}
	
	class ListViewAdapter extends BaseAdapter{
		
		LayoutInflater layoutInflater;
		
		public ListViewAdapter() {
			layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return list != null ? list.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return list != null ? list.get(position) : null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
		    if (view == null) {
		      view = layoutInflater.inflate(R.layout.list_adapter, parent, false);
		    }
		    
		    Photo p = (Photo) getItem(position);
		    
		    ImageView iv = (ImageView) view.findViewById(R.id.iv);
		    TextView name = (TextView) view.findViewById(R.id.name);
		    TextView pos = (TextView) view.findViewById(R.id.pos);
		    
		    imageLoader.displayImage(p.getSmallpic(), iv);
		    name.setText(p.getName());
		    pos.setText(p.getPosition());
		    
			return view;
		}
		
	}
}
