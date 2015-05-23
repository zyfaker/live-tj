package edu.nankai.VisitRecord.activity;

import com.chinasofti.diary.activity.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

// ����1���̳и���Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class SelectActivity extends Activity {

	// ����4��������ҳ���еĽ��������
	private Button btnwrite, btnsearch;

	// ����2����д�����е�һ������OnCreate() Ctrl+Shift+S,V
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ��չ�����ظô���ı�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����3������������Ӧ�Ĳ����ļ����а�
		this.setContentView(R.layout.activity_select);
		// ����5��ʵ�������������

		this.btnsearch = (Button) this.findViewById(R.id.btnsearch);
		this.btnwrite = (Button) this.findViewById(R.id.btnwrite);
		
		// ����7������󶨼�����
		this.btnsearch.setOnClickListener(new ViewOcl());
		this.btnwrite.setOnClickListener(new ViewOcl());
	}

	// ����6������һ���Զ�����ڲ��������Linstener
	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnsearch:
				
				intent.setClass(SelectActivity.this, SearchActivity.class);
				startActivity(intent);
				finish();
				break;
				
			case R.id.btnwrite:
				
				
				intent.setClass(SelectActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		}

	}

}