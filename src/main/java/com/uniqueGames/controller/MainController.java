package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.IndexServiceMapper;
import com.uniqueGames.service.NoticeService;
import java.io.IOException;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

	private final IndexServiceMapper indexServiceMapper;
	private final LoginController login;
	NoticeService noticeService;

	@Autowired
	public MainController(IndexServiceMapper indexServiceMapper, NoticeService noticeService, LoginController login) {
		this.indexServiceMapper = indexServiceMapper;
		this.noticeService = noticeService;
		this.login = login;
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
	@GetMapping("addlike/{gameId}")
	public String addLike(@PathVariable("gameId") String gameId, @Login Member member, Model model) {
		if (member == null) {
			model.addAttribute("result", "no");
			return "/login/login";
		}
		System.out.println("gameId" + gameId + "," + "member" + member);
		int hasLiked = indexServiceMapper.hasLiked(member.getMemberId(), gameId);
		System.out.println(hasLiked);

		if (hasLiked == 1) {
			indexServiceMapper.removeLikeInfo(member.getMemberId(), gameId);
			model.addAttribute("message", "좋아요가 삭제되었습니다.");
		} else {
			indexServiceMapper.addLikeInfo(member.getMemberId(), gameId);
			model.addAttribute("message", "좋아요가 추가되었습니다.");
		}

		return "redirect:/";
	}

}  
