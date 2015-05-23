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
import android.widget.EditText;
import android.widget.Toast;

// ����1���̳и���Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class SearchActivity extends Activity {

	// ����4��������ҳ���еĽ��������
	private EditText password, idnumber;
	private Button btnlogins;

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
		this.setContentView(R.layout.search_layout);
		// ����5��ʵ�������������

		this.password = (EditText) this.findViewById(R.id.password);
		this.idnumber = (EditText) this.findViewById(R.id.idnumber);

		this.btnlogins = (Button) this.findViewById(R.id.btnLogins);

		// ����7������󶨼�����
		this.btnlogins.setOnClickListener(new ViewOcl());
	}

	// ����6������һ���Զ�����ڲ��������Linstener
	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			
			String password1 = password.getText().toString().trim();
			String idnumber2 = idnumber.getText().toString().trim();
			if(password1.equals("dfcq")&&idnumber2.length()!=0)
			{
		    System.out.println(password1+idnumber2);
		    
			Intent intent = new Intent(SearchActivity.this, Search2Activity.class);
			intent.putExtra("idnumber", idnumber2);
            startActivity(intent);
            //finish();//����ͨ���������������Activity���������ڣ�
		    }else if(!password1.equals("dfcq")){
		    	Toast.makeText(getApplicationContext(), "�������",
						Toast.LENGTH_LONG).show();
		    }else{
		    	Toast.makeText(getApplicationContext(), "��Ŵ���",
						Toast.LENGTH_LONG).show();
		    }
			}
	}

}