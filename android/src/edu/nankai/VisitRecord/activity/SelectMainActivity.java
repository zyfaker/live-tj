package edu.nankai.VisitRecord.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.chinasofti.diary.activity.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class SelectMainActivity extends Activity {

	private EditText selectname, selectdate;
	private Button selectByname, selectBydate, selectBytoday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.selectmain);
		// 步骤5：实例化操作类组件

		this.selectname = (EditText) this.findViewById(R.id.selectByname);
		this.selectdate = (EditText) this.findViewById(R.id.selectBydate);

		this.selectByname = (Button) this.findViewById(R.id.btnSelectByname);

		this.selectBydate = (Button) this.findViewById(R.id.btnSelectBydate);
		this.selectBytoday = (Button) this.findViewById(R.id.btnselectToday);

		// 步骤7：组件绑定监听器
		this.selectBydate.setOnClickListener(new ViewOcl());
		this.selectByname.setOnClickListener(new ViewOcl());
		this.selectBytoday.setOnClickListener(new ViewOcl());
	}

	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnSelectByname:
				
				String selectname1=selectname.getText().toString().trim();

				intent.setClass(SelectMainActivity.this, ListReslutActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(getApplicationContext(), selectname1,
						Toast.LENGTH_LONG).show();
				break;

			case R.id.btnSelectBydate:

				String selectdate1=selectdate.getText().toString().trim();
				intent.setClass(SelectMainActivity.this, ListReslutActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(getApplicationContext(), selectdate1,
						Toast.LENGTH_LONG).show();
				break;
			case R.id.btnselectToday:
				
				Date now = new Date();

				intent.setClass(SelectMainActivity.this, ListReslutActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(getApplicationContext(), new SimpleDateFormat("yyyy-MM-dd")
				.format(now),Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}

		}
	}

}
