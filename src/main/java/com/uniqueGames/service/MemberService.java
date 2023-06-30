package com.uniqueGames.service;

import com.uniqueGames.model.MemberVo;

public interface MemberService {
	
	int memberLoginResult(MemberVo memberVo);
	//int getLoginResult(Object vo);
	int memberJoinResult(MemberVo memberVo);
	String memberIdCheckResult(String member_id);
	String memberFindIdResult(MemberVo memberVo);
	int memberFindPwdResult(MemberVo memberVo);
	int memberUpdateResult(MemberVo memberVo);
	int memberChangePwdResult(String member_id, String name, String phone_num);
	int memberChangeMPassword(String member_id, String mnewpassword);
	MemberVo memberMyPageResult(String member_id);
	int memberDeleteResult(MemberVo memberVo);
	int memberEmailCheckResult(String email);
	int memberPhoneCheckResult(String phone_num);

}
