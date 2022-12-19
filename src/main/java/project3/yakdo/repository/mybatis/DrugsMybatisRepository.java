package project3.yakdo.repository.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.repository.DrugsRepository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DrugsMybatisRepository implements DrugsRepository{

	private final DrugsMapper drugsMapper;
	
	/**
	 * DB의 DRUG_INFO Table의 내용을 받아온 리스트로 변경
	 * parameter : List<DrugInfo>
	 * return : insert count
	 * 담당자 : 홍준표
	 */
	@Override
	@Transactional
	public Integer insertDrugInfo(List<DrugInfo> drugInfoList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		drugsMapper.deleteDrugInfo();
		drugsMapper.deleteDrugInfoIngr();
		for(DrugInfo drugInfo:drugInfoList) {
			int insertResult = drugsMapper.insertDrugInfo(drugInfo);
			drugsMapper.insertDrugInfoIngrList(drugInfo.getItemSeq(), drugInfo.getIngrNameList());
			result++;
		}
		return result;
	}

	/**
	 * DB의 FIND_DRUG Table의 현재 내용을 모두 날리고
	 * API에서 새로운 정보를 가져와 셋팅하기
	 * return : insert count
	 * 담당자 : 홍준표
	 */
	@Override
	@Transactional
	public Integer insertFindDrug(List<FindDrug> findDrugList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		drugsMapper.deleteFindDrug();
		for(FindDrug findDrug:findDrugList) {
			int insertResult = drugsMapper.insertFindDrug(findDrug);
			result++;
		}
		return result;
	}
	
	/**
	 * DB에서 DrugInfo 내용을 가져와서
	 * DrugInfo 객체로 이루어진 리스트로 반환
	 * return : ArrayList<DrugInfo>
	 * 담당자 : 홍준표
	 */
	@Override
	public List<DrugInfo> getDrugInfoList() {
		List<DrugInfo> drugInfoList = new ArrayList<>();
		
		//DB에서 가져와서 리스트 반환
		return drugInfoList;
	}
	
	/**
	 * DB에서 FindDrug 내용을 가져와서
	 * FindDrug 객체로 이루어진 리스트로 반환
	 * return : ArrayList<FindDrug>
	 * 담당자 : 홍준표
	 */
	@Override
	public List<FindDrug> getDrugFindInfoList() {
		List<FindDrug> drugFindInfoList = new ArrayList<>();
		
		//DB에서 가져와서 리스트 반환
		return drugFindInfoList;
	}

	/**
	 * DB의 drug_info 테이블을 모두 삭제
	 * drug_info 테이블을 최신화할때 사용함.
	 * 담당자 : 홍준표
	 */
	@Override
	public void deleteDrugInfo() {
		// TODO Auto-generated method stub
		drugsMapper.deleteDrugInfo();
	}

	/**
	 * DB의 drug_info_ingr 테이블을 모두 삭제
	 * drug_info_ingr 테이블을 최신화할때 사용함.
	 * 담당자 : 홍준표
	 */
	@Override
	public void deleteDrugInfoIngr() {
		// TODO Auto-generated method stub
		drugsMapper.deleteDrugInfoIngr();
	}

	/**
	 * DB의 find_drug 테이블을 모두 삭제
	 * find_drug 테이블을 최신화할때 사용함.
	 * 담당자 : 홍준표
	 */
	@Override
	public void deleteFindDrug() {
		// TODO Auto-generated method stub
		drugsMapper.deleteFindDrug();
	}
	
}
