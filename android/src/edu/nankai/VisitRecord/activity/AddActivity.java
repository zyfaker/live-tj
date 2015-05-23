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

// 步骤1：继承父类Android.app.Activity
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class AddActivity extends Activity {
	
	// 步骤4：声明该页面中的交互类组件
	private EditText belong,know,guwen,beizhu;
	private Button btnFinish;

	// 步骤2：重写父类中的一个方法OnCreate()  Ctrl+Shift+S,V	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 扩展：隐藏该窗体的标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 步骤3：将窗体与相应的布局文件进行绑定
		this.setContentView(R.layout.activity_main);
		// 步骤5：实例化操作类组件
		this.belong = (EditText) this.findViewById(R.id.belong);
		this.beizhu = (EditText) this.findViewById(R.id.beizhu);
		this.know = (EditText) this.findViewById(R.id.know);
		this.guwen = (EditText) this.findViewById(R.id.guwen);
		this.btnFinish = (Button) this.findViewById(R.id.btnfinish);
		// 步骤7：组件绑定监听器
		this.btnFinish.setOnClickListener(new ViewOcl());
	}
	
	// 步骤6：创建一个自定义的内部类监听器Linstener
	private class ViewOcl implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				String belong1 = belong.getText().toString().trim();
				String know1 = know.getText().toString().trim();
				String guwen1 = guwen.getText().toString().trim();
				String beizhu1 = beizhu.getText().toString().trim();
				
				// 步骤1-2：对象封装
				Users user = new Users();
				user.setBelong(belong1);
				user.setBeizhu(beizhu1);
				user.setGuwen(guwen1);
				user.setKnow(know1);
				
				if(user != null)
					{
						try {
							//构建Workbook对象, 只读Workbook对象   
							  
				            //直接从本地文件创建Workbook 
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
						Toast.makeText(getApplicationContext(), "添加完成", Toast.LENGTH_LONG).show();
						//Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
					}
					
			}

		}

		
	}

