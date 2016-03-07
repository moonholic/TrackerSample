package com.example.tracker.trackersample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MOON on 3/7/16.
 * Referred to http://dsnight.tistory.com/15
 */
public class SoundRecogActivity extends Activity {

	Intent mIntent;
	SpeechRecognizer mRecognizer;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_recog);

		initView();

		mIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		mIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
		mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

		mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
		mRecognizer.setRecognitionListener(listener);
		mRecognizer.startListening(mIntent);
	}

	private void initView() {
		textView = (TextView) findViewById(R.id.result);
	}

	// 음성인식 리스너
	private RecognitionListener listener = new RecognitionListener() {

		@Override
		public void onRmsChanged(float rmsdB) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onResults(Bundle results) {
			// TODO Auto-generated method stub
			String key = "";
			key = SpeechRecognizer.RESULTS_RECOGNITION;
			ArrayList<String> mResult = results.getStringArrayList(key);
			String[] rs = new String[mResult.size()];
			mResult.toArray(rs);
			if(rs.length > 0) {
				textView.setText(""+rs[0]);
			}
		}

		@Override
		public void onReadyForSpeech(Bundle params) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPartialResults(Bundle partialResults) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEvent(int eventType, Bundle params) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onError(int error) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onBufferReceived(byte[] buffer) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onBeginningOfSpeech() {
			// TODO Auto-generated method stub

		}
	};
}