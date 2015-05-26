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

// 步骤1：继承父类Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class AddActivity extends Activity {

	// 步骤4：声明该页面中的交互类组件
	private String account, phone, date;
	private EditText belong, know, guwen, beizhu;
	private Button btnFinish;

	// 步骤2：重写父类中的一个方法OnCreate() Ctrl+Shift+S,V
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 扩展：隐藏该窗体的标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 步骤3：将窗体与相应的布局文件进行绑定
		this.setContentView(R.layout.activity_main);
		// 步骤5：实例化操作类组件
		this.belong = (EditText) this.findViewById(R.id.belong);
		this.beizhu = (EditText) this.findViewById(R.id.beizhu);
		this.know = (EditText) this.findViewById(R.id.know);
		this.guwen = (EditText) this.findViewById(R.id.guwen);

		Intent intent = getIntent();

		account = intent.getStringExtra("account");
		phone = intent.getStringExtra("phone");
		date = intent.getStringExtra("date");

		this.btnFinish = (Button) this.findViewById(R.id.btnfinish);
		// 步骤7：组件绑定监听器
		this.btnFinish.setOnClickListener(new ViewOcl());
	}

	// 步骤6：创建一个自定义的内部类监听器Linstener
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
