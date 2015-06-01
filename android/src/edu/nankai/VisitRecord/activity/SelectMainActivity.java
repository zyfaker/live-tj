package edu.nankai.VisitRecord.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.chinasofti.diary.activity.R;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

@SuppressLint("SimpleDateFormat")
public class SelectMainActivity extends Activity {

	private EditText selectname, selectdate;
	private Button selectByname, selectBydate, selectBytoday, selectDay,
			btnkdxf;
	DatePickerDialog datePickerDialog = null;
	static final int SHOW_DATE_PICKER_DIALOG = 1;
	private RecognizerDialog rd;
	protected static final String TAG = "ThirdActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.selectmain);
		// 步骤5：实例化操作类组件
		rd = new RecognizerDialog(this, "appid=50e1b967");
		this.selectname = (EditText) this.findViewById(R.id.selectByname);
		this.selectdate = (EditText) this.findViewById(R.id.selectBydate);

		this.selectByname = (Button) this.findViewById(R.id.btnSelectByname);

		this.selectBydate = (Button) this.findViewById(R.id.btnSelectBydate);
		this.selectBytoday = (Button) this.findViewById(R.id.btnselectToday);
		this.selectDay = (Button) this.findViewById(R.id.btnselectDay);
		this.btnkdxf = (Button) this.findViewById(R.id.btnkdxf);

		// 步骤7：组件绑定监听器
		this.selectBydate.setOnClickListener(new ViewOcl());
		this.selectByname.setOnClickListener(new ViewOcl());
		this.selectBytoday.setOnClickListener(new ViewOcl());
		this.selectDay.setOnClickListener(new ViewOcl());
		this.btnkdxf.setOnClickListener(new ViewOcl());
//		selectname.setOnTouchListener(new OnTouchListener() {
//
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				selectname.setSelection(selectname.getText().toString()
//						.length());
//				// Editable ea = content.getText();
//				// Selection.setSelection(ea,ea.length());
//
//				return false;
//			}
//
//		});
	}

	private class ViewOcl implements View.OnClickListener {
		@SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnkdxf:

				showReconigizerDialog();

				break;
			case R.id.btnSelectByname:

				String selectname1 = selectname.getText().toString().trim();

				intent.setClass(SelectMainActivity.this,
						ListReslutActivity.class);

				intent.putExtra("selectname1", selectname1);
				startActivity(intent);
				finish();
				break;

			case R.id.btnSelectBydate:

				String selectdate1 = selectdate.getText().toString().trim();
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

				// String selectdate1 = selectdate.getText().toString().trim();
				// intent.setClass(SelectMainActivity.this,
				// ListReslutActivity.class);
				// intent.putExtra("selectname1", selectdate1);
				// startActivity(intent);
				// finish();

				break;
			case R.id.btnselectDay:
				showDialog(SHOW_DATE_PICKER_DIALOG);
				break;
			case R.id.btnselectToday:

				Date now = new Date();

				intent.setClass(SelectMainActivity.this,
						ListReslutActivity.class);
				intent.putExtra("selectToday", new SimpleDateFormat(
						"yyyy-MM-dd").format(now));
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}

		}
	}

	private void showReconigizerDialog() {
		// setEngine(String engine,String params,String grammar);
		/**
		 * * 识别引擎选择，目前支持以下五种 “sms”：普通文本转写 “poi”：地名搜索 “vsearch”：热词搜索
		 * “vsearch”：热词搜索 “video”：视频音乐搜索 “asr”：命令词识别
		 * 
		 * params 引擎参数配置列表
		 * 附加参数列表，每项中间以逗号分隔，如在地图搜索时可指定搜索区域：“area=安徽省合肥市”，无附加参数传null
		 */
		rd.setEngine("vsearch", null, null);

		// //设置采样频率，默认是16k，android手机一般只支持8k、16k.为了更好的识别，直接弄成16k即可。
		rd.setSampleRate(RATE.rate16k);

		final StringBuilder sb = new StringBuilder();
		Log.i(TAG, "识别准备开始.............");

		rd.setListener(new RecognizerDialogListener() {
			public void onResults(ArrayList<RecognizerResult> result,
					boolean isLast) {
				for (RecognizerResult recognizerResult : result) {
					sb.append(recognizerResult.text);
					// System.out.printf(recognizerResult.text);
					Log.i(TAG, "识别一条结果为···" + recognizerResult.text);
				}
			}

			public void onEnd(SpeechError error) {
				Log.i(TAG, "识别完成.............");
				selectname.setText(sb.toString());
				// Toast.makeText(getApplicationContext(), sb.toString(),
				// Toast.LENGTH_LONG).show();
				Log.i(TAG, "识别完成:" + selectname.getText().toString());
			}
		});

		selectname.setText(""); // 先设置为空，等识别完成后设置内容

		rd.show();

	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			if (monthOfYear > 9 && dayOfMonth > 9) {
				selectdate.setText(year + "-" + (monthOfYear + 1) + "-"
						+ dayOfMonth);
			} else if (monthOfYear > 9 && dayOfMonth < 10) {
				selectdate.setText(year + "-" + (monthOfYear + 1) + "-0"
						+ dayOfMonth);
			} else if (monthOfYear > 9 && dayOfMonth < 10) {
				selectdate.setText(year + "-0" + (monthOfYear + 1) + "-0"
						+ dayOfMonth);
			}

		}
	};

	protected Dialog onCreateDialog(int id, Bundle args) {

		Calendar now = Calendar.getInstance();

		int year = now.get(Calendar.YEAR);

		int monthOfYear = now.get(Calendar.MONTH);

		int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);

		// System.out.println(year + "-" + (monthOfYear + 1) + "-" +
		// dayOfMonth);
		// TODO Auto-generated method stub
		if (id == SHOW_DATE_PICKER_DIALOG) {
			return new DatePickerDialog(this, onDateSetListener, year,
					monthOfYear, dayOfMonth);
		}
		return null;

	}
}
