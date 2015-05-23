package edu.nankai.VisitRecord.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.chinasofti.diary.activity.R;

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

// ����1���̳и���Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class LoginActivity extends Activity {

	// ����4��������ҳ���еĽ��������
	private EditText userName, userPhone;
	private Button btnLogin;

	// ����2����д�����е�һ������OnCreate() Ctrl+Shift+S,V
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// ���ڷ��ʻ���������˽�������ʷ��뵽���߳��н���
		super.onCreate(savedInstanceState);
		// ��չ�����ظô���ı�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����3������������Ӧ�Ĳ����ļ����а�
		this.setContentView(R.layout.activity_login);
		// ����5��ʵ�������������
		this.userName = (EditText) this.findViewById(R.id.userName);
		this.userPhone = (EditText) this.findViewById(R.id.userPhone);
		this.btnLogin = (Button) this.findViewById(R.id.btnLogin);
		// ����7������󶨼�����
		this.btnLogin.setOnClickListener(new ViewOcl());
	}

	// ����6������һ���Զ�����ڲ��������Linstener
	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String account = userName.getText().toString().trim();
			String phone = userPhone.getText().toString().trim();
			Date now = new Date();
			// ����1-2�������װ
			Users user = new Users();
			user.setName(account);
			user.setPhone(phone);
			user.setDate(now);
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
						android.os.Environment.getExternalStorageDirectory()
								.getAbsolutePath() + "/visitrecord.xls");
			} catch (FileNotFoundException e) {
				try {
					WritableWorkbook book = Workbook.createWorkbook(new File(
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

					// Toast.makeText(getApplicationContext(), user.toString(),
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
						android.os.Environment.getExternalStorageDirectory()
								.getAbsolutePath() + "/visitrecord.xls"));
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

			// �����������ظ��绰
			Cell[] cells = readsheet.getColumn(3);
			boolean nologin = true;
			int j = readsheet.getRows();
			//System.out.println(j+cells[1].getContents()+cells[2].getContents());
			int i = 1;
			for (; i < j;) {
				String userphone = userPhone.getText().toString().trim();
				
				System.out.println(j+cells[i].getContents());
				
				String cellphone = cells[i].getContents();
				System.out.println(cellphone);
				
				if (userphone.equals(cellphone)) {
					//System.out.println(userPhone.getText().toString().trim());
					//System.out.println(phone.equals(cells[i].getContents()));
					nologin = false;
					break;
				} else {
					i++;
				}
			}
			//System.out.println(nologin);
			// System.out.println("account="+account.length());
			if (readwb != null && nologin && account.length() != 0
					&& user != null && !account.contains("Ůʿ")
					&& !account.contains("����") && !account.contains("С��")
					&& phone.length() == 11) {
				try {

					jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(
							new File(android.os.Environment
									.getExternalStorageDirectory()
									.getAbsolutePath()
									+ "/visitrecord.xls"), readwb);

					jxl.write.WritableSheet ws = wwb.getSheet(0);

					jxl.write.Label label = new jxl.write.Label(0, j,
							Integer.toString(j));
					jxl.write.Label label2 = new jxl.write.Label(1, j,
							new SimpleDateFormat("yyyy-MM-dd hh:mm:")
									.format(user.getDate()));
					jxl.write.Label label3 = new jxl.write.Label(2, j, userName
							.getText().toString().trim());
					jxl.write.Label label4 = new jxl.write.Label(3, j,
							userPhone.getText().toString().trim());

					label.setCellFormat(cellFormat);
					label3.setCellFormat(cellFormat);
					// ������õĵ�Ԫ����ӵ���������
					ws.addCell(label);
					ws.addCell(label2);
					ws.addCell(label3);
					ws.addCell(label4);

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
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, AddActivity.class);
				startActivity(intent);
				// Toast.makeText(getApplicationContext(), account.length(),
				// Toast.LENGTH_LONG).show();
			} else if (phone.length() == 0 || account.length() == 0) {
				Toast.makeText(getApplicationContext(), "�������Ҫ��Ϣ",
						Toast.LENGTH_LONG).show();
			} else if (!nologin) {
				Toast.makeText(getApplicationContext(), "�绰�����Ѿ���ʹ��",
						Toast.LENGTH_LONG).show();
			} else if (phone.length() == 11) {

				Toast.makeText(getApplicationContext(), "��������������",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), "�绰����λ������",
						Toast.LENGTH_LONG).show();
			}
		}
	}

}
