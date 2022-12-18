package project3.yakdo.service.drugs.api;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.domain.drugs.DrugInfo;

@RequiredArgsConstructor
public class DrugAPI {

	private final DrugsRepository drugRepository;
	
	/**
	 * DB의 DRUG_INFO Table의 현재 내용을 모두 날리고
	 * API에서 새로운 정보를 가져와 셋팅하기
	 * return : 0 성공, 1 실패
	 * 담당자 : 홍준표
	 */
	public int setDrugInfoByAPI() {
		List<DrugInfo> drugInfoList = new ArrayList<>();
		
		//api 받아와야함
		List<String> ingrNameList = new ArrayList<>();
		ingrNameList.add("성분2");
		ingrNameList.add("성분3");
		
		DrugInfo drugInfo = new DrugInfo("일련번호", "제품명", "제품명(영)", "이미지경로",
			"분류번호", "분류명", "성상", "전문/일반", "제조사명", "효능", "용법", "경고", "주의사항",
			"상호작용", "부작용", "보관법", "보험코드", ingrNameList);
		drugInfoList.add(drugInfo);
		return drugRepository.insertDrugInfo(drugInfoList);
	}
	
	/**
	 * DB의 FIND_DRUG Table의 현재 내용을 모두 날리고
	 * API에서 새로운 정보를 가져와 셋팅하기
	 * return : 0 성공, 1 실패
	 * 담당자 : 홍준표
	 */
	public int setFindDrugByAPI() {
		List<FindDrug> findDrugList = new ArrayList<>();
		
		//api 받아와야함
		
		FindDrug findDrug = new FindDrug("일련번호", "표시앞", "표시뒤", "모양",
			"색깔앞", "색깔뒤", "분할선앞", "분할선뒤", "크기장축", "크기단축", "크기두께",
			"마크내용앞", "마크내용뒤", "마크코드앞", "마크코드뒤", "마크이미지앞", "마크이미지뒤");
		findDrugList.add(findDrug);
		return drugRepository.insertFindDrug(findDrugList);
	}
	
	
}
