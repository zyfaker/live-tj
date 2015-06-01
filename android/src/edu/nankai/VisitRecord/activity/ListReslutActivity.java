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

	// 步骤1：声明集合对象以及ListView组件对象
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
		// this.lstMessages.setOnItemLongClickListener(new ItemLongOcl());
	}

	// 步骤4：自定义一个获取列表数据的方法
	private List<Map<String, ?>> fetchData() {
		// TODO Auto-generated method stub
		// 步骤4-1：创建一个空集合对象
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
			// 步骤4-2：调用方法实现对网络服务的请求
			response = WebAccessUtils.httpRequest("SelectByNameServlet",
					lstNameValuePairs);
		} else if (intent.getStringExtra("selectdate") != null) {
			String selectdate = gson
					.toJson(intent.getStringExtra("selectdate"));
			System.out.println(intent.getStringExtra("selectdate"));
			lstNameValuePairs.add(new BasicNameValuePair("date", selectdate));
			// 步骤4-2：调用方法实现对网络服务的请求
			response = WebAccessUtils.httpRequest("SelectByDateServlet",
					lstNameValuePairs);
		} else if (intent.getStringExtra("selectToday") != null) {
			String selectdate = gson.toJson(intent
					.getStringExtra("selectToday"));
			lstNameValuePairs.add(new BasicNameValuePair("date", selectdate));
			// 步骤4-2：调用方法实现对网络服务的请求
			response = WebAccessUtils.httpRequest("SelectByDateServlet",
					lstNameValuePairs);
		}
		
		Gson gsonget = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm")
				.create();
		


		// 步骤4-3：设置一个全新的类型Type
		Type ListClients = new TypeToken<ArrayList<Client>>() {
		}.getType();

		// System.out.println(intent.getStringExtra(response));
		// 步骤4-5：解析JSon数据
		List<Client> lstClient = gsonget.fromJson(response, ListClients);

		// 步骤4-6：使用循环遍历集合对象
		if (lstClient != null) {
			for (Client client : lstClient) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("mid", client.getId());
				item.put("name", client.getName());
				item.put("date", client.getDate());
				// 步骤4-7：将创建好的选项对象添加到集合中

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
			Toast.makeText(getApplicationContext(), "没有符合的记录",
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
			// 步骤6-1：使用该方法的position参数获取选中的选项对象并赋值到Map集合中
			Map<String, ?> selectedItem = lstData.get(position);
			// 测试

			Toast.makeText(getApplicationContext(),
					"您选中的是编号为:" + selectedItem.get("mid"), Toast.LENGTH_LONG)
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

	// // 步骤7：自定义列表选项长点击事件处理
	// private class ItemLongOcl implements AdapterView.OnItemLongClickListener
	// {
	//
	// @Override
	// public boolean onItemLongClick(AdapterView<?> adapter, View view,
	// int position, long arg3) {
	// // TODO Auto-generated method stub
	// // 步骤7-1：使用该方法的position参数获取选中的选项对象并赋值到Map集合中
	// Map<String, ?> selectedItem = lstData.get(position);
	// // 测试
	// Toast.makeText(getApplicationContext(),
	// "放开我：" + selectedItem.get("mid"), Toast.LENGTH_LONG).show();
	// return true;
	// }
	//
	// }

}
