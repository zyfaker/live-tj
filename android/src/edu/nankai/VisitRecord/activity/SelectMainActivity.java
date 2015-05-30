package edu.nankai.VisitRecord.activity;

import java.util.Calendar;

import com.chinasofti.diary.activity.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

@SuppressLint("SimpleDateFormat")
public class SelectMainActivity extends Activity {

	private EditText selectname, selectdate;
	private Button selectByname, selectBydate, selectBytoday;
	DatePickerDialog datePickerDialog = null;
	static final int SHOW_DATE_PICKER_DIALOG = 1;

	

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
		@SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnSelectByname:

				String selectname1 = selectname.getText().toString().trim();

				intent.setClass(SelectMainActivity.this,
						ListReslutActivity.class);

				intent.putExtra("selectname1", selectname1);
				startActivity(intent);
				finish();
				break;

			case R.id.btnSelectBydate:

				 String selectdate1=selectdate.getText().toString().trim();
				 intent.setClass(SelectMainActivity.this,
				 ListReslutActivity.class);
				 intent.putExtra("selectdate", selectdate1);
				 System.out.println(selectdate1);
				 startActivity(intent);
				 finish();
				// Toast.makeText(getApplicationContext(), selectdate1,
				// Toast.LENGTH_LONG).show();
				// DatePickerFragment datePicker = new DatePickerFragment();
				// datePicker.show(getFragmentManager(), "datePicker");
				
//				String selectdate1 = selectdate.getText().toString().trim();
//				intent.setClass(SelectMainActivity.this,
//						ListReslutActivity.class);
//				intent.putExtra("selectname1", selectdate1);
//				startActivity(intent);
//				finish();
				

				break;
			case R.id.btnselectToday:

//				Date now = new Date();
//
//				intent.setClass(SelectMainActivity.this,
//						ListReslutActivity.class);
//				startActivity(intent);
//				finish();
//				Toast.makeText(getApplicationContext(),
//						new SimpleDateFormat("yyyy-MM-dd").format(now),
//						Toast.LENGTH_LONG).show();
				showDialog(SHOW_DATE_PICKER_DIALOG);
				break;
			default:
				break;
			}

		}
	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			selectdate.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);

		}
	};

	protected Dialog onCreateDialog(int id, Bundle args) {
		
		Calendar now= Calendar.getInstance();  
	
		int year=now.get(Calendar.YEAR);  
	 
		int monthOfYear=now.get(Calendar.MONTH);  
	
		int dayOfMonth= now.get(Calendar.DAY_OF_MONTH);  

		System.out.println(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
		// TODO Auto-generated method stub
		if (id == SHOW_DATE_PICKER_DIALOG) {
			return new DatePickerDialog(this, onDateSetListener, year,
					monthOfYear, dayOfMonth);
		}
		return null;

	}
}
