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

// 步骤1：继承父类Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class SearchActivity extends Activity {

	// 步骤4：声明该页面中的交互类组件
	private EditText password, idnumber;
	private Button btnlogins;

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
		this.setContentView(R.layout.search_layout);
		// 步骤5：实例化操作类组件

		this.password = (EditText) this.findViewById(R.id.password);
		this.idnumber = (EditText) this.findViewById(R.id.idnumber);

		this.btnlogins = (Button) this.findViewById(R.id.btnLogins);

		// 步骤7：组件绑定监听器
		this.btnlogins.setOnClickListener(new ViewOcl());
	}

	// 步骤6：创建一个自定义的内部类监听器Linstener
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
            //finish();//可以通过这个函数来结束Activity的生命周期；
		    }else if(!password1.equals("dfcq")){
		    	Toast.makeText(getApplicationContext(), "口令错误",
						Toast.LENGTH_LONG).show();
		    }else{
		    	Toast.makeText(getApplicationContext(), "序号错误",
						Toast.LENGTH_LONG).show();
		    }
			}
	}

}