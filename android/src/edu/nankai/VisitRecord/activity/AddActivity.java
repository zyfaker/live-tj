package edu.nankai.VisitRecord.activity;

import java.io.File;
import java.io.FileInputStream;

import com.chinasofti.diary.activity.R;

import jxl.Sheet;
import jxl.Workbook;
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

// ����1���̳и���Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class AddActivity extends Activity {
	
	// ����4��������ҳ���еĽ��������
	private EditText belong,know,guwen,beizhu;
	private Button btnFinish;

	// ����2����д�����е�һ������OnCreate()  Ctrl+Shift+S,V	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ��չ�����ظô���ı�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����3������������Ӧ�Ĳ����ļ����а�
		this.setContentView(R.layout.activity_main);
		// ����5��ʵ�������������
		this.belong = (EditText) this.findViewById(R.id.belong);
		this.beizhu = (EditText) this.findViewById(R.id.beizhu);
		this.know = (EditText) this.findViewById(R.id.know);
		this.guwen = (EditText) this.findViewById(R.id.guwen);
		this.btnFinish = (Button) this.findViewById(R.id.btnfinish);
		// ����7������󶨼�����
		this.btnFinish.setOnClickListener(new ViewOcl());
	}
	
	// ����6������һ���Զ�����ڲ��������Linstener
	private class ViewOcl implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				String belong1 = belong.getText().toString().trim();
				String know1 = know.getText().toString().trim();
				String guwen1 = guwen.getText().toString().trim();
				String beizhu1 = beizhu.getText().toString().trim();
				
				// ����1-2�������װ
				Users user = new Users();
				user.setBelong(belong1);
				user.setBeizhu(beizhu1);
				user.setGuwen(guwen1);
				user.setKnow(know1);
				
				if(user != null)
					{
						try {
							//����Workbook����, ֻ��Workbook����   
							  
				            //ֱ�Ӵӱ����ļ�����Workbook 
							Workbook readwb = null;
				  
				           //InputStream instream = new FileInputStream(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.xls");   
				  
				            readwb = Workbook.getWorkbook(new FileInputStream(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/visitrecord.xls"));   

				  
				            Sheet readsheet = readwb.getSheet(0);   
				  
				            int j = readsheet.getRows()-1;
				            System.out.println(readwb);
				            
				            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/visitrecord.xls"), readwb);   
				            
				            jxl.write.WritableSheet ws = wwb.getSheet(0); 

				            jxl.write.Label label1 = new jxl.write.Label(4, j, belong.getText().toString().trim()); 
				            jxl.write.Label label2 = new jxl.write.Label(5, j, know.getText().toString().trim());
				            jxl.write.Label label3 = new jxl.write.Label(6, j, guwen.getText().toString().trim());
				       
				            jxl.write.Label label4 = new jxl.write.Label(9, j, beizhu.getText().toString().trim());
				            
				            ws.addCell(label1);
				            ws.addCell(label2);
				            ws.addCell(label3);
				            ws.addCell(label4);
				            
				            
				            wwb.write();
				            wwb.close();
				            readwb.close();

				        } catch (Exception e) {
				            System.out.println(e);
				        }
						
						Intent intent = new Intent();
						intent.setClass(AddActivity.this,SelectActivity.class);
						startActivity(intent);
						Toast.makeText(getApplicationContext(), "������", Toast.LENGTH_LONG).show();
						//Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
					}
					
			}

		}

		
	}

