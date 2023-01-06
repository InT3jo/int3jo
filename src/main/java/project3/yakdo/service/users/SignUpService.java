package project3.yakdo.service.users;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.SignUpForm;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {
	private final UsersRepository usersRepository;
	
	/**
	 * SignUp 페이지에서 입력한 값을 가진 UsersInfo List를 반환
	 * userNo와 familyNo는 null
	 * @param HttpServletRequest
	 * @return List<UsersInfo>
	 */
	public List<UsersInfo> getUsersInfoList(HttpServletRequest req) {
		List<UsersInfo> usersInfoList = new ArrayList<UsersInfo>();
		int i= 1;
		while(true) {
			if(req.getParameter("familyNick"+i) == null) {
				break; 
			}
			UsersInfo userInfo = getUsersInfo(req, i);
			usersInfoList.add(userInfo);
			i++;
		}
		return usersInfoList;
	}

	private UsersInfo getUsersInfo(HttpServletRequest req, int i) {
		UsersInfo userInfo = new UsersInfo();
		userInfo.setFamilyNick(req.getParameter("familyNick"+i));
		userInfo.setBirth(Date.valueOf(req.getParameter("birth"+i)));
		userInfo.setGender(req.getParameter("gender"+i));
		userInfo.setWeight(Double.parseDouble(req.getParameter("weight"+i)));
		userInfo.setUsingDrugList(getUsingDrugList(req,i));
		userInfo.setAllergyList(getAllergyList(req,i));
		return userInfo;
	}

	private List<String> getAllergyList(HttpServletRequest req, int i) {
		List<String> allergyList = new ArrayList<String>();
		int allergyDrugNo = 1;
		while(true) {
			String allergy = req.getParameter("allergy" + i + "and" + allergyDrugNo);
			if(allergy == null) {
				break;
			}
			allergyList.add(allergy);
			allergyDrugNo++;
		}
		return allergyList;
	}

	private List<String> getUsingDrugList(HttpServletRequest req, int i) {
		List<String> usingDrugList = new ArrayList<String>();
		int usingDrugNo = 1;
		while(true) {
			String usingDrug = req.getParameter("usingDrug" + i + "and" + usingDrugNo);
			if(usingDrug == null) {
				break;
			}
			usingDrugList.add(usingDrug);
			usingDrugNo++;
		}
		return usingDrugList;
	}
	
	/**
	 * 회원 가입 실행
	 * @param signUpForm
	 * @param usersInfoList
	 */
	@Transactional
	public void signUpUsersAndUsersInfo(SignUpForm signUpForm, List<UsersInfo> usersInfoList) {
		usersRepository.insertUsers(signUpForm);
		Integer userNo = usersRepository.selectUserByUserEmail(signUpForm.getUserEmail()).getUserNo();
		for(UsersInfo userInfo : usersInfoList) {
			userInfo.setUserNo(userNo);
			usersRepository.insertUsersInfo(userInfo);
		}
	}

	
}
