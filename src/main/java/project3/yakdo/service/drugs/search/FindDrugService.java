package project3.yakdo.service.drugs.search;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.DrugInfo;
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

}
