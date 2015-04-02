package com.temobi.sx.sdk.vshare.activity;

import org.apache.commons.lang.StringUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.temobi.sx.sdk.vshare.R;
import com.temobi.sx.sdk.vshare.R.layout;
import com.temobi.sx.sdk.vshare.widget.VideoListViewByUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Toast;

public class UserPortalActivity extends Activity {

	public static final String PARAMS_USER_ID = "UserId";
	
	String userId;
	RequestQueue requestQueue;
	VideoListViewByUser mVideoListViewByUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_portal);
		
		requestQueue = Volley.newRequestQueue(this);
		
		Intent data = getIntent();
		if (data.hasExtra(PARAMS_USER_ID)) {
			userId = data.getStringExtra(PARAMS_USER_ID);
		}
		
		if (StringUtils.isEmpty(userId)) {
			Toast.makeText(getApplicationContext(), "调用错误", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		ViewGroup container = (ViewGroup)findViewById(R.id.video_list_container);
		
		mVideoListViewByUser = new VideoListViewByUser(this, requestQueue);
		container.addView(mVideoListViewByUser, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		
		mVideoListViewByUser.load(userId);
	}
	
	public static void start(Context c, String userId) {
		Intent intent = new Intent(c, UserPortalActivity.class);
		intent.putExtra(PARAMS_USER_ID, userId);
		c.startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
