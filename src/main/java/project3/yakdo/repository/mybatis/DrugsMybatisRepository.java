package project3.yakdo.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.domain.drugs.DrugsNameForm;
import project3.yakdo.domain.drugs.Dur;
import project3.yakdo.domain.drugs.DurCombi;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.search.FindDrugForm;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DrugsMybatisRepository implements DrugsRepository{

	private final DrugsMapper drugsMapper;
	
/* INSERT 관련 메서드 */
	
	/**
	 * DB의 DRUG_INFO Table에 파라미터의 리스트를 추가
	 * 이미 있는 일련번호는 빈 내용만 가져온 내용으로 채워넣는다.
	 * @param: List<DrugInfo>
	 * @return: Integer(insert count)
	 * 담당자 : 홍준표
	 */
	@Override
	public Integer insertDrugInfo(List<DrugInfo> drugInfoList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		try {
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
					updateCol(drugInfo.getItemSeq(), "NARCOTIC", drugInfo.getNarcotic());
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
					log.info("DRUG_INFO 테이블에 자료넣는중({}/{})",result,drugInfoList.size());
				}
				if(result%100==0) {
					log.info("DRUG_INFO 테이블에 자료넣는중({}/{})",result,drugInfoList.size());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("DRUG_INFO 테이블에 자료 넣는중, 오류발생으로 중지({}/{})",result,drugInfoList.size());
		}
		return result;
	}
	private void updateCol(String itemSeq, String colName, String value) {
		// TODO Auto-generated method stub
		if(itemSeq!=null&&colName!=null&&value!=null) { // 3가지 데이터중 하나라도 null이면 패스
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
				log.info("itemSeq:{}의 {}행 업데이트 실패",col.get(itemSeq),col.get(colName));
			}
		}
	}

	/**
	 * DB의 FIND_DRUG 테이블에 파라미터의 리스트를 추가하기
	 * @param: List<FindDrug>
	 * @return: Integer(insert count)
	 * 담당자 : 홍준표
	 */
	@Override
	public Integer insertFindDrug(List<FindDrug> findDrugList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		try {
			for(FindDrug findDrug:findDrugList) {
				drugsMapper.insertFindDrug(findDrug);
				result++;
				findDrug.allClear(); // 메모리 회수
				if(result==1) {
					log.info("FIND_DRUG 테이블에 자료 넣는중({}/{})",result,findDrugList.size());
				}
				if(result%100==0) {
					log.info("FIND_DRUG 테이블에 자료 넣는중({}/{})",result,findDrugList.size());
				}
			}
			findDrugList.clear(); // 메모리 회수 필요
		} catch (Exception e) {
			// TODO: handle exception
			log.info("FIND_DRUG 테이블에 자료 넣는중, 오류발생으로 중지({}/{})",result,findDrugList.size());
		}
		return result;
	}
	
	/**
	 * DB의 DUR 테이블에 파라미터의 리스트를 추가하기
	 * @param: List<Dur>
	 * @return: Integer(insert count)
	 * 담당자 : 홍준표
	 */
	@Override
	public Integer insertDur(List<Dur> durList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		try {
			for(Dur dur:durList) {
				drugsMapper.insertDur(dur);
				result++;
				dur.allClear(); // 메모리 회수
				if(result==1) {
					log.info("DUR 테이블에 자료 넣는중({}/{})",result,durList.size());
				}
				if(result%100==0) {
					log.info("DUR 테이블에 자료 넣는중({}/{})",result,durList.size());
				}
			}
			durList.clear(); // 메모리 회수 필요			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("DUR 테이블에 자료 넣는중, 오류발생으로 중지({}/{})",result,durList.size());
		}
		return result;
	}
	
	/**
	 * DB의 DUR_COMBI 테이블에 파라미터의 리스트를 추가하기
	 * @param: List<DurCombi>
	 * @return: Integer(insert count)
	 * 담당자 : 홍준표
	 */
	@Override
	public Integer insertDurCombi(List<DurCombi> durList) {
		// TODO Auto-generated method stub
		Integer result = 0;
		try {
			for(DurCombi dur:durList) {
				drugsMapper.insertDurCombi(dur);
				result++;
				dur.allClear(); // 메모리 회수
				if(result==1) {
					log.info("DUR_COMBI 테이블에 자료 넣는중({}/{})",result,durList.size());
				}
				if(result%100==0) {
					log.info("DUR_COMBI 테이블에 자료 넣는중({}/{})",result,durList.size());				
				}
			}
			durList.clear(); // 메모리 회수 필요			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("DUR_COMBI 테이블에 자료 넣는중, 오류발생으로 중지({}/{})",result,durList.size());
		}
		return result;
	}
	
	
/* SELECT 관련 메서드 */
	
	/**
	 * FindDrugForm을 기반으로 DrugInfo 객체로 이루어진 리스트로 반환
	 * 목록용 객체로, 내용은 itemSeq, itemName, itemImage, entpName 만 가져온다.
	 * @param: FindDrugForm
	 * @return: ArrayList<DrugInfo>
	 * 담당자 : 홍준표
	 */
	@Override
	public List<DrugInfo> getDrugInfoListByFindDrugForm(FindDrugForm findDrugForm) {
		String[] itemNames = findDrugForm.getItemName().split(" ");			
		String[] ingrNames = findDrugForm.getIngrName().split(" ");			
		String[] entpNames = findDrugForm.getEntpName().split(" ");	
		String[] drugShapes = findDrugForm.getDrugShape().split(" ");
		String[] drugColors = findDrugForm.getDrugColor().split(" ");
		String[] drugPrints = findDrugForm.getDrugPrint().split("");
		String[] drugLineFronts = findDrugForm.getDrugLineFront().split(" ");
		String[] drugLineBacks = findDrugForm.getDrugLineBack().split(" ");
		String[] drugMarks = findDrugForm.getDrugMark().split(" ");
		List<DrugInfo> drugInfoList = drugsMapper.selectDrugInfoByFindDrugForm(itemNames,ingrNames,entpNames,drugShapes,drugColors,drugPrints,drugLineFronts,drugLineBacks,drugMarks);
		//DB에서 가져와서 리스트 반환
		return drugInfoList;
	}
	
	/**
	 * FindDrugForm을 기반으로 DrugInfo 객체로 이루어진 리스트로 반환
	 * 목록용 객체로, 내용은 itemSeq, itemName, itemImage, entpName 만 가져온다.
	 * @param: FindDrugForm
	 * @return: ArrayList<DrugInfo>
	 * 담당자 : 홍준표
	 */
	@Override
	public List<DrugInfo> getDrugInfoListAllByFindDrugForm(FindDrugForm findDrugForm) {
		String[] itemNames = findDrugForm.getItemName().split(" ");			
		String[] ingrNames = findDrugForm.getIngrName().split(" ");			
		String[] entpNames = findDrugForm.getEntpName().split(" ");	
		List<DrugInfo> drugInfoList = drugsMapper.selectDrugInfoAllByFindDrugForm(itemNames,ingrNames,entpNames);
		//DB에서 가져와서 리스트 반환
		return drugInfoList;
	}

	/**
	 * DB에서 itemSeq를 찾아 DrugInfo 객체 반환
	 * @param: String(itemSeq)
	 * @return: DrugInfo
	 * 담당자 : 홍준표
	 */
	@Override
	public DrugInfo getDrugInfoByItemSeq(String itemSeq) {
		DrugInfo drugInfo = drugsMapper.selectDrugInfoByItemSeq(itemSeq);
		drugInfo.setIngrNameList(drugsMapper.selectIngrListByItemSeq(itemSeq));
		return drugInfo;
	}
	
	/**
	 * DB에서 모든 DrugInfo 객체 반환
	 * @return: List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	@Override
	public Integer getDrugInfoCountAll() {
		Integer count = drugsMapper.selectDrugInfoCountAll();
		return count;
	}
	
	/**
	 * DB에서 모든 drugMark와 itemSeq를 List형태로 반환
	 * @return: List<FindDrugForm>
	 * 담당자 : 홍준표
	 */
	@Override
	public List<DrugMark> getDrugMarkAll(){
		List<DrugMark> drugMarkAll = drugsMapper.selectDrugMarkAll();
		return drugMarkAll;
	}

	/**
	 * DB에서 DUR ItemSeq의 주의사항을 넘겨받음
	 * @param: String(itemSeq)
	 * @return: List<String>(typeName) 
	 */
	public List<String> getDurWarningByItemSeq(String itemSeq) {
		List<String> warningList = drugsMapper.selectTypeNameByItemSeq(itemSeq);
		return warningList;
	}
	
	/**
	 * DB에서 DUR_Combi의 병용금기 성분코드를 넘겨받음
	 * @param: String(itemSeq)
	 * @return: List<String>(mixIngrName) 
	 */
	public List<String> getDurCombiIngrNameByItemSeq(String itemSeq){
		List<String> warningCombiList = drugsMapper.selectCombiIngrNameByItemSeq(itemSeq);
		return warningCombiList;
	}
	
	public List<DrugsNameForm> getDrugsNameFormList(){
		List<DrugsNameForm> drugNameFormList = drugsMapper.selectDrugsNameAll();
		return drugNameFormList;
	}

/* UPDATE 관련 메서드 */
	/**
	 * DurgInfo 객체를 받아와서 해당 DB의 ITEM_IMAGE열을 업데이트
	 * @param: DrugInfo
	 * 담당자 : 홍준표
	 */
	public Integer updateDrugInfoImage(DrugInfo drugInfo) {
		Integer result = drugsMapper.updateDrugInfoImage(drugInfo);
		return result;
	}
	
	
	
	
	
	
	
	
/* DELETE 관련 메서드 (사용주의) */
	
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
	
	/**
	 * DB의 dur 테이블을 모두 삭제
	 * Dur 테이블을 최신화할때 사용함.
	 * 담당자 : 홍준표
	 */
	@Override
	public void deleteDur() {
		// TODO Auto-generated method stub
		drugsMapper.deleteDur();
	}
	
	/**
	 * DB의 dur_combi 테이블을 모두 삭제
	 * Dur 테이블을 최신화할때 사용함.
	 * 담당자 : 홍준표
	 */
	@Override
	public void deleteDurCombi() {
		// TODO Auto-generated method stub
		drugsMapper.deleteDurCombi();
	}
	
}
