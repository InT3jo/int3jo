package project3.yakdo.repository.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

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
	public Integer insertDrugInfo(List<DrugInfo> drugInfoList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		for(DrugInfo drugInfo:drugInfoList) {
			DrugInfo dbDrugInfo = drugsMapper.selectDrugInfoByItemSeq(drugInfo.getItemSeq());
			if( dbDrugInfo != null) {
				//해당 일련번호가 있으면 업데이트
				updateCol(drugInfo.getItemSeq(),"ITEM_NAME",drugInfo.getItemName());
				updateCol(drugInfo.getItemSeq(), "ITEM_ENG_NAME", drugInfo.getItemEngName());
				updateCol(drugInfo.getItemSeq(), "ITEM_IMAGE", drugInfo.getItemImage());
				updateCol(drugInfo.getItemSeq(), "CLASS_NO", drugInfo.getClassNo());
				updateCol(drugInfo.getItemSeq(), "CLASS_NAME", drugInfo.getClassName());
				updateCol(drugInfo.getItemSeq(), "CHART", drugInfo.getChart());
				updateCol(drugInfo.getItemSeq(), "ETC_OTC_NAME", drugInfo.getEtcOtcName());
				updateCol(drugInfo.getItemSeq(), "ENTP_NAME", drugInfo.getEntpName());
				updateCol(drugInfo.getItemSeq(), "EFCY_QESITM", drugInfo.getEfcyQesitm());
				updateCol(drugInfo.getItemSeq(), "USE_METHOD_QESITM", drugInfo.getUseMethodQesitm());
				updateCol(drugInfo.getItemSeq(), "ATPN_WARN_QESITM", drugInfo.getAtpnWarnQesitm());
				updateCol(drugInfo.getItemSeq(), "ATPN_QESITM", drugInfo.getAtpnQesitm());
				updateCol(drugInfo.getItemSeq(), "INTRC_QESITM", drugInfo.getIntrcQesitm());
				updateCol(drugInfo.getItemSeq(), "SE_QESITM", drugInfo.getSeQesitm());
				updateCol(drugInfo.getItemSeq(), "DEPOSIT_METHOD_QESITM", drugInfo.getDepositMethodQesitm());
				updateCol(drugInfo.getItemSeq(), "EDI_CODE", drugInfo.getEdiCode());
				drugInfo.allClear(); // 메모리 회수
			}else {
				// 해당 일련번호가 없으면 인서트
				drugsMapper.insertDrugInfo(drugInfo);
				if(drugInfo.getIngrNameList() != null) {
					for(String ingr:drugInfo.getIngrNameList()) {
						drugsMapper.insertDrugInfoIngr(drugInfo.getItemSeq(), ingr);				
					}				
				}
				drugInfo.allClear(); // 메모리 회수
			}
			result++;
			if(result==1) {
				log.info("마이바티스.insertDrugInfo();("+result+"/"+drugInfoList.size()+")");				
			}
			if(result%100==0) {
				log.info("마이바티스.insertDrugInfo();("+result+"/"+drugInfoList.size()+")");				
			}
		}
		return result;
	}

	private void updateCol(String itemSeq, String colName, String value) {
		// TODO Auto-generated method stub
		Map<String, String> col = new HashMap<>();
		col.put("itemSeq", itemSeq);
		col.put("colName", colName);
		if(colName.equals("ATPN_QESITM")) {
			String temp = "";
			String[] tempList = value.split(""); 
			for(int i=0;i<tempList.length;i++) {
				if(i!=0) {
					temp+="||";
				}
				temp += "TO_CLOB('" + tempList[i] + "')";
			}
			col.put("value", temp);
		}else if(colName.equals("EFCY_QESITM")) {
			String temp = "";
			String[] tempList = value.split(""); 
			for(int i=0;i<tempList.length;i++) {
				if(i!=0) {
					temp+="||";
				}
				temp += "TO_CLOB('" + tempList[i] + "')";
			}
			col.put("value", temp);
		}else if(colName.equals("USE_METHOD_QESITM")) {
			String temp = "";
			String[] tempList = value.split(""); 
			for(int i=0;i<tempList.length;i++) {
				if(i!=0) {
					temp+="||";
				}
				temp += "TO_CLOB('" + tempList[i] + "')";
			}
			col.put("value", temp);
		}else {
			col.put("value", "'"+value+"'");			
		}
		try {
			drugsMapper.updateDrugInfo(col);			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * DB의 FIND_DRUG Table의 현재 내용을 모두 날리고
	 * API에서 새로운 정보를 가져와 셋팅하기
	 * return : insert count
	 * 담당자 : 홍준표
	 */
	@Override
	public Integer insertFindDrug(List<FindDrug> findDrugList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		for(FindDrug findDrug:findDrugList) {
			drugsMapper.insertFindDrug(findDrug);
			result++;
			findDrug.allClear(); // 메모리 회수
			if(result==1) {
				log.info("마이바티스.insertFindDrug();("+result+"/"+findDrugList.size()+")");				
			}
			if(result%100==0) {
				log.info("마이바티스.insertFindDrug();("+result+"/"+findDrugList.size()+")");				
			}
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
