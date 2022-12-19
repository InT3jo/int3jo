package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.FindDrug;

@Mapper
public interface DrugsMapper {
	public Integer insertDrugInfo(@Param("drugInfo") DrugInfo drugInfo);
	public Integer insertDrugInfoIngrList(@Param("itemSeq")String itemSeq, @Param("ingrNameList")List<String> ingrNameList);
	public Integer insertFindDrug(FindDrug findDrug);
//	public List<DrugInfo> getDrugInfoList();
//	public List<String> getDrtgInfoIngrList();
//	public List<FindDrug> getDrugFindInfoList();
	public void deleteDrugInfo();
	public void deleteDrugInfoIngr();
	public void deleteFindDrug();
}
