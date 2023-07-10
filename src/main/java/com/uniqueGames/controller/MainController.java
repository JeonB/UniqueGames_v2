package com.uniqueGames.controller;


import com.uniqueGames.service.IndexServiceMapper;
import com.uniqueGames.service.NoticeService;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

	private final IndexServiceMapper indexServiceMapper;
	NoticeService noticeService;

	@Autowired
	public MainController(IndexServiceMapper indexServiceMapper, NoticeService noticeService) {
		this.indexServiceMapper = indexServiceMapper;
		this.noticeService = noticeService;

	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) throws IOException {
		model.addAttribute("gameList",indexServiceMapper.getGameList());
		model.addAttribute("donation",indexServiceMapper.getDonationList());
		model.addAttribute("ranking",indexServiceMapper.getRankingList());
		model.addAttribute("noticeList", noticeService.getNoticeList(1, 4));

        return "index";
	}

	@GetMapping("alllist")
	public String allList(Model model) throws IOException  {
		model.addAttribute("gameList", indexServiceMapper.getGameList());
		return "main/alllist";
	}

	@GetMapping("topgame")
	public String topgame(Model model) throws IOException  {
		model.addAttribute("ranking", indexServiceMapper.getRankingList());
		return "main/topgame";
	}

//	@RequestMapping(value = "/like", method = RequestMethod.POST)
//	public String handleLikeRequest(@RequestParam("gameId") int gameId, HttpSession session) {
//		SessionVo svo = session.getAttribute("member");
//		if (member == null) {
//			model.setViewName("redirect:/login");
//			return model;
//		}
//
//		int memberId = member.getId();
//
//		int hasLiked = gameDao.hasLiked(memberId, gameId);
//
//		if (hasLiked == 1) {
//			gameDao.removeLikeInfo(memberId, gameId);
//			model.addObject("message", "좋아요가 취소되었습니다.");
//		} else {
//			gameDao.addLikeInfo(memberId, gameId);
//			model.addObject("message", "좋아요가 추가되었습니다.");
//		}
//
//		return "redirect:/game/details?gameId=" + gameId;
//	}
}  
