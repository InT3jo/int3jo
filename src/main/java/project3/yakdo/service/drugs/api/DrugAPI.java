package project3.yakdo.service.drugs.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.Dur;
import project3.yakdo.domain.drugs.DurCombi;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.repository.DrugsRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrugAPI {

	private final DrugsRepository drugRepository;

	/**
	 * DB의 DRUG_INFO Table과 FIND_DRUG Table, DUR Table의 현재 내용을 모두 날리고
	 * API에서 새로운 정보를 가져와 셋팅하기
	 * 담당자 : 홍준표
	 */
	public void getAPI() {
		// TODO Auto-generated method stub
		log.info("API 데이터 DB 최신화 작업 시작");
		trustUrl("https://apis.data.go.kr"); // 신뢰하는 URL에 추가
		
		List<DrugInfo> drugInfoList = new ArrayList<>();
		List<FindDrug> findDrugList = new ArrayList<>();
		List<Dur> durList = new ArrayList<>();
		List<DurCombi> durCombiList = new ArrayList<>();
		List<String> durAPIList = new ArrayList<>();
		durAPIList.add(DrugPath.DUR_AGE_API_PAGE_);
		durAPIList.add(DrugPath.DUR_AGE_API_PAGE_);
		durAPIList.add(DrugPath.DUR_PREGNANT_API_PAGE_);
		durAPIList.add(DrugPath.DUR_CAPACITY_API_PAGE_);
		durAPIList.add(DrugPath.DUR_PERIOD_API_PAGE_);
		durAPIList.add(DrugPath.DUR_DOUBLE_API_PAGE_);
		durAPIList.add(DrugPath.DUR_ER_API_PAGE_);

		log.info("DrugInfo, FindDrug, Dur 테이블 기존 데이터 삭제 시작");
		drugRepository.deleteDrugInfo();
		drugRepository.deleteDrugInfoIngr();
		drugRepository.deleteFindDrug();
		drugRepository.deleteDur();
		drugRepository.deleteDurCombi();
		log.info("DrugInfo, FindDrug, Dur 테이블 기존 데이터 삭제 완료");
	
		int tCnt = 10; // 한번에 가져오면 메모리 부담이 커서 몇번에 나눠서 가져올건지 결정
		for(int i=1;i<=tCnt;i++) {
			setDomainByDrugMoreInfoAPI(drugInfoList,i,tCnt);
			setDrugInfoByAPI(drugInfoList);
			log.info("DrugMoreInfo DB insert 완료("+i+"/"+tCnt+")");			
		}
		setDomainByDrugInfoAPI(drugInfoList);
		setDrugInfoByAPI(drugInfoList);
		setDomainByFindDrugAPI(drugInfoList,findDrugList);
		setFindDrugByAPI(findDrugList);
		setDrugInfoByAPI(drugInfoList);
		
		log.info("Dur API 가져오기 시작");
		for(String path:durAPIList) {
			setDomainByDurAPI(durList,path);
			setDurByAPI(durList);
		}
		setDomainByDurCombiAPI(durCombiList);
		setDurCombiByAPI(durCombiList);
		log.info("Dur 전체 API 가져오기 및 DB insert 완료");
		
	}

	private void setDomainByFindDrugAPI(List<DrugInfo> drugInfoList, List<FindDrug> findDrugList) {
		log.info("FindDrug API 가져오기 시작");
		String tempJson = getJsonStringByApi(DrugPath.FINDDRUG_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String findDrugJson = getJsonStringByApi(DrugPath.FINDDRUG_API_PAGE_,i);
			JSONObject jObject = new JSONObject(findDrugJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				DrugInfo drugInfo = new DrugInfo();
				drugInfo.setItemSeq(itemObj.getString("ITEM_SEQ").toString());
				drugInfo.setItemEngName(itemObj.get("ITEM_ENG_NAME").toString());
				drugInfo.setClassNo(itemObj.get("CLASS_NO").toString());
				drugInfo.setClassName(itemObj.get("CLASS_NAME").toString());
				drugInfo.setEdiCode(itemObj.get("EDI_CODE").toString());
				drugInfoList.add(drugInfo);
				
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
			if(i%10==0) {
				log.info("DrugAPI.setDomainByFindDrugAPI();({}page/{}page)",i,totalPage);
			}
		}
		log.info("FindDrug API 가져오기 완료");
	}
	
	private void setDomainByDurAPI(List<Dur> durList, String path) {
		String tempJson = getJsonStringByApi(path,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(path,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				Dur dur = new Dur();
				dur.setItemSeq(itemObj.get("ITEM_SEQ").toString());
				if(path != DrugPath.DUR_ER_API_PAGE_) {
					dur.setIngrCode(itemObj.get("INGR_CODE").toString());
					dur.setIngrName(itemObj.get("INGR_NAME").toString());					
				}
				dur.setTypeName(itemObj.get("TYPE_NAME").toString());
				durList.add(dur);
			}
			if(i%10==0) {
				log.info("DrugAPI.setDomainByDurAPI();({}page/{}page)",i,totalPage);
			}		
		}
	}
	
	private void setDomainByDurCombiAPI(List<DurCombi> durList) {
		String tempJson = getJsonStringByApi(DrugPath.DUR_COMBI_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.DUR_COMBI_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				DurCombi dur = new DurCombi();
				dur.setItemSeq(itemObj.get("ITEM_SEQ").toString());
				dur.setIngrCode(itemObj.get("INGR_CODE").toString());
				dur.setIngrName(itemObj.get("INGR_KOR_NAME").toString());
				dur.setMixIngrCode(itemObj.get("MIXTURE_INGR_CODE").toString());
				dur.setMixIngrName(itemObj.get("MIXTURE_INGR_KOR_NAME").toString());
				durList.add(dur);
			}
			if(i%10==0) {
				log.info("DrugAPI.setDomainByDurCombiAPI();({}page/{}page)",i,totalPage);
			}		
		}
	}
	

	
	private void setDomainByDrugMoreInfoAPI(List<DrugInfo> drugInfoList, int cnt, int tCnt) {
		log.info("DrugMoreInfo API("+cnt+"/"+tCnt+") 가져오기 시작");
		String tempJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		int startNum = ((totalPage*(cnt-1))/tCnt)+1 ;
		int endNum = (totalPage*cnt)/tCnt;
		for(int i=startNum;i<=endNum;i++) {
			String drugMoreInfoJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugMoreInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
			for(int j=0;j<itemList.length();j++) {
				JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
				DrugInfo drugInfo = new DrugInfo();
				drugInfo.setItemSeq(itemObj.get("ITEM_SEQ").toString());
				drugInfo.setItemName(itemObj.get("ITEM_NAME").toString());
				drugInfo.setChart(itemObj.get("CHART").toString());
				drugInfo.setEtcOtcName(itemObj.get("ETC_OTC_CODE").toString());
				drugInfo.setEntpName(itemObj.get("ENTP_NAME").toString());
				drugInfo.setEfcyQesitm(itemObj.get("EE_DOC_DATA").toString());
				drugInfo.setUseMethodQesitm(itemObj.get("UD_DOC_DATA").toString());
				drugInfo.setAtpnQesitm(itemObj.get("NB_DOC_DATA").toString());
				drugInfo.setNarcotic(itemObj.get("NARCOTIC_KIND_CODE").toString());
				String temp = itemObj.get("MAIN_ITEM_INGR").toString()+"|"+itemObj.get("INGR_NAME").toString();
				List<String> ingrList = new ArrayList<>(Arrays.asList(temp.split("\\|")));
				drugInfo.setIngrNameList(ingrList);
				drugInfoList.add(drugInfo);
			}
			if(i%10==0) {
				log.info("DrugAPI.setDomainByDrugMoreInfoAPI();({}page/{}page)",i,endNum);
			}
		}
		log.info("DrugMoreInfo API("+cnt+"/"+tCnt+") 가져오기 완료");
	}

	private void setDomainByDrugInfoAPI(List<DrugInfo> drugInfoList) {
		log.info("DrugInfo API 가져오기 시작");
		String tempJson = getJsonStringByApi(DrugPath.DRUGINFO_API_PAGE_,1);
		JSONObject tempObject = new JSONObject(tempJson);
		int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
		int totalPage = (totalCnt/100)+1;
		
		for(int i=1;i<=totalPage;i++) {
			String drugInfoJson = getJsonStringByApi(DrugPath.DRUGINFO_API_PAGE_,i);
			JSONObject jObject = new JSONObject(drugInfoJson);
			JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");

			for(int j=0;j<itemList.length();j++) {
				DrugInfo drugInfo = new DrugInfo();
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
				drugInfoList.add(drugInfo);
			}
			if(i%10==0) {
				log.info("DrugAPI.setDomainByDrugInfoAPI();({}page/{}page)",i,totalPage);
			}
		}
		log.info("DrugInfo API 가져오기 완료");
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

	private void trustUrl(String htmlUrl) {
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

	private int setDurByAPI(List<Dur> durList) {
		int result = drugRepository.insertDur(durList);
		durList.clear(); // 메모리 회수 필요
		return result;
	}
	
	private int setDurCombiByAPI(List<DurCombi> durList) {
		int result = drugRepository.insertDurCombi(durList);
		durList.clear(); // 메모리 회수 필요
		return result;
	}
	
	private int setDrugInfoByAPI(List<DrugInfo> drugInfoList) {
		int result = drugRepository.insertDrugInfo(drugInfoList);
		drugInfoList.clear(); // 메모리 회수 필요
		log.info("DrugInfo DB insert 완료");
		return result;
	}

	private int setFindDrugByAPI(List<FindDrug> findDrugList) {
		int result = drugRepository.insertFindDrug(findDrugList);
		log.info("FindDrug DB(FindDrug) insert 완료");
		return result;
	}
	
//	private void setDomainByDrugMoreInfoAPITest(List<DrugInfo> drugInfoList) {
//	String tempJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,1);
//	JSONObject tempObject = new JSONObject(tempJson);
//	int totalCnt = tempObject.getJSONObject("body").getInt("totalCount");
//	int totalPage = (totalCnt/100)+1;
//	
//	for(int i=1;i<=2;i++) {
//		String drugMoreInfoJson = getJsonStringByApi(DrugPath.DRUGMOREINFO_API_PAGE_,i);
//		JSONObject jObject = new JSONObject(drugMoreInfoJson);
//		JSONArray itemList = jObject.getJSONObject("body").getJSONArray("items");
//		for(int j=0;j<itemList.length();j++) {
//			JSONObject itemObj = (JSONObject)itemList.getJSONObject(j);
//			DrugInfo drugInfo = new DrugInfo();
//			drugInfo.setItemSeq(itemObj.get("ITEM_SEQ").toString());
//			drugInfo.setItemName(itemObj.get("ITEM_NAME").toString());
//			drugInfo.setChart(itemObj.get("CHART").toString());
//			drugInfo.setEtcOtcName(itemObj.get("ETC_OTC_CODE").toString());
//			drugInfo.setEntpName(itemObj.get("ENTP_NAME").toString());
//			drugInfo.setEfcyQesitm(itemObj.get("EE_DOC_DATA").toString());
//			drugInfo.setUseMethodQesitm(itemObj.get("UD_DOC_DATA").toString());
//			drugInfo.setAtpnQesitm(itemObj.get("NB_DOC_DATA").toString());
//			String temp = itemObj.get("MAIN_ITEM_INGR").toString()+"|"+itemObj.get("INGR_NAME").toString();
//			List<String> ingrList = new ArrayList<>(Arrays.asList(temp.split("|")));
//			drugInfo.setIngrNameList(ingrList);
//			drugInfoList.add(drugInfo);
//		}			
//	}
//}
}
