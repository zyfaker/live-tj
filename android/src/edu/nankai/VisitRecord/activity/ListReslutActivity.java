package edu.nankai.VisitRecord.activity;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.chinasofti.diary.activity.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.nankai.VisitRecord.internet.WebAccessUtils;
import edu.nankai.VisitRecord.po.Client;
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
	private String namelist, phonelist, datelist, belonglist, guwenlist,
			knowlist, beizhulist;
//	private Handler handler = new Handler();
//    private ProgressDialog progressDialog = null;

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
		// this.lstMessages.setOnItemLongClickListener(new ItemLongOcl());
	}

	// ����4���Զ���һ����ȡ�б����ݵķ���
	private List<Map<String, ?>> fetchData() {
		// TODO Auto-generated method stub
		// ����4-1������һ���ռ��϶���
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();

		Intent intent = getIntent();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm")
				.create();
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		System.out.println("--------");
		String response = "";
		if (intent.getStringExtra("selectname1") != null) {
			String clientname = gson.toJson(intent
					.getStringExtra("selectname1"));
			lstNameValuePairs.add(new BasicNameValuePair("clientName",
					clientname));
			System.out.println(intent.getStringExtra("selectname1"));
			// ����4-2�����÷���ʵ�ֶ�������������
			response = WebAccessUtils.httpRequest("SelectByNameServlet",
					lstNameValuePairs);
		} else if (intent.getStringExtra("selectdate") != null) {
			String selectdate = gson
					.toJson(intent.getStringExtra("selectdate"));
			System.out.println(intent.getStringExtra("selectdate"));
			lstNameValuePairs.add(new BasicNameValuePair("date", selectdate));
			// ����4-2�����÷���ʵ�ֶ�������������
			response = WebAccessUtils.httpRequest("SelectByDateServlet",
					lstNameValuePairs);
		} else if (intent.getStringExtra("selectToday") != null) {
			String selectdate = gson.toJson(intent
					.getStringExtra("selectToday"));
			lstNameValuePairs.add(new BasicNameValuePair("date", selectdate));
			// ����4-2�����÷���ʵ�ֶ�������������
			response = WebAccessUtils.httpRequest("SelectByDateServlet",
					lstNameValuePairs);
		}
		
		Gson gsonget = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm")
				.create();
		


		// ����4-3������һ��ȫ�µ�����Type
		Type ListClients = new TypeToken<ArrayList<Client>>() {
		}.getType();

		// System.out.println(intent.getStringExtra(response));
		// ����4-5������JSon����
		List<Client> lstClient = gsonget.fromJson(response, ListClients);

		// ����4-6��ʹ��ѭ���������϶���
		if (lstClient != null) {
			for (Client client : lstClient) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("mid", client.getId());
				item.put("name", client.getName());
				item.put("date", client.getDate());
				// ����4-7���������õ�ѡ�������ӵ�������

				namelist = client.getName();
				phonelist = client.getPhone();
				datelist = client.getDate();
				belonglist = client.getTeambelong();
				beizhulist = client.getRemark();
				guwenlist = client.getCounselor();
				knowlist = client.getKownway();
				phonelist = client.getPhone();

				lst.add(item);
			}
	
		} else {
			Intent intentback = new Intent();
			intentback.setClass(ListReslutActivity.this,
					SelectMainActivity.class);
			Toast.makeText(getApplicationContext(), "û�з��ϵļ�¼",
					Toast.LENGTH_LONG).show();
			startActivity(intent);

		}
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
			intent.putExtra("name", namelist);
			intent.putExtra("phone", phonelist);
			intent.putExtra("date", datelist);
			intent.putExtra("belonglist", belonglist);
			intent.putExtra("beizhulist", beizhulist);
			intent.putExtra("guwenlist", guwenlist);
			intent.putExtra("knowlist", knowlist);

			startActivity(intent);
		}

	}

	// // ����7���Զ����б�ѡ�����¼�����
	// private class ItemLongOcl implements AdapterView.OnItemLongClickListener
	// {
	//
	// @Override
	// public boolean onItemLongClick(AdapterView<?> adapter, View view,
	// int position, long arg3) {
	// // TODO Auto-generated method stub
	// // ����7-1��ʹ�ø÷�����position������ȡѡ�е�ѡ����󲢸�ֵ��Map������
	// Map<String, ?> selectedItem = lstData.get(position);
	// // ����
	// Toast.makeText(getApplicationContext(),
	// "�ſ��ң�" + selectedItem.get("mid"), Toast.LENGTH_LONG).show();
	// return true;
	// }
	//
	// }

}
