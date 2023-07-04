package com.uniqueGames.service;

import com.uniqueGames.model.Member;

public interface MemberService {
	
	int memberLoginResult(Member member);
	//int getLoginResult(Object vo);
	int memberJoinResult(Member member);
	int memberIdCheckResult(String memberId);
	String memberFindIdResult(Member member);
	int memberFindPwdResult(Member member);
	int memberUpdateResult(Member member);
	int memberChangePwdResult(String memberId, String name, String phone_num);
	int memberChangeMPassword(String memberId, String mnewpassword);
	Member memberMyPageResult(String memberId);
	int memberDeleteResult(Member member);
	int memberEmailCheckResult(String email);
	int memberPhoneCheckResult(String phone_num);

}
