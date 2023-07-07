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

	private MemberMapper memberMapper;

	public MemberService(MemberRepositoryMapper memberRepositoryMapper, MemberMapper memberMapper) {
		this.memberRepositoryMapper = memberRepositoryMapper;
		this.memberMapper = memberMapper;
	}

	public int save(Member member) {
		return memberMapper.save(member);
	}


	public int idCheck(String memberId) {
		return memberRepositoryMapper.idCheck(memberId);
	}

}
