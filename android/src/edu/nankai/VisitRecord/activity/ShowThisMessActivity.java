package edu.nankai.VisitRecord.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.chinasofti.diary.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowThisMessActivity extends Activity {
	private EditText sidthis, sdatethis, snamethis, sphonethis, sbelongthis,
			sknowthis, sguwenthis, sbeizhuthis;

	private Button btnbackhead, btnbacklogin, btnbackadd, btnperfect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 步骤3：将窗体与相应的布局文件进行绑定
		this.setContentView(R.layout.showthisadd_activity);

		this.sidthis = (EditText) this.findViewById(R.id.sidthis);
		this.sdatethis = (EditText) this.findViewById(R.id.sdatethis);
		this.snamethis = (EditText) this.findViewById(R.id.snamethis);
		this.sphonethis = (EditText) this.findViewById(R.id.sphonethis);
		this.sbelongthis = (EditText) this.findViewById(R.id.sbelongthis);
		this.sknowthis = (EditText) this.findViewById(R.id.sknowthis);
		this.sguwenthis = (EditText) this.findViewById(R.id.sguwenthis);
		this.sbeizhuthis = (EditText) this.findViewById(R.id.sbeizhuthis);

		Intent intent = getIntent();

		sdatethis.setText(intent.getStringExtra("sdateintent"));
		snamethis.setText(intent.getStringExtra("snameintent"));
		sphonethis.setText(intent.getStringExtra("sphoneintent"));
		sbelongthis.setText(intent.getStringExtra("sbelongintent"));
		sknowthis.setText(intent.getStringExtra("sknowintent"));
		sguwenthis.setText(intent.getStringExtra("sguwenintent"));
		sbeizhuthis.setText(intent.getStringExtra("sbeizhuintent"));
		
		Workbook readwb = null;
		try {
			readwb = Workbook.getWorkbook(new FileInputStream(
					android.os.Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/visitrecord.xls"));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet readsheet = readwb.getSheet(0);
		int j = readsheet.getRows();
		sidthis.setText(Integer.toString(j));
		readwb.close();

		this.btnbackhead = (Button) this.findViewById(R.id.btnBackhead);
		this.btnbackadd = (Button) this.findViewById(R.id.btnBackadd);
		this.btnbacklogin = (Button) this.findViewById(R.id.btnBacklogin);
		this.btnperfect = (Button) this.findViewById(R.id.btnperfect);

		// 步骤7：组件绑定监听器
		this.btnbackhead.setOnClickListener(new ViewOcl());
		this.btnbackadd.setOnClickListener(new ViewOcl());
		this.btnbacklogin.setOnClickListener(new ViewOcl());
		this.btnperfect.setOnClickListener(new ViewOcl());
	}

	private class ViewOcl implements View.OnClickListener {
		@SuppressWarnings("unused")
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnBackhead:

				intent.setClass(ShowThisMessActivity.this, SelectActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnBacklogin:

				intent.setClass(ShowThisMessActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.btnBackadd:

				intent.setClass(ShowThisMessActivity.this, AddActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnperfect:

				Workbook readwb = null;
				WritableCellFormat cellFormat = new WritableCellFormat();
				try {
					cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
				} catch (WriteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					readwb = Workbook.getWorkbook(new FileInputStream(
							android.os.Environment
									.getExternalStorageDirectory()
									.getAbsolutePath()
									+ "/visitrecord.xls"));
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Sheet readsheet = readwb.getSheet(0);

				int j = readsheet.getRows();
				// System.out.println("account="+account.length());
				if (readwb != null) {
					try {

						jxl.write.WritableWorkbook wwb = Workbook
								.createWorkbook(new File(android.os.Environment
										.getExternalStorageDirectory()
										.getAbsolutePath()
										+ "/visitrecord.xls"), readwb);

						jxl.write.WritableSheet ws = wwb.getSheet(0);

						jxl.write.Label label = new jxl.write.Label(0, j,
								Integer.toString(j));
						jxl.write.Label label2 = new jxl.write.Label(1, j,
								sdatethis.getText().toString().trim());
						jxl.write.Label label3 = new jxl.write.Label(2, j,
								snamethis.getText().toString().trim());
						jxl.write.Label label4 = new jxl.write.Label(3, j,
								sphonethis.getText().toString().trim());
						jxl.write.Label label5 = new jxl.write.Label(4, j,
								sbelongthis.getText().toString().trim());
						jxl.write.Label label6 = new jxl.write.Label(5, j,
								sknowthis.getText().toString().trim());
						jxl.write.Label label7 = new jxl.write.Label(6, j,
								sguwenthis.getText().toString().trim());
						jxl.write.Label label8 = new jxl.write.Label(9, j,
								sbeizhuthis.getText().toString().trim());

						label.setCellFormat(cellFormat);
						label3.setCellFormat(cellFormat);
						// 将定义好的单元格添加到工作表中
						ws.addCell(label);
						ws.addCell(label2);
						ws.addCell(label3);
						ws.addCell(label4);
						ws.addCell(label5);
						ws.addCell(label6);
						ws.addCell(label7);
						ws.addCell(label8);

						wwb.write();
						wwb.close();
						readwb.close();
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IndexOutOfBoundsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					intent.setClass(ShowThisMessActivity.this,
							SelectActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "修改出错",
							Toast.LENGTH_LONG).show();
				}

				break;
			default:
				break;
			}

		}
	}
}
