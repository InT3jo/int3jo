package project3.yakdo.service.drugs.api;

public interface DrugPath {
	// 개요정보
	public final String DRUGINFO_API_PAGE_ = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// 상세허가정보
	public final String DRUGMOREINFO_API_PAGE_ = "http://apis.data.go.kr/1471000/DrugPrdtPrmsnInfoService02/getDrugPrdtPrmsnDtlInq01?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// dur정보
	public final String DUR_API_PAGE_ = "https://apis.data.go.kr/1471000/DURPrdlstInfoService02/getOdsnAtentInfoList2?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";
	// 식별정보
	public final String FINDDRUG_API_PAGE_ = "https://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01?serviceKey=G62hk2I0pLFxtqFT%2BvdbXYYbtsEt3ozXPWOIQj9AkjwkkUnD4T3ix2MD3biuEMtrquj2my5Hp9RJrSleYlDrzA%3D%3D&type=json&numOfRows=100&pageNo=";

}
