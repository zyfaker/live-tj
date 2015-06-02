package edu.nankai.VisitRecord.utils;

import java.util.ArrayList;

import android.util.Log;

import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

public class VedioLoginTool {
	private String stringlogin = "";
	protected static final String TAG = "ThirdActivity";
	
	
	public String showReconigizerDialog(RecognizerDialog rd) {
		
		// setEngine(String engine,String params,String grammar);
		/**
		 * * ʶ������ѡ��Ŀǰ֧���������� ��sms������ͨ�ı�תд ��poi������������ ��vsearch�����ȴ�����
		 * ��vsearch�����ȴ����� ��video������Ƶ�������� ��asr���������ʶ��
		 * 
		 * params ������������б�
		 * ���Ӳ����б�ÿ���м��Զ��ŷָ������ڵ�ͼ����ʱ��ָ���������򣺡�area=����ʡ�Ϸ��С����޸��Ӳ�����null
		 */
		rd.setEngine("vsearch", null, null);

		// //���ò���Ƶ�ʣ�Ĭ����16k��android�ֻ�һ��ֻ֧��8k��16k.Ϊ�˸��õ�ʶ��ֱ��Ū��16k���ɡ�
		rd.setSampleRate(RATE.rate16k);

		final StringBuilder sb = new StringBuilder();
		Log.i(TAG, "ʶ��׼����ʼ.............");

		rd.setListener(new RecognizerDialogListener() {
			public void onResults(ArrayList<RecognizerResult> result,
					boolean isLast) {
				for (RecognizerResult recognizerResult : result) {
					sb.append(recognizerResult.text);
					// System.out.printf(recognizerResult.text);
					Log.i(TAG, "ʶ��һ�����Ϊ������" + recognizerResult.text);
				}
			}

			public void onEnd(SpeechError error) {
				Log.i(TAG, "ʶ�����.............");
				stringlogin=sb.toString();
				// Toast.makeText(getApplicationContext(), sb.toString(),
				// Toast.LENGTH_LONG).show();
				Log.i(TAG, "ʶ�����:" + stringlogin);
			}
		});

		//selectname.setText(""); // ������Ϊ�գ���ʶ����ɺ���������

		rd.show();
		return stringlogin;

	}

}
