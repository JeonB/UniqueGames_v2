package com.uniqueGames.controller;

import com.uniqueGames.model.Member;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.MemberService;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    
    private MemberService memberService;
    @Autowired
    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }
    
//    @GetMapping("/mypage")
//    public String mypage(HttpSession session, Model model) {
//        String viewName = "";
//        String mode = session.getAttribute("login").toString();
//
//        if(mode.equals("member")) {
//            Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
//            String password = member.getPassword();
//			String email1 = member.getEmail().split("@")[0];
//			String email2 = member.getEmail().split("@")[1];
//			String email3 = member.getEmail().split("@")[1];
//			String tel = member.getTel();
//			String phone1 = member.getPhoneNum().split("-")[0];
//			String phone2 = member.getPhoneNum().split("-")[1];
//			String phone3 = member.getPhoneNum().split("-")[2];
//			String addr = member.getAddr();
//
//            if (addr == null) {
//                System.out.println("주소 자체가 없을 때? 이게 필요있나="+member.getAddr());
//				member.setAddr1("");
//				member.setAddr2("");
//			}else {
//				String[] addrSplit = addr.split("   ");
//				if (addrSplit.length == 1) {
//					String addr1 = addrSplit[0];
//					member.setAddr1(addr1);
//					member.setAddr2("");
//                    System.out.println("주소 하나만 있을 때 addr1="+ member.getAddr1());
//				} else if (addrSplit.length == 2) {
//					String addr1 = addrSplit[0];
//					String addr2 = addrSplit[1];
//					member.setAddr1(addr1);
//					member.setAddr2(addr2);
//                    System.out.println("주소 두개 다 있을 때 addr1="+ member.getAddr1());
//                    System.out.println("주소 두개 다 있을 때 addr2="+ member.getAddr2());
//				} else {
//					member.setAddr1("");
//					member.setAddr2("");
//                    System.out.println("주소 다 없을 때 addr1="+ member.getAddr1());
//                    System.out.println("주소 다 없을 때 addr2="+ member.getAddr2());
//				}
//			}
//			member.setEmail1(email1);
//			member.setEmail2(email2);
//			member.setEmail3(email3);
//			member.setTel(tel);
//			member.setPhone1(phone1);
//			member.setPhone2(phone2);
//			member.setPhone3(phone3);
//            model.addAttribute("member", member);
//            System.out.println("member addr1="+ member.getAddr1());
//            System.out.println("member addr2="+ member.getAddr2());
//
//            viewName = "myPage/member-page";
//        }else if(mode.equals("company")) {
//            System.out.println("법인");
//            viewName = "company-page";
//        }
//        return viewName;
//    }
    
    
    
    
    
    
    

//	@Autowired
//	private MemberServiceMethod memberServiceMethod;
//
//	@Autowired
//	private CompanyMemberService companyMemberService;
//
//	//show information about member user
//	@RequestMapping(value="/myPage", method=RequestMethod.GET)
//	public ModelAndView myPage(HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//
////		String mode = session.getAttribute(SessionConstants.LOGIN_MEMBER).toString();
//		String mode = (String)session.getAttribute("login");
//
//
//		if(mode.equals("member")) {
//			Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
//			String password = member.getPassword();
//			String email1 = member.getEmail().split("@")[0];
//			String email2 = member.getEmail().split("@")[1];
//			String email3 = member.getEmail().split("@")[1];
//			String tel = member.getTel();
//			String phone1 = member.getPhoneNum().split("-")[0];
//			String phone2 = member.getPhoneNum().split("-")[1];
//			String phone3 = member.getPhoneNum().split("-")[2];
//			String addr = member.getAddr();
//			System.out.println("member id="+ member.getMemberId());
//			System.out.println("member pass="+ member.getPassword());
//			System.out.println("member name="+ member.getName());
//			System.out.println("member email="+ member.getEmail());
//			System.out.println("member tel="+ member.getTel());
//			System.out.println("member phoneNum="+ member.getPhoneNum());
//			System.out.println("member addr="+ member.getAddr());
//
//			if (addr == null) {
//				member.setAddr1("");
//				member.setAddr2("");
//			}else {
//				String[] addrSplit = addr.split("   ");
//				if (addrSplit.length == 1) {
//					String addr1 = addrSplit[0];
//					member.setAddr1(addr1);
//					member.setAddr2("");
//				} else if (addrSplit.length == 2) {
//					String addr1 = addrSplit[0];
//					String addr2 = addrSplit[1];
//					member.setAddr1(addr1);
//					member.setAddr2(addr2);
//				} else {
//					member.setAddr1("");
//					member.setAddr2("");
//				}
//			}
//			member.setEmail1(email1);
//			member.setEmail2(email2);
//			member.setEmail3(email3);
//			member.setTel(tel);
//			member.setPhone1(phone1);
//			member.setPhone2(phone2);
//			member.setPhone3(phone3);
//			mav.addObject("member", member);
//			mav.setViewName("member-page");
//
//		}else if(mode.equals("company")) {
//
//			Company company = (Company)session.getAttribute(SessionConstants.LOGIN_MEMBER);
//
//			String Gname = companyMemberService.companyGameName(company.getCompanyId());
//			String password = company.getPassword();
//			String email1 = company.getEmail().split("@")[0];
//			String email2 = company.getEmail().split("@")[1];
//			String email3 = company.getEmail().split("@")[1];
//			String tel = company.getTel();
//			String phone1 = company.getPhoneNum().split("-")[0];
//			String phone2 = company.getPhoneNum().split("-")[1];
//			String phone3 = company.getPhoneNum().split("-")[2];
//			String addr = company.getAddr();
//			System.out.println("company id="+ company.getCompanyId());
//			System.out.println("company pass="+ company.getPassword());
//			System.out.println("company name="+ company.getName());
//			System.out.println("company email="+ company.getEmail());
//			System.out.println("company tel="+ company.getTel());
//			System.out.println("company phoneNum="+ company.getPhoneNum());
//			System.out.println("company addr="+ company.getAddr());
//
//			if (addr == null) {
//				company.setAddr1("");
//				company.setAddr2("");
//
//			} else {
//
//				String[] addrSplit = addr.split("   ");
//				if (addrSplit.length == 1) {
//					String addr1=addrSplit[0];
//					company.setAddr1(addr1);
//					company.setAddr2("");
//				} else if (addrSplit.length == 2) {
//					String addr1 = addrSplit[0];
//					String addr2 = addrSplit[1];
//					company.setAddr1(addr1);
//					company.setAddr2(addr2);
//				} else {
//					company.setAddr1("");
//					company.setAddr2("");
//				}
//			}
//			company.setGame(Gname);
//			company.setEmail1(email1);
//			company.setEmail2(email2);
//			company.setEmail3(email3);
//			company.setTel(tel);
//			company.setPhone1(phone1);
//			company.setPhone2(phone2);
//			company.setPhone3(phone3);
//
//			mav.addObject("company", company);
//			mav.setViewName("company-page");
//		}else {
//			mav.setViewName("login/login");
//		}
//
//		return mav;
//	}
//
//	//mypage actual update
//	@RequestMapping(value="/myPage_proc", method=RequestMethod.POST)
//	public ModelAndView myPage_proc(Member member, HttpSession request) {
//		ModelAndView mav = new ModelAndView();
//		int result = memberServiceMethod.memberUpdateResult(member);
//
//		if(result == 1) {
//			request.setAttribute(SessionConstants.LOGIN_MEMBER, member);
//			mav.setViewName("redirect:/");
//		}else {
//			System.out.println("update 실패");
//		}
//
//		return mav;
//	}
//
//	@RequestMapping(value="/companyMyPage_proc", method=RequestMethod.POST)
//	public ModelAndView companyMyPage_proc(Company company, HttpSession request) {
//		ModelAndView mav = new ModelAndView();
//		int result = companyMemberService.companyUpdateResult(company);
//		if(result == 1) {
//			request.setAttribute(SessionConstants.LOGIN_MEMBER, company);
//			mav.setViewName("redirect:/");
//
//		}else {
//			System.out.println("update 실패");
//		}
//
//		return mav;
//	}

//	@RequestMapping(value="/companyMyPage", method=RequestMethod.GET)
//	public ModelAndView companyMyPage(HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		CompanyVo companyVo = (CompanyVo)session.getAttribute(SessionConstants.LOGIN_MEMBER);
	//CompanyVo companyVo = companyMemberService.companyPageResult((String)session.getAttribute("company_id"));


//		if(companyVo != null) {
//
//			String password = companyVo.getPassword();
//			String email1 = companyVo.getEmail().split("@")[0];
//			String email2 = companyVo.getEmail().split("@")[1];
//			String email3 = companyVo.getEmail().split("@")[1];
//			String tel = companyVo.getTel();
//			String phone1 = companyVo.getPhone_num().split("-")[0];
//			String phone2 = companyVo.getPhone_num().split("-")[1];
//			String phone3 = companyVo.getPhone_num().split("-")[2];
//			String addr = companyVo.getAddr();
//
//			if (addr == null) {
//	            // addr ???? null?? ??? ???
//				companyVo.setAddr1(""); // ????? ???
//				companyVo.setAddr2(""); // ????? ???
//
//	        } else {
//
//	        	String[] addrSplit = addr.split("   ");
//	            if (addrSplit.length == 1) {
//	                // addr1 ???? ??? ???
//	            	String addr1=addrSplit[0];
//	            	companyVo.setAddr1(addr1);
//	            	companyVo.setAddr2(""); // ????? ???
//	            } else if (addrSplit.length == 2) {
//	                // addr1, addr2 ???? ??? ??? ???
//	            	String addr1 = addrSplit[0];
//	            	String addr2 = addrSplit[1];
//	            	companyVo.setAddr1(addr1);
//	            	companyVo.setAddr2(addr2);
//	            } else {
//	                // addr ?? ?????? ????? ??? ??? ??? ??? ????? ?????? ???
//	            	companyVo.setAddr1(""); // ????? ???
//	            	companyVo.setAddr2(""); // ????? ???
//	            }
//	        }
//
//			companyVo.setGame(Gname);
//			companyVo.setEmail1(email1);
//			companyVo.setEmail2(email2);
//			companyVo.setEmail3(email3);
//			companyVo.setTel(tel);
//			companyVo.setPhone1(phone1);
//			companyVo.setPhone2(phone2);
//			companyVo.setPhone3(phone3);
//
//			mav.addObject("companyVo", companyVo);
//			mav.setViewName("/myPage/companyMyPage");
//		}else {
//
//			mav.addObject("myPage_result", "fail");
//			mav.setViewName("/login/login");
//		}
//
//		return mav;
//	}




}
