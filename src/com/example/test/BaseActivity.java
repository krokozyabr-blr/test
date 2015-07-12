package com.example.test;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity{
	
	protected ImageLoader imageLoader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (imageLoader == null)
			imageLoader = ImageLoader.getInstance();
		if (!imageLoader.isInited())
			imageLoader.init(getImageLoaderConfiguration());
	}
	
	protected void showError(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	protected ImageLoaderConfiguration getImageLoaderConfiguration(){
		DisplayImageOptions options = new DisplayImageOptions.Builder()
	    .cacheInMemory(true)
	    .cacheOnDisk(true)
	    .build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .memoryCacheExtraOptions(800, 1280) // width, height
        .threadPoolSize(1)
        .threadPriority(Thread.MIN_PRIORITY + 2)
        .denyCacheImageMultipleSizesInMemory()
        .memoryCache(new UsingFreqLimitedMemoryCache(10 * 1024 * 1024)) // 10 Mb
        .defaultDisplayImageOptions(options)
        .build();
		return config;
	}
}
