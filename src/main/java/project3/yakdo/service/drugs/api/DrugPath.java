package project3.yakdo.service.drugs.api;

public interface DrugPath {
	// 개요정보
	public final String DRUGINFO_API_PAGE_ = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// 상세허가정보
	public final String DRUGMOREINFO_API_PAGE_ = "http://apis.data.go.kr/1471000/DrugPrdtPrmsnInfoService02/getDrugPrdtPrmsnDtlInq01?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// 식별정보
	public final String FINDDRUG_API_PAGE_ = "https://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 노인금기
	public final String DUR_OLDMAN_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getOdsnAtentInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 병용금기
	public final String DUR_COMBI_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getUsjntTabooInfoList02?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 특정연령대금기
	public final String DUR_AGE_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getSpcifyAgrdeTabooInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 임부금기
	public final String DUR_PREGNANT_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getPwnmTabooInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 용량주의
	public final String DUR_CAPACITY_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getCpctyAtentInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 투여기간주의
	public final String DUR_PERIOD_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getMdctnPdAtentInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 효능중복정보
	public final String DUR_DOUBLE_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getEfcyDplctInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보 - 서방정분할주의
	public final String DUR_ER_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getSeobangjeongPartitnAtentInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";

}
