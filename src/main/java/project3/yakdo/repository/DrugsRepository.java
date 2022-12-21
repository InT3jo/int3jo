package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.drugs.FindDrug;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.Dur;
import project3.yakdo.domain.drugs.DurCombi;

public interface DrugsRepository {

	public Integer insertDrugInfo(List<DrugInfo> drugInfoList);
	public Integer insertFindDrug(List<FindDrug> findDrugList);
	public Integer insertDur(List<Dur> durList);
	public Integer insertDurCombi(List<DurCombi> durList);
	public List<DrugInfo> getDrugInfoList();
	public List<FindDrug> getDrugFindInfoList();
	public void deleteDrugInfo();
	public void deleteDrugInfoIngr();
	public void deleteFindDrug();
	public void deleteDur();
	public void deleteDurCombi();
}
