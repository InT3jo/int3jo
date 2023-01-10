package project3.yakdo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project3.yakdo.service.drugs.search.FindDrugForm;

public class FindDrugValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FindDrugForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		FindDrugForm findDrugForm = (FindDrugForm) target;
		if (( findDrugForm.getDrugColor() == null || findDrugForm.getDrugColor().trim().equals(""))
				&& ( findDrugForm.getDrugLineBack() == null || findDrugForm.getDrugLineBack().trim().equals(""))
				&& ( findDrugForm.getDrugLineFront() == null || findDrugForm.getDrugLineFront().trim().equals(""))
				&& ( findDrugForm.getDrugMark() == null || findDrugForm.getDrugMark().trim().equals(""))
				&& ( findDrugForm.getDrugPrint() == null || findDrugForm.getDrugPrint().trim().equals(""))
				&& ( findDrugForm.getDrugShape() == null || findDrugForm.getDrugShape().trim().equals(""))
				&& ( findDrugForm.getEntpName() == null || findDrugForm.getEntpName().trim().equals(""))
				&& ( findDrugForm.getIngrName() == null || findDrugForm.getIngrName().trim().equals(""))
				&& ( findDrugForm.getItemName() == null || findDrugForm.getItemName().trim().equals(""))) {
			errors.reject("최소 한가지 이상의 검색조건이 필요합니다.",null);
		}
	}
}
