package com.uniqueGames.repository;

import com.uniqueGames.model.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public int login(Member member) {
		return sqlSession.selectOne("mapper.member.login", member);
	}

	/**sign up member*/
	public int insert(Member member) {
		return sqlSession.insert("mapper.member.insert", member);

	}
	/**ID checking*/
	public int idCheck(String memberId) {
		return sqlSession.selectOne("mapper.member.idCheck", memberId);
	}
	
	/**find-id-check*/
	public String findIdCheck(Member member) {
		return sqlSession.selectOne("mapper.member.findId", member);
	}
	
	/*********************��й�ȣ �缳��****************************************/
	public int select(Member member) {
		return sqlSession.selectOne("mapper.member.select", member);
	}
	
	/**mypage session id information update*/
	public int update(Member member) {
		return sqlSession.update("mapper.member.update", member);
	}
	
	public int changeMpassword(String memberId, String mnewpassword) {
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMnewpassword(mnewpassword);
		return sqlSession.update("mapper.member.changeMpassword", member);
	}
	
	/**mypage session id information
	public MemberVo select(Object member_id) {
		MemberVo memberVo = null;
		String sql = "select member_id, password, name, email, phone_num, addr, tel from member where member_id=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, (String)member_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberVo = new MemberVo();
				
				memberVo.setMember_id(rs.getString(1));
				memberVo.setPassword(rs.getString(2));
				memberVo.setName(rs.getString(3));
				memberVo.setEmail(rs.getString(4));
				memberVo.setPhone_num(rs.getString(5));
				memberVo.setAddr(rs.getString(6));
				memberVo.setTel(rs.getString(7));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberVo;
	}
	*/
	
	public Member myPage(String memberId) {
		return sqlSession.selectOne("mapper.member.myPage", memberId);
	}
	
	/**delete Account Ajax*/
	public int delete(Member member) {
		return sqlSession.delete("mapper.member.delete", member);
	}
	
	/**email check*/
	public int emailCheck(String email) {
		return sqlSession.selectOne("mapper.member.emailCheck", email);
	}
	
	/**phone check*/
	public int phoneCheck(String phone_num) {
		return sqlSession.selectOne("mapper.member.phoneCheck", phone_num);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*********************�Ѿ���� name���� �޾Ƽ� ���� ������ member�� �ƴϸ� company��(����)
	public int selectMode(String name, String phone_num) {
		int result=0;
		String sql = "select count(*) from member where name=? and phone_num=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, phone_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	****************************************/
	
	
	

	/**delete Account
	public int delete(MemberVo memberVo) {
		int result=0;
		String sql = "delete from member where member_id=? and password=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, memberVo.getMember_id());
			pstmt.setString(2, memberVo.getPassword());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	*/
	

}
