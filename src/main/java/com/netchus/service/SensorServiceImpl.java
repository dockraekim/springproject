package com.netchus.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.netchus.mapper.EnvironmentMapper;
import com.netchus.utils.MyDateUtil;


@Service
public class SensorServiceImpl implements SensorService {

	private EnvironmentMapper mapper;

	public SensorServiceImpl(EnvironmentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public void setMapper(EnvironmentMapper mapper) {
		this.mapper = mapper;
	} 


	public static final String APP_KEY = "";
	
	/*
	 * sensor 값을 DB에서 읽어오는 메소드
	*/
//	public ArrayList<HashMap<String, Object>> getResponse() {
//		ArrayList<HashMap<String,Object>> tmp = new ArrayList<HashMap<String,Object>>();
//		try {
//			tmp = mapper.getTest();
//			for (HashMap<String, Object> hashMap : tmp) {
//				System.out.println(hashMap.entrySet());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return tmp;
//	}
	
	@Override
	public ArrayList<Map<String, Object>> getResponse() {
		String tmp;
		ArrayList<Map<String, Object>> arrayList = null;
		try {
			tmp = sendGet();
			arrayList = makeSensorData(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

    public ArrayList<Map<String, Object>> makeSensorData(String tmp) throws ParseException{
    	// 1. json parser로 자른다
    	JSONParser jsonParser = new JSONParser(); 
    	JSONObject jsonObject = (JSONObject)jsonParser.parse(tmp);
    	JSONObject jsonObj = (JSONObject)jsonObject.get("content");
    	JSONArray jsonArray = (JSONArray)jsonObj.get("data");
    	
    	System.out.println(jsonObj.get("result"));
    	
    	// 객체(map)을 담을 ArrayArrayList 선언
    	ArrayList<Map<String, Object>> jsonSensorArrayList = new ArrayList<Map<String,Object>>();
    	
    	for (@SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = jsonArray.iterator(); iterator.hasNext();) {
    		JSONObject object = (JSONObject) iterator.next();
    		JSONObject metaObj = (JSONObject) object.get("meta");
    		
    		JSONObject rawObj = (JSONObject) object.get("raw");
    		JSONObject conObj = (JSONObject) rawObj.get("content");
    		JSONArray pldArr = (JSONArray) conObj.get("payloads");
    		JSONObject dataObj = (JSONObject)pldArr.get(0);
    		JSONArray dataArr = (JSONArray)dataObj.get("data");
    		
    		// 2. 자른 parse 데이터를 HashMap에 담고
    		// 담을 tmp map 선언
    		Map<String, Object> jsonSensorsMap = new HashMap<String,Object>();
    		
    		// 객체 잘라서 담기
    		jsonSensorsMap.put("sensorName", metaObj.get("sensorName"));
    		jsonSensorsMap.put("sensorId", metaObj.get("sensorId"));
    		
    		for (int i = 0; i < dataArr.size(); i++) {
    			JSONObject tmpObj = (JSONObject)dataArr.get(i);
				jsonSensorsMap.put((String)tmpObj.get("property"),tmpObj.get("value"));
			}
    		
    		String pattern = "yyyy-MM-dd HH:mm:ss";
    		SimpleDateFormat sdft = new SimpleDateFormat(pattern);
    		
    		String createTime = sdft.format(object.get("create-time"));
    		
    		System.out.println(createTime.toString());
    		jsonSensorsMap.put("createTime", createTime);
    		
    		// 3. 만들어진 HashMap을 ArrayArrayList에 담는다.
    		jsonSensorArrayList.add(jsonSensorsMap);
    	}
    	
    	return jsonSensorArrayList;
    }
    
	/*
	 * mapper를 이용해서 DB에 넣는 메서드
	*/
//    private void insert(ArrayList<Map<String, Object>> data) {
//    	System.out.println(mapper.insert(data));
//    }
    
    
    private String sendGet() throws Exception {
        HttpURLConnection con = makeConnection();
        int responseCode = con.getResponseCode();
        
        if(responseCode!=200) {
        	return null;
        }
        System.out.println("Response Code : " + responseCode);

        Charset charset = Charset.forName("UTF-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

	private HttpURLConnection makeConnection() throws MalformedURLException, IOException, ProtocolException {
		
//		String ed = MyDateUtil.minFormatter(0);
//		String sd = MyDateUtil.minFormatter(1);
//		String url = "https://sentry-api.rbinsight.co.kr/sentry/v1/sensor/WPAS_KTR_SENSOR?sensorStartTime="
//				+ sd + "&sensorEndTime=" + ed+ "&offset=0&limit=200";
		
		String ed = MyDateUtil.getDefaultDate();
		System.out.println(ed.toString());
		String sd = MyDateUtil.minFormatter(1);
		System.out.println(sd.toString());
		String url = "http://sentry-api.rbinsight.co.kr/sentry/v1/sensor/SENTRY_PTU_SENSOR?sensorStartTime="
				+ sd
				+ "&sensorEndTime="
				+ ed
				+ "&offset=0&limit=200";
		
		System.out.println(url);
		URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //전송방식
        con.setRequestMethod("GET");
        //Request Header 정의
        con.setRequestProperty("url", url);
        con.setConnectTimeout(10000);       //컨텍션타임아웃 10초
        con.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5총
		return con;
	}

	

}
