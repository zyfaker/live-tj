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
		 * * 识别引擎选择，目前支持以下五种 “sms”：普通文本转写 “poi”：地名搜索 “vsearch”：热词搜索
		 * “vsearch”：热词搜索 “video”：视频音乐搜索 “asr”：命令词识别
		 * 
		 * params 引擎参数配置列表
		 * 附加参数列表，每项中间以逗号分隔，如在地图搜索时可指定搜索区域：“area=安徽省合肥市”，无附加参数传null
		 */
		rd.setEngine("vsearch", null, null);

		// //设置采样频率，默认是16k，android手机一般只支持8k、16k.为了更好的识别，直接弄成16k即可。
		rd.setSampleRate(RATE.rate16k);

		final StringBuilder sb = new StringBuilder();
		Log.i(TAG, "识别准备开始.............");

		rd.setListener(new RecognizerDialogListener() {
			public void onResults(ArrayList<RecognizerResult> result,
					boolean isLast) {
				for (RecognizerResult recognizerResult : result) {
					sb.append(recognizerResult.text);
					// System.out.printf(recognizerResult.text);
					Log.i(TAG, "识别一条结果为···" + recognizerResult.text);
				}
			}

			public void onEnd(SpeechError error) {
				Log.i(TAG, "识别完成.............");
				stringlogin=sb.toString();
				// Toast.makeText(getApplicationContext(), sb.toString(),
				// Toast.LENGTH_LONG).show();
				Log.i(TAG, "识别完成:" + stringlogin);
			}
		});

		//selectname.setText(""); // 先设置为空，等识别完成后设置内容

		rd.show();
		return stringlogin;

	}

}
