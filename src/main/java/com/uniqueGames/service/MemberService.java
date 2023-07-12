package com.uniqueGames.service;

import com.uniqueGames.model.Member;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {
	
	int memberLoginResult(Member member);
	//int getLoginResult(Object vo);
	int memberJoinResult(Member member);
	int memberIdCheckResult(String memberId);
	String memberFindIdResult(Member member);
	int memberFindPwdResult(Member member);
	int memberUpdateResult(Member member);
	int memberChangeMPassword(String memberId, String mnewpassword);
	Member memberMyPageResult(String memberId);
	int memberDeleteResult(Member member);
	int memberEmailCheckResult(String email);
	int memberPhoneCheckResult(String phoneNum);
	ArrayList<Member> aGetMemberList();

}
