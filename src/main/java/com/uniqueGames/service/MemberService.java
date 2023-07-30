package com.uniqueGames.service;

import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Member;
import com.uniqueGames.repository.MemberMapper;
import com.uniqueGames.repository.MemberRepositoryMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@Service
public class MemberService extends FileUploadUtil {

    @Autowired
    private MemberRepositoryMapper memberRepositoryMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    protected void extractFile(Object obj) {
        super.setFile((MultipartFile) obj);
    }

    public int save(Member member) {
        return memberMapper.save(member);
    }

//    public int idCheck(String memberId) {
//        return memberMapper.idCheck(memberId);
//    }
//
//    public String findMid(String email, String name) {
//        Member member = new Member();
//        member.setEmail(email);
//        member.setName(name);
//        return memberMapper.findMid(member);
//    }
//
//    public int phoneCheck(String phoneNum) {
//        return memberMapper.phoneCheck(phoneNum);
//    }
//
//    public int emailCheck(String email) {
//        return memberMapper.emailCheck(email);
//    }

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

//    public int delete(String memberId, String password) {
//        return memberMapper.delete(memberId, password);
//    }

//    public String findMpass(String email, String memberId, String name) {
//        Member member = new Member();
//        member.setEmail(email);
//        member.setMemberId(memberId);
//        member.setName(name);
//        return memberMapper.findMpass(member);
//    }

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

    /**0729 변경사항*/
    public int idCheck1(String id, String type2) {
        Map param = new HashMap();
        param.put("id", id);
        param.put("type2", type2);
        return memberMapper.idCheck1(param);
    }
    public int emailDuplicateCheck(String email, String type2) {
        Map param = new HashMap();
        param.put("email", email);
        param.put("type2", type2);
        return memberMapper.emailDuplicateCheck(param);
    }
    public int phoneCheck1(String phoneNum, String type2) {
        Map param = new HashMap();
        param.put("phoneNum", phoneNum);
        param.put("type2", type2);
        return memberMapper.phoneCheck1(param);
    }

    public String findId(String email, String name, String type2) {
        Map param = new HashMap();
        param.put("email", email);
        param.put("name", name);
        param.put("type2", type2);
        return memberMapper.findId(param);
    }

    public String findPass(String email, String id, String name, String type2) {
        Map param = new HashMap();
        param.put("email", email);
        param.put("id", id);
        param.put("name", name);
        param.put("type2", type2);
        return memberMapper.findPass(param);
    }
    /**
    public int modalChangePass(String id, String newpassword, String modaltype) {
        Map param = new HashMap();
        param.put("id", id);
        param.put("newpassword", newpassword);
        param.put("modaltype", modaltype);
        return memberMapper.modalChangePass(param);
    }*/

    public int delete1(String id, String password, String type2) {
        Map param = new HashMap();
        param.put("id", id);
        param.put("password", password);
        param.put("type2", type2);
        return memberMapper.delete1(param);
    }





}
