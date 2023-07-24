package com.uniqueGames.service;

import com.uniqueGames.model.Member;
import com.uniqueGames.repository.MemberMapper;
import com.uniqueGames.repository.MemberRepositoryMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
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

    public int phoneCheck(String phoneNum) {
        return memberMapper.phoneCheck(phoneNum);
    }

    public int emailCheck(String email) {
        return memberMapper.emailCheck(email);
    }

    public ArrayList<Member> aGetMemberList(String order1, String order2, int start, int end) {
        ArrayList<Member> mList = new ArrayList<>();
        for (Member member : memberMapper.aGetMemberList(order1, order2, start, end)) {
            mList.add(member);
        }
        return mList;
    }

    public Member aGetDetailMember(String id) {
        return memberMapper.aGetDetailMember(id);
    }

    public int delete(String memberId, String password) {
        return memberMapper.delete(memberId, password);
    }

    public String findMpass(String email, String memberId, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setMemberId(memberId);
        member.setName(name);
        return memberMapper.findMpass(member);
    }

    public int changeMpass(String memberId, String newpassword) {
        Member member = new Member();
        member.setMemberId(memberId);
        member.setNewpassword(newpassword);
        return memberMapper.changeMpass(member);
    }

    public int totRowCount() {
        return memberMapper.totRowCount();
    }

    public int totRowCountSearch(String keyword) {
        return memberMapper.totRowCountSearch(keyword);
    }
  
    public int mypageNewPass(String memberId, String password, String newpassword) {
      Member member = new Member();
      member.setMemberId(memberId);
      member.setPassword(password);
      member.setNewpassword(newpassword);
      return memberMapper.mypageNewPass(member);
    }

    public int update(Member member) {
      return memberMapper.update(member);
    }

    public int aDeleteMember(String mid) {
        return memberMapper.aDeleteMember(mid);
    }
}