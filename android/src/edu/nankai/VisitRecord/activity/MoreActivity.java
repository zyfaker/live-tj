package edu.nankai.VisitRecord.activity;

import com.chinasofti.diary.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MoreActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ��չ�����ظô���ı�����
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.setContentView(R.layout.activity_more);
	    
	}

}
