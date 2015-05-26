package edu.nankai.VisitRecord.activity;

import java.io.File;
import java.io.FileInputStream;

import com.chinasofti.diary.activity.R;

import jxl.Sheet;
import jxl.Workbook;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.nankai.VisitRecord.classes.Users;

// ����1���̳и���Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class AddActivity extends Activity {

	// ����4��������ҳ���еĽ��������
	private String account, phone, date;
	private EditText belong, know, guwen, beizhu;
	private Button btnFinish;

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
		this.setContentView(R.layout.activity_main);
		// ����5��ʵ�������������
		this.belong = (EditText) this.findViewById(R.id.belong);
		this.beizhu = (EditText) this.findViewById(R.id.beizhu);
		this.know = (EditText) this.findViewById(R.id.know);
		this.guwen = (EditText) this.findViewById(R.id.guwen);

		Intent intent = getIntent();

		account = intent.getStringExtra("account");
		phone = intent.getStringExtra("phone");
		date = intent.getStringExtra("date");

		this.btnFinish = (Button) this.findViewById(R.id.btnfinish);
		// ����7������󶨼�����
		this.btnFinish.setOnClickListener(new ViewOcl());
	}

	// ����6������һ���Զ�����ڲ��������Linstener
	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String belong1 = belong.getText().toString().trim();
			String know1 = know.getText().toString().trim();
			String guwen1 = guwen.getText().toString().trim();
			String beizhu1 = beizhu.getText().toString().trim();

			Intent intent = new Intent();
			intent.setClass(AddActivity.this, ShowThisMessActivity.class);
			
			intent.putExtra("sdateintent", date);
			intent.putExtra("snameintent", account);	
			intent.putExtra("sphoneintent", phone);
			intent.putExtra("sbelongintent", belong1);	
			intent.putExtra("sknowintent", know1);
			intent.putExtra("sguwenintent", guwen1);	
			intent.putExtra("sbeizhuintent", beizhu1);
			//System.out.println(date+account+phone+guwen1);
			startActivity(intent);
			finish();
//			Toast.makeText(getApplicationContext(), date+account+phone+guwen1, Toast.LENGTH_LONG)
//					.show();
		}

	}

}
