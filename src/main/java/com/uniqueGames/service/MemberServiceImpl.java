package com.uniqueGames.service;


import com.uniqueGames.model.Member;
import com.uniqueGames.repository.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public int memberLoginResult(Member member) {
		return memberDao.login(member);
	}

	@Override
	public int memberJoinResult(Member member) {
		
		return memberDao.insert(member);
	}

	@Override
	public int memberIdCheckResult(String memberId) {
		return memberDao.idCheck(memberId);
	}
	
	@Override
	public String memberFindIdResult(Member member) {
		return memberDao.findIdCheck(member);
	}
	 
	@Override
	public int memberFindPwdResult(Member member) {
		return memberDao.select(member);
	}

	@Override
	public int memberUpdateResult(Member member) {
		return memberDao.update(member);
	}

	@Override
	public int memberChangePwdResult(String memberId, String name, String phone_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memberChangeMPassword(String memberId, String mnewpassword) {
		return memberDao.changeMpassword(memberId, mnewpassword);
	}

	@Override
	public Member memberMyPageResult(String memberId) {
		return memberDao.myPage(memberId);
	}

	@Override
	public int memberDeleteResult(Member member) {
		return memberDao.delete(member);
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
