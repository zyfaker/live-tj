package edu.nankai.VisitRecord.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinasofti.diary.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListReslutActivity extends Activity {

	// 步骤1：声明集合对象以及ListView组件对象
	private List<Map<String, ?>> lstData;
	private ListView lstMessages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 窗体绑定布局文件
		this.setContentView(R.layout.main_list);
		// 步骤2：实例化列表视图组件
		this.lstMessages = (ListView) this.findViewById(R.id.listmain);
		// 步骤3：获取自定义列表组件中的数据
		this.lstData = fetchData();
		// 步骤4：将自定义的布局与获取到的列表数据进行绑定
		SimpleAdapter adapter = new SimpleAdapter(this, this.lstData,
				R.layout.list_reslut, new String[] { "name", "date" },
				new int[] { R.id.cusname, R.id.comedate });
		// 步骤5：列表组件绑定适配器
		this.lstMessages.setAdapter(adapter);
		// 步骤7：将列表视图组件与事件监听器绑定
		this.lstMessages.setOnItemClickListener(new ItemOcl());
		this.lstMessages.setOnItemLongClickListener(new ItemLongOcl());
	}
	 // 步骤4：自定义一个获取列表数据的方法
	 private List<Map<String, ?>> fetchData() {
	 // TODO Auto-generated method stub
	 // 步骤4-1：创建一个空集合对象
	 List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
	 // 步骤4-2：创建一个列表中选项对象并实例化
	 Map<String, Object> item01 = new HashMap<String, Object>();
	 item01.put("mid", 1);
	 item01.put("name", "张三");
	 item01.put("date", "2014-08-21 10:08");
	
	 Map<String, Object> item02 = new HashMap<String, Object>();
	 item02.put("mid", 2);
	 item02.put("name", "李四");
	 item02.put("date", "2014-08-21 10:09");
	 lst.add(item01);
	 lst.add(item02);
	 return lst;
	 }
	

	private class ItemOcl implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			// TODO Auto-generated method stub
			// 步骤6-1：使用该方法的position参数获取选中的选项对象并赋值到Map集合中
			Map<String, ?> selectedItem = lstData.get(position);
			// 测试
			
			Toast.makeText(getApplicationContext(),
					"您选中的是编号为:" + selectedItem.get("mid"), Toast.LENGTH_LONG)
					.show();
			Intent intent = new Intent();
			intent.setClass(ListReslutActivity.this, Search2Activity.class);
			intent.putExtra("idnumber", selectedItem.get("mid").toString());
			startActivity(intent);
		}

	}

	// 步骤7：自定义列表选项长点击事件处理
	private class ItemLongOcl implements AdapterView.OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			// TODO Auto-generated method stub
			// 步骤7-1：使用该方法的position参数获取选中的选项对象并赋值到Map集合中
			Map<String, ?> selectedItem = lstData.get(position);
			// 测试
			Toast.makeText(getApplicationContext(),
					"放开我：" + selectedItem.get("mid"), Toast.LENGTH_LONG).show();
			return true;
		}

	}

}
