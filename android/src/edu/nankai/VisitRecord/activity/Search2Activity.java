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
public class Search2Activity extends Activity {

	// ����4��������ҳ���еĽ��������
	private EditText sid, sdate, sname, sphone, sbelong, sknow, sguwen,
			sbeizhu;
	private Button btnback1, btnback2, btnok;
	

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
		this.setContentView(R.layout.search2_layout);
		// ����5��ʵ�������������

		this.sid = (EditText) this.findViewById(R.id.sid);
		this.sdate = (EditText) this.findViewById(R.id.sdate);
		this.sname = (EditText) this.findViewById(R.id.sname);
		this.sphone = (EditText) this.findViewById(R.id.sphone);
		this.sbelong = (EditText) this.findViewById(R.id.sbelong);
		this.sknow = (EditText) this.findViewById(R.id.sknow);
		this.sguwen = (EditText) this.findViewById(R.id.sguwen);
		this.sbeizhu = (EditText) this.findViewById(R.id.sbeizhu);

		Intent intent = getIntent();

		sid.setText(intent.getStringExtra("idnumber"));

		System.out.println(intent.getStringExtra("idnumber"));
		
		//sid.setText("1");
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
		int row = Integer.parseInt(sid.getText().toString());

		//int row=1;
		Cell[] cells = readsheet.getRow(row);
		// Ȼ����ȡÿһ��Cell�е�ֵ
		String dates = cells[1].getContents();
		String names = cells[2].getContents();
		String phones = cells[3].getContents();
		String belongs = cells[4].getContents();
		String knows = cells[5].getContents();
		String guwens = cells[6].getContents();
		String beizhus = cells[9].getContents();

		//System.out.println(dates+names+guwens);
		
		sdate.setText(dates);
		sname.setText(names);
		sphone.setText(phones);
		sbelong.setText(belongs);
		sknow.setText(knows);
		sguwen.setText(guwens);
		sbeizhu.setText(beizhus);
		
		//sbeizhu.setText("woshibeizhu");

		readwb.close();

		this.btnback1 = (Button) this.findViewById(R.id.btnBack1);
		this.btnback2 = (Button) this.findViewById(R.id.btnBack2);
		this.btnok = (Button) this.findViewById(R.id.btnok);

		// ����7������󶨼�����
		this.btnback1.setOnClickListener(new ViewOcl());
		this.btnback2.setOnClickListener(new ViewOcl());
		this.btnok.setOnClickListener(new ViewOcl());
	}

	// ����6������һ���Զ�����ڲ��������Linstener
	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnBack1:

				intent.setClass(Search2Activity.this, SelectActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnBack2:

				intent.setClass(Search2Activity.this, SearchActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnok:

				int row = Integer.parseInt(sid.getText().toString().trim());

				//int row=1;
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
				// System.out.println("account="+account.length());
				if (readwb != null) {
					try {

						jxl.write.WritableWorkbook wwb = Workbook
								.createWorkbook(new File(android.os.Environment
										.getExternalStorageDirectory()
										.getAbsolutePath()
										+ "/visitrecord.xls"), readwb);

						jxl.write.WritableSheet ws = wwb.getSheet(0);

						

						jxl.write.Label label1 = new jxl.write.Label(4, row,
								sbelong.getText().toString().trim());
						jxl.write.Label label2 = new jxl.write.Label(5, row,
								sknow.getText().toString().trim());
						jxl.write.Label label3 = new jxl.write.Label(6, row,
								sguwen.getText().toString().trim());
						jxl.write.Label label4 = new jxl.write.Label(9, row,
								sbeizhu.getText().toString().trim());
						
						jxl.write.Label label5 = new jxl.write.Label(2, row,
								sname.getText().toString().trim());

						jxl.write.Label label6 = new jxl.write.Label(3, row,
								sphone.getText().toString().trim());

						label5.setCellFormat(cellFormat);
						
						// ������õĵ�Ԫ����ӵ���������
						ws.addCell(label1);
						ws.addCell(label2);
						ws.addCell(label3);
						ws.addCell(label4);
						ws.addCell(label5);
						ws.addCell(label6);

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
					intent.setClass(Search2Activity.this, SelectActivity.class);
					startActivity(intent);
					finish();
					// Toast.makeText(getApplicationContext(), account.length(),
					// Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "�޸ĳ���",
							Toast.LENGTH_LONG).show();
				}
				break;
			default:
				break;
			}

		}
	}
}
