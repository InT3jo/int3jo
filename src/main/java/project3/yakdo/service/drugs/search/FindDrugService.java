package project3.yakdo.service.drugs.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
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
	 * @param: FindDrugForm
	 * @return: List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	public List<DrugInfo> findDrugResult(FindDrugForm findDrugForm){
		List<DrugInfo> diList = drugsRepository.getDrugInfoListByFindDrugForm(findDrugForm);
		return diList;
	}
	
	/**
	 * findDrugForm 기반으로 DrugInfo List를 반환
	 * @param: FindDrugForm
	 * @return: List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	public List<DrugInfo> findDrugResultAll(FindDrugForm findDrugForm){
		List<DrugInfo> diList = drugsRepository.getDrugInfoListAllByFindDrugForm(findDrugForm);
		return diList;
	}

	/**
	 * 200개 이상인 tempMarkList를 40개 단위로 쪼개어 drugMarkList에 담고, 총 페이지를 drugMarkPage에 담음
	 * @param: List<List<DrugMark>>
	 * @param: List<Integer>
	 * 담당자 : 홍준표
	 */
	public void setMarkListAndMarkPage(List<List<DrugMark>> drugMarkList, List<Integer> drugMarkPage) {
		// TODO Auto-generated method stub
		List<DrugMark> tempMarkList = drugsRepository.getDrugMarkAll();
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
	
	/**
	 * FindDrugForm을 기반으로 DrugInfo를 List로 받음
	 * @param: FindDrugForm
	 * @param: HttpServletRequest
	 * @return List<DrugInfo>
	 * 담당자: 홍준표
	 */
	public List<DrugInfo> setFindDrugInfoList(FindDrugForm findDrugForm, HttpServletRequest req) {
		List<DrugInfo> findDrugInfoList = new ArrayList<>();
		if(req.getParameter("searchAll") != null) { //통합검색일때 findDrugForm을 만들어서 검색결과를 가져옴
			findDrugForm.setItemName(req.getParameter("searchAll"));
			findDrugForm.setIngrName(req.getParameter("searchAll"));
			findDrugForm.setEntpName(req.getParameter("searchAll"));
			findDrugForm.setDrugShape("");
			findDrugForm.setDrugColor("");
			findDrugForm.setDrugPrint("");
			findDrugForm.setDrugLineFront("");
			findDrugForm.setDrugLineBack("");
			findDrugForm.setDrugMark("");
			findDrugInfoList = findDrugResultAll(findDrugForm);
		}else { //상세검색일때 fimdDrugForm으로 검색결과를 가져옴
			findDrugInfoList = findDrugResult(findDrugForm);			
		}
		return findDrugInfoList;
	}

}
