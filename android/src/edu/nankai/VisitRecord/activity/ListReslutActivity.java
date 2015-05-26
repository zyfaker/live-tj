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

	// ����1���������϶����Լ�ListView�������
	private List<Map<String, ?>> lstData;
	private ListView lstMessages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ����󶨲����ļ�
		this.setContentView(R.layout.main_list);
		// ����2��ʵ�����б���ͼ���
		this.lstMessages = (ListView) this.findViewById(R.id.listmain);
		// ����3����ȡ�Զ����б�����е�����
		this.lstData = fetchData();
		// ����4�����Զ���Ĳ������ȡ�����б����ݽ��а�
		SimpleAdapter adapter = new SimpleAdapter(this, this.lstData,
				R.layout.list_reslut, new String[] { "name", "date" },
				new int[] { R.id.cusname, R.id.comedate });
		// ����5���б������������
		this.lstMessages.setAdapter(adapter);
		// ����7�����б���ͼ������¼���������
		this.lstMessages.setOnItemClickListener(new ItemOcl());
		this.lstMessages.setOnItemLongClickListener(new ItemLongOcl());
	}
	 // ����4���Զ���һ����ȡ�б����ݵķ���
	 private List<Map<String, ?>> fetchData() {
	 // TODO Auto-generated method stub
	 // ����4-1������һ���ռ��϶���
	 List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
	 // ����4-2������һ���б���ѡ�����ʵ����
	 Map<String, Object> item01 = new HashMap<String, Object>();
	 item01.put("mid", 1);
	 item01.put("name", "����");
	 item01.put("date", "2014-08-21 10:08");
	
	 Map<String, Object> item02 = new HashMap<String, Object>();
	 item02.put("mid", 2);
	 item02.put("name", "����");
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
			// ����6-1��ʹ�ø÷�����position������ȡѡ�е�ѡ����󲢸�ֵ��Map������
			Map<String, ?> selectedItem = lstData.get(position);
			// ����
			
			Toast.makeText(getApplicationContext(),
					"��ѡ�е��Ǳ��Ϊ:" + selectedItem.get("mid"), Toast.LENGTH_LONG)
					.show();
			Intent intent = new Intent();
			intent.setClass(ListReslutActivity.this, Search2Activity.class);
			intent.putExtra("idnumber", selectedItem.get("mid").toString());
			startActivity(intent);
		}

	}

	// ����7���Զ����б�ѡ�����¼�����
	private class ItemLongOcl implements AdapterView.OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			// TODO Auto-generated method stub
			// ����7-1��ʹ�ø÷�����position������ȡѡ�е�ѡ����󲢸�ֵ��Map������
			Map<String, ?> selectedItem = lstData.get(position);
			// ����
			Toast.makeText(getApplicationContext(),
					"�ſ��ң�" + selectedItem.get("mid"), Toast.LENGTH_LONG).show();
			return true;
		}

	}

}
