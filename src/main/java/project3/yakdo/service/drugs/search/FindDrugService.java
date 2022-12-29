package project3.yakdo.service.drugs.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.repository.DrugsRepository;

@Service
@RequiredArgsConstructor
public class FindDrugService {
	
	private final DrugsRepository drugsRepository;
	
	/**
	 * findDrugForm 기반으로 DrugInfo List를 반환
	 * parameter : FindDrugForm findDrugForm
	 * return : List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	public List<DrugInfo> findDrugResult(FindDrugForm findDrugForm){
		List<DrugInfo> diList = drugsRepository.getDrugInfoListByFindDrugForm(findDrugForm);
		return diList;
	}
	
	/**
	 * findDrugForm 기반으로 DrugInfo List를 반환
	 * parameter : FindDrugForm findDrugForm
	 * return : List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	public List<DrugInfo> findDrugResultAll(FindDrugForm findDrugForm){
		List<DrugInfo> diList = drugsRepository.getDrugInfoListAllByFindDrugForm(findDrugForm);
		return diList;
	}

	/**
	 * 200개 이상인 tempMarkList를 40개 단위로 쪼개어 drugMarkList에 담고, 총 페이지를 drugMarkPage에 담음
	 * parameter : List<DrugMark> tempMarkList, List<List<DrugMark>> drugMarkList, List<Integer> drugMarkPage
	 * 담당자 : 홍준표
	 */
	public void setMarkListAndMarkPage(List<DrugMark> tempMarkList, List<List<DrugMark>> drugMarkList, List<Integer> drugMarkPage) {
		// TODO Auto-generated method stub
		int divide = 40;
		int index = 0;
		int page = 1;
		for(int i=0;i<(tempMarkList.size()/divide)+1;i++) {
			List<DrugMark> tempList=new ArrayList<>();
			for(int j=0;j<divide;j++) {
				if(index >= tempMarkList.size()) {
					break;
				}
				tempList.add(tempMarkList.get(index));
				index++;
			}
			drugMarkList.add(tempList);
			drugMarkPage.add(page++);
		}
	}

}
