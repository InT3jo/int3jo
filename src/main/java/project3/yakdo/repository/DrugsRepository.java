package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.domain.drugs.DrugsNameForm;
import project3.yakdo.domain.drugs.Dur;
import project3.yakdo.domain.drugs.DurCombi;
import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.service.drugs.search.FindDrugForm;

public interface DrugsRepository {
	/* INSERT 메서드 */
	public Integer insertDrugInfo(List<DrugInfo> drugInfoList);
	public Integer insertFindDrug(List<FindDrug> findDrugList);
	public Integer insertDur(List<Dur> durList);
	public Integer insertDurCombi(List<DurCombi> durList);
	/* SELECT 메서드 */
	public DrugInfo getDrugInfoByItemSeq(String itemSeq);
	public Integer getDrugInfoCountAll();
	public List<DrugInfo> getDrugInfoListByFindDrugForm(FindDrugForm findDrugForm);
	public List<DrugInfo> getDrugInfoListAllByFindDrugForm(FindDrugForm findDrugForm);
	public List<DrugMark> getDrugMarkAll();
	public List<String> getDurWarningByItemSeq(String itemSeq);
	public List<String> getDurCombiIngrNameByItemSeq(String itemSeq);
	public List<DrugsNameForm> getDrugsNameFormList();
	
	/* UPDATE 메서드 */
	 public Integer updateDrugInfoImage(DrugInfo drugInfo);
	
	/* DELETE 메서드 */
	public void deleteDrugInfo();
	public void deleteDrugInfoIngr();
	public void deleteFindDrug();
	public void deleteDur();
	public void deleteDurCombi();
	
}
