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

// 步骤1：继承父类Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class LoginActivity extends Activity {

	// 步骤4：声明该页面中的交互类组件
	private EditText userName, userPhone;
	private Button btnLogin;

	// 步骤2：重写父类中的一个方法OnCreate() Ctrl+Shift+S,V
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 由于访问互联网，因此将网络访问放入到子线程中进行
		super.onCreate(savedInstanceState);
		// 扩展：隐藏该窗体的标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 步骤3：将窗体与相应的布局文件进行绑定
		this.setContentView(R.layout.activity_login);
		// 步骤5：实例化操作类组件
		this.userName = (EditText) this.findViewById(R.id.userName);
		this.userPhone = (EditText) this.findViewById(R.id.userPhone);
		this.btnLogin = (Button) this.findViewById(R.id.btnLogin);
		// 步骤7：组件绑定监听器
		this.btnLogin.setOnClickListener(new ViewOcl());
	}

	// 步骤6：创建一个自定义的内部类监听器Linstener
	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String account = userName.getText().toString().trim();
			String phone = userPhone.getText().toString().trim();
			Date now = new Date();

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
					// 生成名为“第一页”的工作表，参数0表示这是第一页
					WritableSheet sheet = book.createSheet("到访记录", 0);
					// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
					// sheet.setRowView(0, 20);
					// 以及单元格内容为test
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

					Label label = new Label(0, 0, "序号");
					Label labe2 = new Label(1, 0, "日期");
					Label labe3 = new Label(2, 0, "姓名");
					Label labe4 = new Label(3, 0, "联系电话");

					Label labe5 = new Label(4, 0, "拓客组归属");

					Label labe6 = new Label(5, 0, "获知途径");
					Label labe7 = new Label(6, 0, "置业顾问");

					Label labe8 = new Label(7, 0, "认筹日期");
					Label labe9 = new Label(8, 0, "认购日期");

					Label labe10 = new Label(9, 0, "备注");

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

					// 将定义好的单元格添加到工作表中
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

			// 不允许输入重复电话
			Cell[] cells = readsheet.getColumn(3);
			boolean nologin = true;
			int j = readsheet.getRows();
			// System.out.println(j+cells[1].getContents()+cells[2].getContents());
			int i = 1;
			if (j > 1) {
				for (; i < j;) {
					String userphone = userPhone.getText().toString().trim();

					//System.out.println(j + cells[i].getContents());

					String cellphone = cells[i].getContents();
	
					if (userphone.equals(cellphone)) {
						// System.out.println(userPhone.getText().toString().trim());
						// System.out.println(phone.equals(cells[i].getContents()));
						nologin = false;
						break;
					} else {
						i++;
					}
				}
			}
			if (readwb != null && nologin && account.length() != 0
					&& !account.contains("女士") && !account.contains("先生")
					&& !account.contains("小姐") && phone.length() == 11) {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, AddActivity.class);
				intent.putExtra("account", account);
				intent.putExtra("phone", phone);
				intent.putExtra("date", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:").format(now));
//				Toast.makeText(getApplicationContext(),account+phone+new SimpleDateFormat(
//						"yyyy-MM-dd hh:mm:").format(now),
//						Toast.LENGTH_LONG).show();
				startActivity(intent);
				finish();
			} else if (phone.length() == 0 || account.length() == 0) {
				Toast.makeText(getApplicationContext(), "请输入必要信息",
						Toast.LENGTH_LONG).show();
			} else if (!nologin) {
				Toast.makeText(getApplicationContext(), "电话号码已经被使用",
						Toast.LENGTH_LONG).show();
			} else if (phone.length() == 11) {

				Toast.makeText(getApplicationContext(), "输入姓名不完整",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), "电话号码位数不对",
						Toast.LENGTH_LONG).show();
			}
		}
	}

}
