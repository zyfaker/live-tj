package edu.nankai.VisitRecord.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.chinasofti.diary.activity.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.nankai.VisitRecord.internet.WebAccessUtils;
import edu.nankai.VisitRecord.po.Client;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListReslutActivity extends Activity {

	// ����1���������϶����Լ�ListView�������
	private List<Map<String, ?>> lstData;
	private ListView lstMessages;
	private EditText nameprint;
	private Button btnprint,btnback;

	private String namelist, phonelist, datelist, belonglist, guwenlist,
			knowlist, beizhulist;
	List<Client> lstClient;

	// private Handler handler = new Handler();
	// private ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ����󶨲����ļ�
		this.setContentView(R.layout.main_list);
		// ����2��ʵ�����б���ͼ���
		this.lstMessages = (ListView) this.findViewById(R.id.listmain);

		this.nameprint = (EditText) this.findViewById(R.id.printname);

		this.btnprint = (Button) this.findViewById(R.id.btnprint);
		this.btnback = (Button) this.findViewById(R.id.btnbacksearch);
		

		// ����7������󶨼�����
		this.btnprint.setOnClickListener(new ViewOcl());
		
		this.btnback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentback = new Intent();
				intentback.setClass(ListReslutActivity.this,
						SelectMainActivity.class);
				startActivity(intentback);
			}


		});
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
		lstClient = gsonget.fromJson(response, ListClients);

		// ����4-6��ʹ��ѭ���������϶���
		if (lstClient.size() > 0) {
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
//			if(namelist==null){
//				Intent intentback = new Intent();
//				intentback.setClass(ListReslutActivity.this,
//						SelectMainActivity.class);
//				Toast.makeText(getApplicationContext(), "û�з��ϵļ�¼",
//						Toast.LENGTH_LONG).show();
//				startActivity(intent);
//				
			}
			}else {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("name", "û�з��ϵļ�¼");
				lst.add(item);
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

	private class ViewOcl implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			
			String filename = nameprint.getText().toString().trim();
			if (lstClient != null && filename != null) {
				Workbook readwb = null;
				WritableCellFormat cellFormat = new WritableCellFormat();
				try {
					cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
				} catch (WriteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String fileturename = "/" + filename + ".xls";
				System.out.println(fileturename+"----------------");
				try {
					WritableWorkbook book = Workbook.createWorkbook(new File(
							android.os.Environment
									.getExternalStorageDirectory()
									.getAbsolutePath()
									+ fileturename));
					// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
					WritableSheet sheet = book.createSheet(filename, 0);
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

				// ��Ӳ���
				for (Client client : lstClient) {
					try {
						readwb = Workbook.getWorkbook(new FileInputStream(
								android.os.Environment
										.getExternalStorageDirectory()
										.getAbsolutePath()
										+ fileturename));
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

					int j = readsheet.getRows();
					try {

						jxl.write.WritableWorkbook wwb = Workbook
								.createWorkbook(new File(android.os.Environment
										.getExternalStorageDirectory()
										.getAbsolutePath()
										+ fileturename), readwb);

						jxl.write.WritableSheet ws = wwb.getSheet(0);

						jxl.write.Label addlabel = new jxl.write.Label(0, j,
								Integer.toString(j));
						jxl.write.Label addlabel2 = new jxl.write.Label(1, j,
								client.getDate());
						jxl.write.Label addlabel3 = new jxl.write.Label(2, j,
								client.getName());
						jxl.write.Label addlabel4 = new jxl.write.Label(3, j,
								client.getPhone());
						jxl.write.Label addlabel5 = new jxl.write.Label(4, j,
								client.getTeambelong());
						jxl.write.Label addlabel6 = new jxl.write.Label(5, j,
								client.getKownway());
						jxl.write.Label addlabel7 = new jxl.write.Label(6, j,
								client.getCounselor());
						jxl.write.Label addlabel8 = new jxl.write.Label(9, j,
								client.getRemark());

						addlabel.setCellFormat(cellFormat);
						addlabel3.setCellFormat(cellFormat);
						// ������õĵ�Ԫ����ӵ���������
						ws.addCell(addlabel);
						ws.addCell(addlabel2);
						ws.addCell(addlabel3);
						ws.addCell(addlabel4);
						ws.addCell(addlabel5);
						ws.addCell(addlabel6);
						ws.addCell(addlabel7);
						ws.addCell(addlabel8);

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
				}
			} else if (lstClient == null) {
				Toast.makeText(getApplicationContext(), "��¼Ϊ��",
						Toast.LENGTH_LONG).show();

			} else if (filename == null && lstClient != null) {
				Toast.makeText(getApplicationContext(), "���������ļ���",
						Toast.LENGTH_LONG).show();

			}

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
