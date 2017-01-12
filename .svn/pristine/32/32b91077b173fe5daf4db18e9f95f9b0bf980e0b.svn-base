package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.util.CommonUtil;
import com.util.HttpClientUtil;



public class Main {
	
	public static void main(String[] args) {
		System.out.println(CommonUtil.getDateDayTime());
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("imgUrl", "http://lftdb.52fdw.com:81/LFT-ResPlatform/15/403b9a6067ad48c9856032f2f9c18c86.png"));
		String ocrResult;
		try {
			ocrResult = HttpClientUtil.post("http://lft-ocrcloud.52fdw.com:9094/SearchClient/ocr", params);
//			JSONObject ocrResultJson= JSONObject.fromObject(ocrResult);
			System.out.println(ocrResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(CommonUtil.getDateDayTime());
	}
}
