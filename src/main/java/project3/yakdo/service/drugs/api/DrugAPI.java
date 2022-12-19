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
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.dto.DrugInfo1;
import project3.yakdo.service.drugs.api.dto.DrugInfo2;
import project3.yakdo.service.drugs.api.dto.DrugInfo3;
import project3.yakdo.domain.drugs.DrugInfo;

@Slf4j
@RequiredArgsConstructor
public class DrugAPI {

	private final DrugsRepository drugRepository;

	/**
	 * DB의 DRUG_INFO Table과 FIND_DRUG Table의 현재 내용을 모두 날리고 API에서 새로운 정보를 가져와 셋팅하기
	 * 담당자 : 홍준표
	 */
	public void getAPI() {
		// TODO Auto-generated method stub
		trustUrl();
		
		List<DrugInfo> drugInfoList = new ArrayList<>();
		List<FindDrug> findDrugList = new ArrayList<>();
		List<DrugInfo1> tempInfo1 = new ArrayList<>();
		List<DrugInfo2> tempInfo2 = new ArrayList<>();
		List<DrugInfo3> tempInfo3 = new ArrayList<>();

		log.info("DrugInfo 시작");
		setDomainByDrugInfoAPI(tempInfo1);
		log.info("DrugInfo 완료");
		
		log.info("DrugMoreInfo 시작");
		setDomainByDrugMoreInfoAPI(tempInfo2);
		log.info("DrugMoreInfo 완료");
		
		//DUR 금기타입 테이블 필요
		log.info("DUR 시작");
		setDomainByDurAPI();
		log.info("DUR 완료");
		
		log.info("FindDrug 시작");
		setDomainByFindDrugAPI(tempInfo3,findDrugList);
		log.info("FindDrug 완료");
		
		log.info("domainConbine 시작");
		domainConbine(drugInfoList,tempInfo1,tempInfo2,tempInfo3);
		log.info("domainConbine 완료");
		
		log.info("DrugInfo DB 시작");
		int resultDI = setDrugInfoByAPI(drugInfoList);
		log.info("DrugInfo DB 완료");
		
		log.info("FindDrug DB 시작");
		int resultFD = setFindDrugByAPI(findDrugList);
		log.info("FindDrug DB 완료");
		
		
		/////////////////////////////////////////////////////////////
		
		
		
		
	}
	
	private void domainConbine(List<DrugInfo> drugInfoList, List<DrugInfo1> tempInfo1, List<DrugInfo2> tempInfo2,
			List<DrugInfo3> tempInfo3) {
		// TODO Auto-generated method stub
		for(DrugInfo2 di:tempInfo2) {
			DrugInfo drugInfo = new DrugInfo();
			drugInfo.setItemSeq(di.getItemSeq());
			drugInfo.setItemName(di.getItemName());
			drugInfo.setChart(di.getChart());
			drugInfo.setEtcOtcName(di.getEtcOtcName());
			drugInfo.setEntpName(di.getEntpName());
			drugInfo.setEfcyQesitm(di.getEfcyQesitm());
			drugInfo.setUseMethodQesitm(di.getUseMethodQesitm());
			drugInfo.setAtpnQesitm(di.getAtpnQesitm());
			drugInfo.setIngrNameList(di.getIngrNameList());
			drugInfoList.add(drugInfo);
		}
		for(DrugInfo1 di:tempInfo1) {
			boolean isFind = false;
			for(DrugInfo drugInfo:drugInfoList) {
				if(drugInfo.getItemSeq().equals(di.getItemSeq())) {
					isFind = true;
					drugInfo.setItemImage(di.getItemImage());
					if(drugInfo.getEntpName().equals("null")||drugInfo.getEntpName()==null) {
						drugInfo.setEntpName(di.getEntpName());						
					}
					if(drugInfo.getEfcyQesitm().equals("null")||drugInfo.getEfcyQesitm()==null) {
						drugInfo.setEfcyQesitm(di.getEfcyQesitm());
					}
					if(drugInfo.getUseMethodQesitm().equals("null")||drugInfo.getUseMethodQesitm()==null) {
						drugInfo.setUseMethodQesitm(di.getUseMethodQesitm());
					}
					drugInfo.setAtpnWarnQesitm(di.getAtpnWarnQesitm());
					if(drugInfo.getAtpnQesitm().equals("null")||drugInfo.getAtpnQesitm()==null) {
						drugInfo.setAtpnQesitm(di.getAtpnQesitm());
					}
					drugInfo.setSeQesitm(di.getSeQesitm());
					drugInfo.setDepositMethodQesitm(di.getDepositMethodQesitm());
				}
			}
			if(!isFind) {
				DrugInfo drugInfo = new DrugInfo();
				drugInfo.setItemSeq(di.getItemSeq());
				drugInfo.setItemImage(di.getItemImage());
				drugInfo.setItemImage(di.getItemImage());
				drugInfo.setEntpName(di.getEntpName());	
				drugInfo.setEfcyQesitm(di.getEfcyQesitm());
				drugInfo.setUseMethodQesitm(di.getUseMethodQesitm());
				drugInfo.setAtpnWarnQesitm(di.getAtpnWarnQesitm());
				drugInfo.setAtpnQesitm(di.getAtpnQesitm());
				drugInfo.setSeQesitm(di.getSeQesitm());
				drugInfo.setDepositMethodQesitm(di.getDepositMethodQesitm());
				drugInfoList.add(drugInfo);
			}
		}
		for(DrugInfo3 di:tempInfo3) {
			boolean isFind = false;
			for(DrugInfo drugInfo:drugInfoList) {
				if(drugInfo.getItemSeq().equals(di.getItemSeq())) {
					isFind = true;
					drugInfo.setItemEngName(di.getItemEngName());
					drugInfo.setClassNo(di.getClassNo());						
					drugInfo.setClassName(di.getClassName());
					drugInfo.setEdiCode(di.getEdiCode());
				}
			}
			if(!isFind) {
				DrugInfo drugInfo = new DrugInfo();
				drugInfo.setItemSeq(di.getItemSeq());
				drugInfo.setItemEngName(di.getItemEngName());
				drugInfo.setClassNo(di.getClassNo());						
				drugInfo.setClassName(di.getClassName());
				drugInfo.setEdiCode(di.getEdiCode());
				drugInfoList.add(drugInfo);
			}
		}
	}

	private void setDomainByFindDrugAPI(List<DrugInfo3> tempInfo3, List<FindDrug> findDrugList) {
		String tempJson = getJsonStringByApi(DrugPath.FINDDRUG_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.FINDDRUG_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				DrugInfo3 drugInfo = new DrugInfo3();
				drugInfo.setItemSeq(itemObj.getString("ITEM_SEQ").toString());
				drugInfo.setItemEngName(itemObj.get("ITEM_ENG_NAME").toString());
				drugInfo.setClassNo(itemObj.get("CLASS_NO").toString());
				drugInfo.setClassName(itemObj.get("CLASS_NAME").toString());
				drugInfo.setEdiCode(itemObj.get("EDI_CODE").toString());
				tempInfo3.add(drugInfo);
				
				FindDrug findDrug = new FindDrug();
				findDrug.setItemSeq(itemObj.get("ITEM_SEQ").toString());
				findDrug.setPrintFront(itemObj.get("PRINT_FRONT").toString());
				findDrug.setPrintBack(itemObj.get("PRINT_BACK").toString());
				findDrug.setDrugShape(itemObj.get("DRUG_SHAPE").toString());
				findDrug.setColorClass1(itemObj.get("COLOR_CLASS1").toString());
				findDrug.setColorClass2(itemObj.get("COLOR_CLASS2").toString());
				findDrug.setLineFront(itemObj.get("LINE_FRONT").toString());
				findDrug.setLineBack(itemObj.get("LINE_BACK").toString());
				findDrug.setLengLong(itemObj.get("LENG_LONG").toString());
				findDrug.setLengShort(itemObj.get("LENG_SHORT").toString());
				findDrug.setThick(itemObj.get("THICK").toString());
				findDrug.setMarkCodeFrontAnal(itemObj.get("MARK_CODE_FRONT_ANAL").toString());
				findDrug.setMarkCodeBackAnal(itemObj.get("MARK_CODE_BACK_ANAL").toString());
				findDrug.setMarkCodeFront(itemObj.get("MARK_CODE_FRONT").toString());
				findDrug.setMarkCodeBack(itemObj.get("MARK_CODE_BACK").toString());
				findDrug.setMarkCodeFrontImg(itemObj.get("MARK_CODE_FRONT_IMG").toString());
				findDrug.setMarkCodeBackImg(itemObj.get("MARK_CODE_BACK_IMG").toString());
				
				findDrugList.add(findDrug);
			}
		}
		
		
	}
	
	private void setDomainByDurAPI() {
//		String tempJson = getJsonStringByApi(DrugPath.DUR_API_PAGE_,1);
//		JSONObject tempObject = new JSONObject(tempJson);
//		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
//		int totalPage = (totalCnt/100)+1;
//		
//		for(int i=1;i<=totalPage;i++) {
//			String drugMoreInfoJson = getJsonStringByApi(DrugPath.DUR_API_PAGE_,i);
//			JSONObject jObject = new JSONObject(drugMoreInfoJson);
//			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
//			for(int j=0;j<itemList.length();j++) {
//				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
//				
//
//				
//				
//			}			
//		}
	}
	
	private void setDomainByDrugMoreInfoAPI(List<DrugInfo2> tempInfo2) {
		String tempJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				DrugInfo2 drugInfo = new DrugInfo2();
				drugInfo.setItemSeq(itemObj.get("ITEM_SEQ").toString());
				drugInfo.setItemName(itemObj.get("ITEM_NAME").toString());
				drugInfo.setChart(itemObj.get("CHART").toString());
				drugInfo.setEtcOtcName(itemObj.get("ETC_OTC_CODE").toString());
				drugInfo.setEntpName(itemObj.get("ENTP_NAME").toString());
				drugInfo.setEfcyQesitm(itemObj.get("EE_DOC_DATA").toString());
				drugInfo.setUseMethodQesitm(itemObj.get("UD_DOC_DATA").toString());
				drugInfo.setAtpnQesitm(itemObj.get("NB_DOC_DATA").toString());
				String temp = itemObj.get("MAIN_ITEM_INGR").toString()+"|"+itemObj.get("INGR_NAME").toString();
				List<String> ingrList = new ArrayList<>(Arrays.asList(temp.split("|")));
				drugInfo.setIngrNameList(ingrList);
				tempInfo2.add(drugInfo);
			}			
		}
	}

	private void setDomainByDrugInfoAPI(List<DrugInfo1> tempInfo1) {
		String tempJson = getJsonStringByApi(DrugPath.DRUGINFO_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugInfoJson = getJsonStringByApi(DrugPath.DRUGINFO_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");

			for(int j=0;j<itemList.length();j++) {
				DrugInfo1 drugInfo = new DrugInfo1();
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				drugInfo.setItemSeq(itemObj.get("itemSeq").toString());
				drugInfo.setItemName(itemObj.get("itemName").toString());
				drugInfo.setItemImage(itemObj.get("itemImage").toString());
				drugInfo.setEntpName(itemObj.get("entpName").toString());
				drugInfo.setEfcyQesitm(itemObj.get("efcyQesitm").toString());
				drugInfo.setUseMethodQesitm(itemObj.get("useMethodQesitm").toString());
				drugInfo.setAtpnWarnQesitm(itemObj.get("atpnWarnQesitm").toString());
				drugInfo.setAtpnQesitm(itemObj.get("atpnQesitm").toString());
				drugInfo.setIntrcQesitm(itemObj.get("intrcQesitm").toString());
				drugInfo.setSeQesitm(itemObj.get("seQesitm").toString());
				drugInfo.setDepositMethodQesitm(itemObj.get("depositMethodQesitm").toString());
				tempInfo1.add(drugInfo);
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
