package com.uniqueGames.service;


import com.uniqueGames.model.MemberVo;
import com.uniqueGames.repository.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public int memberLoginResult(MemberVo memberVo) {
		return memberDao.login(memberVo);
	}

	@Override
	public int memberJoinResult(MemberVo memberVo) {
		
		return memberDao.insert(memberVo);
	}
	@Override
	public String memberIdCheckResult(String member_id) {
		int result = memberDao.idCheck(member_id);
		return String.valueOf(result);
	}
	
	@Override
	public String memberFindIdResult(MemberVo memberVo) {
		return memberDao.findIdCheck(memberVo);
	}
	 
	@Override
	public int memberFindPwdResult(MemberVo memberVo) {
		return memberDao.select(memberVo);
	}

	@Override
	public int memberUpdateResult(MemberVo memberVo) {
		return memberDao.update(memberVo);
	}

	@Override
	public int memberChangePwdResult(String member_id, String name, String phone_num) {
		return 0;
	}

	@Override
	public int memberChangeMPassword(String member_id, String mnewpassword) {
		return memberDao.changeMpassword(member_id, mnewpassword);
	}

	@Override
	public MemberVo memberMyPageResult(String member_id) {
		return memberDao.myPage(member_id);
	}

	@Override
	public int memberDeleteResult(MemberVo memberVo) {
		return memberDao.delete(memberVo);
	}

	@Override
	public int memberEmailCheckResult(String email) {
		
		return memberDao.emailCheck(email);
	}

	@Override
	public int memberPhoneCheckResult(String phone_num) {
		return memberDao.phoneCheck(phone_num);
	}

	

}
