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
		// 扩展：隐藏该窗体的标题栏
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.setContentView(R.layout.activity_more);
	    
	}

}
