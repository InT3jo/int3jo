package project3.yakdo.service.drugs.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.domain.drugs.DrugInfo;

@RequiredArgsConstructor
public class DrugAPI {

	private final DrugsRepository drugRepository;

	/**
	 * DB의 DRUG_INFO Table과 FIND_DRUG Table의 현재 내용을 모두 날리고 API에서 새로운 정보를 가져와 셋팅하기
	 * return : 0 성공, 1 실패 담당자 : 홍준표
	 */
	public void getAPI() {
		// TODO Auto-generated method stub
		trustUrl();
		
		List<DrugInfo> drugInfoList = new ArrayList<>();
		List<FindDrug> findDrugList = new ArrayList<>();
		
		setDomainByDrugInfoAPI(drugInfoList);
		setDomainByDrugMoreInfoAPI(drugInfoList);
		//DUR 금기타입 테이블 필요
		setDomainByDurAPI(drugInfoList);
		setDomainByFindDrugAPI(drugInfoList,findDrugList);
		
		
		
		int resultDI = setDrugInfoByAPI(drugInfoList);
		int resultFD = setFindDrugByAPI(findDrugList);

	}
	
	private void setDomainByFindDrugAPI(List<DrugInfo> drugInfoList, List<FindDrug> findDrugList) {
		String tempJson = getJsonStringByApi(DrugPath.FINDDRUG_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.FINDDRUG_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = (JSONArray) jObject.get("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				for(DrugInfo drugInfo:drugInfoList) {
					if(drugInfo.getItemSeq().equals(itemObj.getString("itemSeq"))) {
						drugInfo.setItemEngName(itemObj.getString("ITEM_ENG_NAME"));
						drugInfo.setClassNo(itemObj.getString("CLASS_NO"));
						drugInfo.setClassName(itemObj.getString("CLASS_NAME"));
						drugInfo.setEdiCode(itemObj.getString("EDI_CODE"));
					}
				}
				FindDrug findDrug = new FindDrug();
				findDrug.setItemSeq(itemObj.getString("ITEM_SEQ"));
				findDrug.setPrintFront(itemObj.getString("PRINT_FRONT"));
				findDrug.setPrintBack(itemObj.getString("PRINT_BACK"));
				findDrug.setDrugShape(itemObj.getString("DRUG_SHAPE"));
				findDrug.setColorClass1(itemObj.getString("COLOR_CLASS1"));
				findDrug.setColorClass2(itemObj.getString("COLOR_CLASS2"));
				findDrug.setLineFront(itemObj.getString("LINE_FRONT"));
				findDrug.setLineBack(itemObj.getString("LINE_BACK"));
				findDrug.setLengLong(itemObj.getString("LENG_LONG"));
				findDrug.setLengShort(itemObj.getString("LENG_SHORT"));
				findDrug.setThick(itemObj.getString("THICK"));
				findDrug.setMarkCodeFrontAnal(itemObj.getString("MARK_CODE_FRONT_ANAL"));
				findDrug.setMarkCodeBackAnal(itemObj.getString("MARK_CODE_BACK_ANAL"));
				findDrug.setMarkCodeFront(itemObj.getString("MARK_CODE_FRONT"));
				findDrug.setMarkCodeBack(itemObj.getString("MARK_CODE_BACK"));
				findDrug.setMarkCodeFrontImg(itemObj.getString("MARK_CODE_FRONT_IMG"));
				findDrug.setMarkCodeBackImg(itemObj.getString("MARK_CODE_BACK_IMG"));
				
				findDrugList.add(findDrug);
			}
		}
		
		
	}
	
	private void setDomainByDurAPI(List<DrugInfo> drugInfoList) {
		String tempJson = getJsonStringByApi(DrugPath.DUR_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.DUR_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = (JSONArray) jObject.get("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				

				
				
			}			
		}
	}
	
	private void setDomainByDrugMoreInfoAPI(List<DrugInfo> drugInfoList) {
		String tempJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = (JSONArray) jObject.get("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				for(DrugInfo drugInfo:drugInfoList) {
					if(drugInfo.getItemSeq().equals(itemObj.getString("itemSeq"))) {
						drugInfo.setChart(itemObj.getString("CHART"));
						drugInfo.setEtcOtcName(itemObj.getString("ETC_OTC_CODE"));
						String temp = itemObj.getString("MAIN_ITEM_INGR")+"|"+itemObj.getString("INGR_NAME");
						List<String> ingrList = new ArrayList<>(Arrays.asList(temp.split("|")));
						drugInfo.setIngrNameList(ingrList);
					}
				}
			}			
		}
	}

	private void setDomainByDrugInfoAPI(List<DrugInfo> drugInfoList) {
		String tempJson = getJsonStringByApi(DrugPath.DRUGINFO_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			DrugInfo drugInfo = new DrugInfo();
			String drugInfoJson = getJsonStringByApi(DrugPath.DRUGINFO_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugInfoJson);
			JSONArray itemList = (JSONArray) jObject.get("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				drugInfo.setItemSeq(itemObj.getString("itemSeq"));
				drugInfo.setItemName(itemObj.getString("itemName"));
				drugInfo.setItemImage(itemObj.getString("itemImage"));
				drugInfo.setEntpName(itemObj.getString("entpName"));
				drugInfo.setEfcyQesitm(itemObj.getString("efcyQesitm"));
				drugInfo.setUseMethodQesitm(itemObj.getString("useMethodQesitm"));
				drugInfo.setAtpnWarnQesitm(itemObj.getString("atpnWarnQesitm"));
				drugInfo.setAtpnQesitm(itemObj.getString("atpnQesitm"));
				drugInfo.setIntrcQesitm(itemObj.getString("intrcQesitm"));
				drugInfo.setSeQesitm(itemObj.getString("seQesitm"));
				drugInfo.setDepositMethodQesitm(itemObj.getString("depositMethodQesitm"));
				drugInfoList.add(drugInfo);
			}			
		}
	}

	private String getJsonStringByApi(String strUrl,int page) {
		String jsonStr = "";
		try {
			URL url = new URL(strUrl + page);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			jsonStr = sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	private void trustUrl() {
		String htmlUrl = "https://apis.data.go.kr";
		TrustManager[] trustAllCerts = new TrustManager[] { 
		    new X509TrustManager() {
		        public X509Certificate[] getAcceptedIssuers() {
		            return null;
		        }

		        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
		        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
			}
		};
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private int setDrugInfoByAPI(List<DrugInfo> drugInfoList) {
		return drugRepository.insertDrugInfo(drugInfoList);
	}

	private int setFindDrugByAPI(List<FindDrug> findDrugList) {

		return drugRepository.insertFindDrug(findDrugList);
	}

}
