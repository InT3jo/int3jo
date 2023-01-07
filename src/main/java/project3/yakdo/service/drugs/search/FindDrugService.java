package project3.yakdo.service.drugs.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.domain.drugs.DrugsNameForm;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.users.LoginService;

@Service
@RequiredArgsConstructor
public class FindDrugService {
	
	private final DrugsRepository drugsRepository;
	private final LoginService loginService;
	
	/**
	 * findDrugForm 기반으로 DrugInfo List를 반환
	 * @param: FindDrugForm
	 * @return: List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	public List<DrugInfo> findDrugResult(FindDrugForm findDrugForm){
		List<DrugInfo> diList = drugsRepository.getDrugInfoListByFindDrugForm(findDrugForm);
		return diList;
	}
	
	/**
	 * findDrugForm 기반으로 DrugInfo List를 반환
	 * @param: FindDrugForm
	 * @return: List<DrugInfo>
	 * 담당자 : 홍준표
	 */
	public List<DrugInfo> findDrugResultAll(FindDrugForm findDrugForm){
		List<DrugInfo> diList = drugsRepository.getDrugInfoListAllByFindDrugForm(findDrugForm);
		return diList;
	}

	/**
	 * 200개 이상인 tempMarkList를 40개 단위로 쪼개어 drugMarkList에 담고, 총 페이지를 drugMarkPage에 담음
	 * @param: List<List<DrugMark>>
	 * @param: List<Integer>
	 * 담당자 : 홍준표
	 */
	public void setMarkListAndMarkPage(List<List<DrugMark>> drugMarkList, List<Integer> drugMarkPage) {
		// TODO Auto-generated method stub
		List<DrugMark> tempMarkList = drugsRepository.getDrugMarkAll();
		int divide = 40;
		int index = 0;
		int page = 1;
		for(int i=0;i<(tempMarkList.size()/divide)+1;i++) {
			List<DrugMark> tempList=new ArrayList<>();
			for(int j=0;j<divide;j++) {
				if(index >= tempMarkList.size()) {
					break;
				}
				tempList.add(tempMarkList.get(index));
				index++;
			}
			drugMarkList.add(tempList);
			drugMarkPage.add(page++);
		}
	}
	
	/**
	 * FindDrugForm을 기반으로 DrugInfo를 List로 받음
	 * @param: FindDrugForm
	 * @param: HttpServletRequest
	 * @return List<DrugInfo>
	 * 담당자: 홍준표
	 */
	public List<DrugInfo> setFindDrugInfoList(FindDrugForm findDrugForm, HttpServletRequest req) {
		List<DrugInfo> findDrugInfoList = new ArrayList<>();
		if(req.getParameter("searchAll") != null) { //통합검색일때 findDrugForm을 만들어서 검색결과를 가져옴
			findDrugForm.setItemName(req.getParameter("searchAll"));
			findDrugForm.setIngrName(req.getParameter("searchAll"));
			findDrugForm.setEntpName(req.getParameter("searchAll"));
			findDrugForm.setDrugShape("");
			findDrugForm.setDrugColor("");
			findDrugForm.setDrugPrint("");
			findDrugForm.setDrugLineFront("");
			findDrugForm.setDrugLineBack("");
			findDrugForm.setDrugMark("");
			findDrugInfoList = findDrugResultAll(findDrugForm);
		}else { //상세검색일때 fimdDrugForm으로 검색결과를 가져옴
			findDrugInfoList = findDrugResult(findDrugForm);			
		}
		return findDrugInfoList;
	}

	/**
	 * Users와 DrugInfo를 받아, 해당되는 경고문을 작성하여 반환
	 * @param: Users
	 * @param: DrugInfo
	 * @return: String(wraningMessage)
	 */
	public String getWarningMessage(Users user, DrugInfo drugInfo) {
		String warningMessage = "";
		if(user != null) {
			// 유저의 가족들 정보( UsersInfoList ) by userNo
			List<UsersInfo> usersInfoList = loginService.getUsersInfoListByUserNo(user.getUserNo());
			// 약물의 DUR 위험정보( DURList ) by itemSeq
			List<String> warningList = drugsRepository.getDurWarningByItemSeq(drugInfo.getItemSeq());
			// 약물의 병용금기 정보( DurCombiList ) by itemSeq
			List<String> warningCombiIngrNameList = drugsRepository.getDurCombiIngrNameByItemSeq(drugInfo.getItemSeq());
			warningMessage = setWarningMessage(drugInfo, warningMessage, usersInfoList, warningList, warningCombiIngrNameList);
		}
		return warningMessage;
	}

	private String setWarningMessage(DrugInfo drugInfo, String warningMessage, List<UsersInfo> usersInfoList,
			List<String> warningList, List<String> warningCombiIngrCodeList) {
		for(UsersInfo usersInfo : usersInfoList) {
			// 복용중약 성분과 병용금기 성분 대조하여 경고문 설정
			warningMessage = setCombiWarningMessage(drugInfo, warningMessage, usersInfo, warningCombiIngrCodeList);
			// 알러지약 성분과 현재약 성분 대조하여 경고문 설정
			warningMessage = setAllergyWarningMessage(drugInfo, warningMessage, usersInfo);
			int userAge = setUserAge(usersInfo);
			for(String warning : warningList) {
				// 특정연령대금기 - 12세미만 65세 이상 주의 경고문 설정
				warningMessage = setAgeWarnningMessage(warningMessage, usersInfo, userAge, warning);
				// 임부금기 - 유저인포 20-49세 여성 경고문 설정
				warningMessage = setPregnantWarningMessage(warningMessage, usersInfo, userAge, warning);
			}
		}
		for(String warning : warningList) {
			// 투여기간주의 - 전체
			warningMessage = setPeriodWarningMessage(warningMessage, warning);
			// 분할주의 - 전체
			warningMessage = setCutWarningMessage(warningMessage, warning);
		}
		// 마약류 취급주의 - 전체
		warningMessage = setNarcoticWarningMessage(drugInfo, warningMessage);
		return warningMessage;
	}

	private String setCombiWarningMessage(DrugInfo drugInfo, String warningMessage, UsersInfo usersInfo, List<String> warningCombiIngrNameList) {
		for(String usingDrug : usersInfo.getUsingDrugList()) {
			DrugInfo usingDrugInfo = drugsRepository.getDrugInfoByItemSeq(usingDrug);
			for(String combiIngrName : warningCombiIngrNameList) {
				for(String ingrName : usingDrugInfo.getIngrNameList()) {
					if(ingrName.indexOf(combiIngrName) > 0) {
						warningMessage += usersInfo.getFamilyNick() + "님이 복용중인 "+ usingDrugInfo.getItemName() +"과(와) 함께 복용할 수 없는 약입니다.\n";
					}
				}
			}
		}
		return warningMessage;
	}

	private String setAllergyWarningMessage(DrugInfo drugInfo, String warningMessage, UsersInfo usersInfo) {
		for(String allergyDrug : usersInfo.getAllergyList()) {
			DrugInfo allergyDrugInfo = drugsRepository.getDrugInfoByItemSeq(allergyDrug);
			for(String allergyIngrName : allergyDrugInfo.getIngrNameList()) {
				for(String ingrName : drugInfo.getIngrNameList()) {
					if(ingrName.equals(allergyIngrName)) {
						warningMessage += usersInfo.getFamilyNick() + "님은 " + drugInfo.getItemName() +"에 알러지가 있을 수 있습니다.\n";
					}
				}					
			}
		}
		return warningMessage;
	}

	private int setUserAge(UsersInfo usersInfo) {
		Date userBirth = usersInfo.getBirth();
		Date nowDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String userBirthString = format.format(userBirth);
		String nowDateString = format.format(nowDate);
		int userAge = Integer.parseInt(nowDateString) - Integer.parseInt(userBirthString);
		return userAge;
	}

	private String setAgeWarnningMessage(String warningMessage, UsersInfo usersInfo, int userAge, String warning) {
		if(warning.equals("특정연령대금기")) {
			if(userAge < 12 || userAge >= 65) {
				warningMessage += usersInfo.getFamilyNick() + "님은 복용가능연령에 주의가 필요합니다.\n";
				return warningMessage;
			}
		}
		return warningMessage;
	}

	private String setPregnantWarningMessage(String warningMessage, UsersInfo usersInfo, int userAge, String warning) {
		if(warning.equals("임부금기")) {
			if(userAge > 19 && userAge < 51 && usersInfo.getGender().equals("여자")) {
				warningMessage += usersInfo.getFamilyNick() + "님은 임신 가능성에 주의가 필요합니다.\n";
				return warningMessage;
			}
		}
		return warningMessage;
	}

	private String setPeriodWarningMessage(String warningMessage, String warning) {
		if(warning.equals("투여기간주의")) {
			warningMessage += "이 약은 투여기간에 주의가 필요합니다. 의/약사와 상담을 권장합니다.\n";
			return warningMessage;
		}
		return warningMessage;
	}

	private String setCutWarningMessage(String warningMessage, String warning) {
		if(warning.equals("분할주의")) {
			warningMessage += "이 약은 분할하면(자르면) 효능저하나 부작용이 발생할 수 있습니다.\n";
			return warningMessage;
		}
		return warningMessage;
	}

	private String setNarcoticWarningMessage(DrugInfo drugInfo, String warningMessage) {
		if(drugInfo.getNarcotic()!=null && !drugInfo.getNarcotic().equals("null")) {
			warningMessage += "이 약은 마약류 약품으로, 취급에 주의를 요하며, 폐기시 주변 병원이나 약국으로 가져다 주시기 바랍니다.\n";
			return warningMessage;
		}
		return warningMessage;
	}

	public List<DrugsNameForm> getDrugsNameFormList() {
		List<DrugsNameForm> drugNameList = drugsRepository.getDrugsNameFormList();
		return drugNameList;
	}
}
