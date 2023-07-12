package com.uniqueGames.service;


import com.uniqueGames.model.Member;
import com.uniqueGames.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public int memberLoginResult(Member member) {
        return memberRepository.login(member);
    }

    @Override
    public int memberJoinResult(Member member) {

        return memberRepository.insert(member);
    }

    @Override
    public int memberIdCheckResult(String memberId) {
        return memberRepository.idCheck(memberId);
    }

    @Override
    public String memberFindIdResult(Member member) {
        return memberRepository.findIdCheck(member);
    }

    @Override
    public int memberFindPwdResult(Member member) {
        return memberRepository.select(member);
    }

    @Override
    public int memberUpdateResult(Member member) {
        return memberRepository.update(member);
    }

    @Override
    public int memberChangeMPassword(String memberId, String mnewpassword) {
        return memberRepository.changeMpassword(memberId, mnewpassword);
    }

    @Override
    public Member memberMyPageResult(String memberId) {
        return memberRepository.myPage(memberId);
    }

    @Override
    public int memberDeleteResult(Member member) {
        return memberRepository.delete(member);
    }

    @Override
    public int memberEmailCheckResult(String email) {
        return memberRepository.emailCheck(email);
    }

    @Override
    public int memberPhoneCheckResult(String phoneNum) {
        return memberRepository.phoneCheck(phoneNum);
    }

    @Override
    public ArrayList<Member> aGetMemberList() {
        ArrayList<Member> mList = new ArrayList<>();
        for (Member member : memberRepository.aGetMemberList()) {
            mList.add(member);
        }
        return mList;
    }

}
