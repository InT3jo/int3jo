package project3.yakdo.service.drugs.search;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.DrugAPI;

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
	 * API에서 DB를 update한다.
	 * API에 정보가 부족해서 src/main/webapp/drug/drugOpenData.csv파일을 사용하기때문에
	 * HttpServletRequest를 참조한다.
	 * parameter : HttpServletRequest req
	 * 담당자 : 홍준표
	 */
	public void dbUpdate(HttpServletRequest req) {
		DrugAPI drugAPI = new DrugAPI(drugsRepository);
		drugAPI.getAPI(req);
	}
}
