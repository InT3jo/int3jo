package project3.yakdo.service.users;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.PasswordForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
	private final UsersRepository usersRepository;
//	private final UsersValidator usersValidator;
	/**
	 * 입력받은 userNick과 기존 loginUser의 userNick이 같은지 확인
	 * 같으면 닉네임 수정 된 loginUser return
	 * 아니면 기존 loginUser return
	 * @param userNick
	 * @return Users loginUser
	 */
	public Users checkModifyNick(String userNick, Users loginUser) {
		/**
		 * 입력 받은 닉네임이 비어 있을 때
		 * 닉네임을 입력해 주세요 를 띄워야하고
		 * 
		 * 기존 닉네임과 새로 입력받은 닉네임이 같고
		 * 업데이트가 실패했을 때
		 * 사용 중인 닉네임입니다 띄우는 validation 만들어야함
		 */
//		usersValidator
		Integer validateResult = usersRepository.updateUserNickByUserNo(userNick, loginUser.getUserNo());
		//업데이트 성공 했을 시 loginUser의 닉네임 변경
		if(validateResult == 1) {
			//loginUser 닉네임 수정
			loginUser.setUserNick(userNick);
			return loginUser;
		}
		
		//닉네임 수정 안 된 loginUser
		return loginUser;
	}

	/**
	 * 기존 비밀 번호가 올바르게 입력 되었는지 확인 후
	 * 새 비밀번호로 변경 => 로그인 화면으로 넘어가야하기에 user를 null로 반환
	 * 비밀번호 변경에 실패했을 시 => 기존 user 반환
	 * @param passwordForm
	 * @param loginUser
	 * @return
	 */
	public Users checkModifyPw(PasswordForm passwordForm, Users loginUser) {
		/**
		 * 기존 비밀번호 맞는지 확인
		 * 새 비밀번호, 비밀번호 확인 맞는지 확인
		 * 셋 다 확인 하고 하나라도 맞지 않을 시
		 * 다시 한 번 확인해주세요 띄우기
		 * 조건 안 걸어서 무조건 null로 반환된다
		 */
		//업데이트 성공 했을 시 loginUser의 비밀번호 변경 후 로그인 하기 위해 null로 반환
		if(usersRepository.updateUserPwByUserNo(passwordForm.getUserPwNew(), loginUser.getUserNo()) == 1) {
			loginUser.setUserPw(passwordForm.getUserPwNew());
			return null;
		}
		
		//비밀번호 수정 안 됐으면 기존 loginUser return
		return loginUser;
	}

	
	/**
	 * 회원 탈퇴 실행하는 서비스
	 * @param userNo
	 * @param user 
	 */
	public void leaveUser(Integer userNo) {
		usersRepository.updateUserStatusLeaveByUserNo(userNo);
	}
	
	
	/**
	 * UserEmail의 활동 상태 찾기
	 * @param userEmail
	 * @return
	 */
	public Integer searchUserStatus(Model model, String userEmail) {
		List<Users> userList = usersRepository.selectUserAllByUserEmail(userEmail);
		log.info("userList---------------------- {}", userList);
		Integer result = 0;
		if(userList.isEmpty()) {
			log.info("?????????????????????????????????");
			return null;
		}
		for(Users user : userList) {
			if(user.getUserStatus() == 2) {
				/* 블락된 계정입니다 */
				result = 2;
			}
			if(user.getUserStatus() == 1) {
				/* 존재하지 않는 계정입니다 */
				result = 1;
			}
		}
		return result;
	}

	/**
	 * 비밀번호 변경 기능
	 * 
	 * @param userEmail 
	 * @param passwordForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	public Integer changePassword(String userEmail, PasswordForm passwordForm) {
		Users user = usersRepository.selectUserByUserEmail(userEmail);
		Integer result = usersRepository.updateUserPwByUserNo(passwordForm.getUserPwNew(), user.getUserNo());
		if(result == 1) {
			user.setUserPw(passwordForm.getUserPwNew());
			return result;
		}
		return result;
	}

	
	/**
	 * user 찾기
	 * @param userEmail
	 */
	public Users searchUser(String userEmail) {
		Users user = usersRepository.selectUserByUserEmail(userEmail);
		return user;
	}

	/**
	 * HttpServletRequest로 받아온 정보를 UsersInfo 테이블에 추가
	 * @param Users
	 * @param HttpServletRequest
	 */
	public void addUsersInfo(Users user, HttpServletRequest req) {
		UsersInfo usersInfo = makeUsersInfo(user, req);
		usersRepository.insertUsersInfo(usersInfo);	
	}

	private UsersInfo makeUsersInfo(Users user, HttpServletRequest req) {
		UsersInfo usersInfo = new UsersInfo();
		usersInfo.setUserNo(user.getUserNo());
		usersInfo.setBirth(Date.valueOf(req.getParameter("birth")));
		usersInfo.setGender(req.getParameter("gender"));
		usersInfo.setWeight(Double.parseDouble(req.getParameter("weight")));
		usersInfo.setFamilyNick(req.getParameter("familyNick"));
		usersInfo.setUsingDrugList(getUsingDrugList(req));
		usersInfo.setAllergyList(getAllergyList(req));
		return usersInfo;
	}
	private List<String> getAllergyList(HttpServletRequest req) {
		List<String> allergyList = new ArrayList<String>();
		int allergyDrugNo = 1;
		while(true) {
			String allergy = req.getParameter("allergy" + allergyDrugNo);
			if(allergy == null) {
				break;
			}
			allergyList.add(allergy);
			allergyDrugNo++;
		}
		return allergyList;
	}

	private List<String> getUsingDrugList(HttpServletRequest req) {
		List<String> usingDrugList = new ArrayList<String>();
		int usingDrugNo = 1;
		while(true) {
			String usingDrug = req.getParameter("usingDrug" + usingDrugNo);
			if(usingDrug == null) {
				break;
			}
			usingDrugList.add(usingDrug);
			usingDrugNo++;
		}
		return usingDrugList;
	}

	/**
	 * HttpServletRequest로 받아온 정보로 UsersInfo 테이블 수정
	 * @param Users
	 * @param HttpServletRequest
	 */
	public void updateUsersInfo(Users user, HttpServletRequest req, Integer familyNo) {
		// TODO Auto-generated method stub
		UsersInfo usersInfo = makeUsersInfo(user, req);
		usersInfo.setFamilyNo(familyNo);
		usersRepository.updateUsersInfo(usersInfo);	
	}

	/**
	 * HttpServletRequest로 받아온 정보로 UsersInfo 테이블 삭제
	 * @param Users
	 * @param HttpServletRequest
	 */
	public void deleteUsersInfo(Users user, HttpServletRequest req, Integer familyNo) {
		// TODO Auto-generated method stub
		UsersInfo usersInfo = new UsersInfo();
		usersInfo.setUserNo(user.getUserNo());
		usersInfo.setFamilyNo(familyNo);
		usersRepository.deleteUsersInfo(usersInfo);	
	}
	
	

}
