package com.example.test;

import android.os.Bundle;
import android.widget.ImageView;

public class DetailsActivity extends BaseActivity {
	
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		iv = (ImageView) findViewById(R.id.iv);
		
		if (getIntent() != null && getIntent().getStringExtra("pic") != null){
			imageLoader.displayImage(getIntent().getStringExtra("pic"), iv);
		}
	}
}
