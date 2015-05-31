package edu.nankai.VisitRecord.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.chinasofti.diary.activity.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.nankai.VisitRecord.internet.WebAccessUtils;
import edu.nankai.VisitRecord.po.Client;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// ����1���̳и���Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Search2Activity extends Activity {

	// ����4��������ҳ���еĽ��������
	private TextView sid, sdate;
	private EditText sphone, sbelong, sknow, sguwen, sbeizhu, sname;
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

		this.sid = (TextView) this.findViewById(R.id.sid);
		this.sdate = (TextView) this.findViewById(R.id.sdate);
		this.sname = (EditText) this.findViewById(R.id.sname);
		this.sphone = (EditText) this.findViewById(R.id.sphone);
		this.sbelong = (EditText) this.findViewById(R.id.sbelong);
		this.sknow = (EditText) this.findViewById(R.id.sknow);
		this.sguwen = (EditText) this.findViewById(R.id.sguwen);
		this.sbeizhu = (EditText) this.findViewById(R.id.sbeizhu);

		Intent intent = getIntent();

		sid.setText(intent.getStringExtra("idnumber"));
		sdate.setText(intent.getStringExtra("date"));
		sname.setText(intent.getStringExtra("name"));
		sphone.setText(intent.getStringExtra("phonelist"));
		sbelong.setText(intent.getStringExtra("belonglist"));
		sknow.setText(intent.getStringExtra("knowlist"));
		sguwen.setText(intent.getStringExtra("guwenlist"));
		sbeizhu.setText(intent.getStringExtra("beizhulist"));

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
		@SuppressLint("SimpleDateFormat")
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.btnBack1:

				intent.setClass(Search2Activity.this, MainActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnBack2:

				intent.setClass(Search2Activity.this, SelectMainActivity.class);
				startActivity(intent);
				finish();
				break;

			case R.id.btnok:

				String id = sid.getText().toString().trim();
				String date = sdate.getText().toString().trim();
				String name = sname.getText().toString().trim();
				String phone = sphone.getText().toString().trim();
				String belong = sbelong.getText().toString().trim();
				String guwen = sguwen.getText().toString().trim();
				String know = sknow.getText().toString().trim();
				String beizhu = sbeizhu.getText().toString().trim();
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

				client.setId(Integer.parseInt(id));
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
				String response = WebAccessUtils.httpRequest("UpdateServlet",
						lstNameValuePairs);

				// ����3������JSON����
				// ����3-1�������л����ݷ�װ��һ������
				// ����4-3������һ��ȫ�µ�����Type
				Type Result = new TypeToken<Boolean>() {
				}.getType();

				// ����4-5������JSon����
				boolean res = gson.fromJson(response, Result);
				if (res) {
					Toast.makeText(getApplicationContext(), "���������ѳɹ���ӵ�������!",
							Toast.LENGTH_LONG).show();

					Workbook readwb = null;
					WritableCellFormat cellFormat = new WritableCellFormat();
					try {
						cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
					} catch (WriteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						@SuppressWarnings({ "unused", "resource" })
						InputStream instream = new FileInputStream(
								android.os.Environment
										.getExternalStorageDirectory()
										.getAbsolutePath()
										+ "/visitrecord.xls");
					} catch (FileNotFoundException e) {
						try {
							WritableWorkbook book = Workbook
									.createWorkbook(new File(
											android.os.Environment
													.getExternalStorageDirectory()
													.getAbsolutePath()
													+ "/visitrecord.xls"));
							// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
							WritableSheet sheet = book.createSheet("���ü�¼", 0);
							// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
							// sheet.setRowView(0, 20);
							// �Լ���Ԫ������Ϊtest
							sheet.setColumnView(0, 5);
							sheet.setColumnView(1, 20);
							sheet.setColumnView(2, 10);
							sheet.setColumnView(3, 20);

							sheet.setColumnView(4, 30);
							sheet.setColumnView(5, 30);
							sheet.setColumnView(6, 20);

							sheet.setColumnView(7, 20);
							sheet.setColumnView(8, 20);

							sheet.setColumnView(9, 30);

							Label label = new Label(0, 0, "���");
							Label labe2 = new Label(1, 0, "����");
							Label labe3 = new Label(2, 0, "����");
							Label labe4 = new Label(3, 0, "��ϵ�绰");

							Label labe5 = new Label(4, 0, "�ؿ������");

							Label labe6 = new Label(5, 0, "��֪;��");
							Label labe7 = new Label(6, 0, "��ҵ����");

							Label labe8 = new Label(7, 0, "�ϳ�����");
							Label labe9 = new Label(8, 0, "�Ϲ�����");

							Label labe10 = new Label(9, 0, "��ע");

							label.setCellFormat(cellFormat);
							labe2.setCellFormat(cellFormat);
							labe3.setCellFormat(cellFormat);
							labe4.setCellFormat(cellFormat);

							labe5.setCellFormat(cellFormat);
							labe6.setCellFormat(cellFormat);
							labe7.setCellFormat(cellFormat);
							labe8.setCellFormat(cellFormat);
							labe9.setCellFormat(cellFormat);
							labe10.setCellFormat(cellFormat);

							// ������õĵ�Ԫ����ӵ���������
							sheet.addCell(label);
							sheet.addCell(labe2);
							sheet.addCell(labe3);
							sheet.addCell(labe4);
							sheet.addCell(labe5);
							sheet.addCell(labe6);
							sheet.addCell(labe7);
							sheet.addCell(labe8);
							sheet.addCell(labe9);
							sheet.addCell(labe10);

							// Toast.makeText(getApplicationContext(),
							// user.toString(),
							// Toast.LENGTH_LONG).show();

							book.write();
							book.close();
						} catch (RowsExceededException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (WriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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

					// ���β�ѯ�Ƿ�Ϊ���ؿͻ�
					Cell[] cells = readsheet.getColumn(2);
					boolean hastthisname = false;
					int j = readsheet.getRows();
					int row = 0;
					// System.out.println(j+cells[1].getContents()+cells[2].getContents());
					int i = 1;
					if (j > 1) {
						for (; i < j;) {
							String username = sname.getText().toString().trim();

							// System.out.println(j + cells[i].getContents());

							String cellname = cells[i].getContents();

							if (username.equals(cellname)) {
								// System.out.println(userPhone.getText().toString().trim());
								// System.out.println(phone.equals(cells[i].getContents()));
								hastthisname = true;
								row = i;
								break;
							} else {
								i++;
							}
						}
					}
					if (hastthisname) {
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

							jxl.write.Label label1 = new jxl.write.Label(4,
									row, sbelong.getText().toString().trim());
							jxl.write.Label label2 = new jxl.write.Label(5,
									row, sknow.getText().toString().trim());
							jxl.write.Label label3 = new jxl.write.Label(6,
									row, sguwen.getText().toString().trim());
							jxl.write.Label label4 = new jxl.write.Label(9,
									row, sbeizhu.getText().toString().trim());

							jxl.write.Label label5 = new jxl.write.Label(2,
									row, sname.getText().toString().trim());

							jxl.write.Label label6 = new jxl.write.Label(3,
									row, sphone.getText().toString().trim());

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
						Toast.makeText(getApplicationContext(), "�Ѿ��ɹ��޸ı�������!",
								Toast.LENGTH_LONG).show();

					}

				} else {
					Toast.makeText(getApplicationContext(), "��ӵ�����������!",
							Toast.LENGTH_LONG).show();
				}
				intent.setClass(Search2Activity.this, MainActivity.class);
				startActivity(intent);
				finish();

				break;
			default:
				break;
			}

		}
	}
}
