package project3.yakdo.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
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
	public Integer updateDrugInfoImage(DrugInfo drugInfo);
	
	//select
	public List<String> selectIngrListByItemSeq(@Param("itemSeq") String itemSeq);
	public DrugInfo selectDrugInfoByItemSeq(@Param("itemSeq") String itemSeq);
	public Integer selectDrugInfoCountAll();
	public List<DrugMark> selectDrugMarkAll();
	public List<DrugInfo> selectDrugInfoByFindDrugForm(@Param("itemNames") String[] itemNames, @Param("ingrNames") String[] ingrNames,
			@Param("entpNames") String[] entpNames, @Param("drugShapes") String[] drugShapes, @Param("drugColors") String[] drugColors,
			@Param("drugPrints") String[] drugPrints, @Param("drugLineFronts") String[] drugLineFronts,
			@Param("drugLineBacks") String[] drugLineBacks, @Param("drugMarks") String[] drugMarks);
	public List<DrugInfo> selectDrugInfoAllByFindDrugForm(@Param("itemNames") String[] itemNames, @Param("ingrNames") String[] ingrNames,
			@Param("entpNames") String[] entpNames);
	public List<String> selectTypeNameByItemSeq(String itemSeq);
	public List<String> selectCombiIngrNameByItemSeq(String itemSeq);
	
	//delete
	public void deleteDrugInfo();
	public void deleteDrugInfoIngr();
	public void deleteFindDrug();
	public void deleteDur();
	public void deleteDurCombi();
	
	
}
