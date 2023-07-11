package com.uniqueGames.service;

import com.uniqueGames.model.Member;
import com.uniqueGames.repository.MemberMapper;
import com.uniqueGames.repository.MemberRepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberRepositoryMapper memberRepositoryMapper;

    @Autowired
	private MemberMapper memberMapper;

	public int save(Member member) {
		return memberMapper.save(member);
	}
	public int idCheck(String memberId) {
		return memberMapper.idCheck(memberId);
	}
	public String findMid(String email, String name) {
		Member member = new Member();
		member.setEmail(email);
		member.setName(name);
		return memberMapper.findMid(member);
	}

	public String findMpass(String email, String memberId, String name) {
		Member member = new Member();
		member.setEmail(email);
		member.setMemberId(memberId);
		member.setName(name);
		return memberMapper.findMpass(member);
	}

	public int changeMpass(String memberId, String mnewpassword) {
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMnewpassword(mnewpassword);
		return memberMapper.changeMpass(member);
	}

	public int phoneCheck(String phoneNum) {
		return memberMapper.phoneCheck(phoneNum);
	}

	public int emailCheck(String email) {
		return memberMapper.emailCheck(email);
	}

}
