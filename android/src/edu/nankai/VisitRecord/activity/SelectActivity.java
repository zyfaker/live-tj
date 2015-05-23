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

// 步骤1：继承父类Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class SelectActivity extends Activity {

	// 步骤4：声明该页面中的交互类组件
	private Button btnwrite, btnsearch;

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
		this.setContentView(R.layout.activity_select);
		// 步骤5：实例化操作类组件

		this.btnsearch = (Button) this.findViewById(R.id.btnsearch);
		this.btnwrite = (Button) this.findViewById(R.id.btnwrite);
		
		// 步骤7：组件绑定监听器
		this.btnsearch.setOnClickListener(new ViewOcl());
		this.btnwrite.setOnClickListener(new ViewOcl());
	}

	// 步骤6：创建一个自定义的内部类监听器Linstener
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