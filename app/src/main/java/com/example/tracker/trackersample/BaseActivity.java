package com.example.tracker.trackersample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends Activity implements View.OnClickListener {

	Button soundRecogBtn, loactionBtn, dbBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		soundRecogBtn = (Button) findViewById(R.id.btn_sound_recog);
		loactionBtn = (Button) findViewById(R.id.btn_location);
		dbBtn = (Button) findViewById(R.id.btn_database);

		soundRecogBtn.setOnClickListener(this);
		loactionBtn.setOnClickListener(this);
		dbBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if(id == R.id.btn_sound_recog) {
			startActivity(new Intent(this, SoundRecogActivity.class));

		} else if(id == R.id.btn_location) {
			startActivity(new Intent(this, LocationActivity.class));

		} else if(id == R.id.btn_database) {
			startActivity(new Intent(this, DatabaseActivity.class));

		}
	}
}
