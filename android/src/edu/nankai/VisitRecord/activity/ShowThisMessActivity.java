package edu.nankai.VisitRecord.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import com.chinasofti.diary.activity.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.nankai.VisitRecord.internet.WebAccessUtils;
import edu.nankai.VisitRecord.po.Client;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowThisMessActivity extends Activity {
	private TextView sidthis, sdatethis, snamethis, sphonethis, sbelongthis,
			sknowthis, sguwenthis, sbeizhuthis;

	private Button btnbackhead, btnbacklogin, btnperfect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����3������������Ӧ�Ĳ����ļ����а�
		this.setContentView(R.layout.showthisadd_activity);

		this.sidthis = (TextView) this.findViewById(R.id.sidthis);
		this.sdatethis = (TextView) this.findViewById(R.id.sdatethis);
		this.snamethis = (TextView) this.findViewById(R.id.snamethis);
		this.sphonethis = (TextView) this.findViewById(R.id.sphonethis);
		this.sbelongthis = (TextView) this.findViewById(R.id.sbelongthis);
		this.sknowthis = (TextView) this.findViewById(R.id.sknowthis);
		this.sguwenthis = (TextView) this.findViewById(R.id.sguwenthis);
		this.sbeizhuthis = (TextView) this.findViewById(R.id.sbeizhuthis);

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
		this.btnbacklogin = (Button) this.findViewById(R.id.btnBacklogin);
		this.btnperfect = (Button) this.findViewById(R.id.btnperfect);

		// ����7������󶨼�����
		this.btnbackhead.setOnClickListener(new ViewOcl());
		this.btnbacklogin.setOnClickListener(new ViewOcl());
		this.btnperfect.setOnClickListener(new ViewOcl());
	}

	private class ViewOcl implements View.OnClickListener {
		@SuppressLint("SimpleDateFormat")
		@SuppressWarnings("unused")
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnBackhead:

				intent.setClass(ShowThisMessActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnBacklogin:

				intent.setClass(ShowThisMessActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnperfect:

				// ����1����ȡ���ݲ���װ���󣬽�����������л���JSON��
				// ����1-1����ȡ�˺ź�����
				String date = sdatethis.getText().toString().trim();
				String name = snamethis.getText().toString().trim();
				String phone = sphonethis.getText().toString().trim();
				String belong = sbelongthis.getText().toString().trim();
				String guwen = sguwenthis.getText().toString().trim();
				String know = sknowthis.getText().toString().trim();
				String beizhu = sbeizhuthis.getText().toString().trim();
				// ����1-2�������װ

				Client client = new Client();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				Date d = new Date();
				try {
					d = sdf.parse(date);
					System.out.println(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				client.setDate(d);
				client.setName(name);
				client.setPhone(phone);
				client.setCounselor(guwen);
				client.setKownway(know);
				client.setRemark(beizhu);
				client.setTeambelong(belong);
				// ����1-3�����л�
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm")
						.create();
				String client_data = gson.toJson(client);

				// ����2����������������ϲ����÷��������緢����������

				// ����2-1������һ����������
				List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
				lstNameValuePairs.add(new BasicNameValuePair("client_data",
						client_data));

				// ����2-2�����÷�ʽʵ������ķ���
				String response = WebAccessUtils.httpRequest("AddServlet",
						lstNameValuePairs);

				// ����3������JSON����
				// ����3-1�������л����ݷ�װ��һ������
				// ����4-3������һ��ȫ�µ�����Type
				Type Result = new TypeToken<Boolean>() {
				}.getType();

				// ����4-5������JSon����
				boolean res = gson.fromJson(response, Result);
				if (res) {
					// ���ݼ��뱾��Excel��
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
									.createWorkbook(
											new File(
													android.os.Environment
															.getExternalStorageDirectory()
															.getAbsolutePath()
															+ "/visitrecord.xls"),
											readwb);

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
							// ������õĵ�Ԫ����ӵ���������
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

					} else {
						Toast.makeText(getApplicationContext(), "���������",
								Toast.LENGTH_LONG).show();
					}
					Toast.makeText(getApplicationContext(), "�����ѳɹ���ӵ�������!",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "��ӵ�����������!",
							Toast.LENGTH_LONG).show();
				}
				intent.setClass(ShowThisMessActivity.this, MainActivity.class);
				startActivity(intent);
				finish();

				break;
			default:
				break;
			}

		}
	}
}
