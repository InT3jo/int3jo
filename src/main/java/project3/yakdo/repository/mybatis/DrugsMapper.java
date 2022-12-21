package project3.yakdo.repository.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.Dur;
import project3.yakdo.domain.drugs.DurCombi;
import project3.yakdo.domain.drugs.FindDrug;

@Mapper
public interface DrugsMapper {
	//insert
	public Integer insertDrugInfo(DrugInfo drugInfo);
	public Integer insertDrugInfoIngr(@Param("itemSeq")String itemSeq, @Param("ingrName")String ingrName);
	public Integer insertFindDrug(FindDrug findDrug);
	public Integer insertDur(Dur dur);
	public Integer insertDurCombi(DurCombi dur);

	//update
	public Integer updateDrugInfo(Map<String, String> col);
	
	//select
	public DrugInfo selectDrugInfoByItemSeq(@Param("itemSeq") String itemSeq);
//	public List<DrugInfo> getDrugInfoList();
//	public List<String> getDrtgInfoIngrList();
//	public List<FindDrug> getDrugFindInfoList();

	//delete
	public void deleteDrugInfo();
	public void deleteDrugInfoIngr();
	public void deleteFindDrug();
	public void deleteDur();
	public void deleteDurCombi();
}
