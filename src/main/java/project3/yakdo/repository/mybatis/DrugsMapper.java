package project3.yakdo.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.Dur;
import project3.yakdo.domain.drugs.DurCombi;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.service.drugs.search.FindDrugForm;

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
	public List<DrugInfo> selectDrugInfoAll(@Param("startNum") int startNum,@Param("endNum") int endNum);
	public List<DrugInfo> selectDrugInfoByFindDrugForm(@Param("itemNames") String[] itemNames, @Param("ingrNames") String[] ingrNames,
			@Param("entpNames") String[] entpNames, @Param("drugShapes") String[] drugShapes, @Param("drugColors") String[] drugColors,
			@Param("drugPrints") String[] drugPrints, @Param("drugLines") String[] drugLines, @Param("drugMarks") String[] drugMarks);

	//delete
	public void deleteDrugInfo();
	public void deleteDrugInfoIngr();
	public void deleteFindDrug();
	public void deleteDur();
	public void deleteDurCombi();
}
