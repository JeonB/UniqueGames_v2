package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.AjaxResponse;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.IndexServiceMapper;
import com.uniqueGames.service.NoticeService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

	@PostMapping("addlike/{gameId}/")
	@ResponseBody
	public ResponseEntity<AjaxResponse> addLike(@PathVariable("gameId") String gameId, @Login Member member) {
		AjaxResponse ajaxResponse;

		log.info("로그인 체크");
		log.info("1" + gameId);
		log.info("2" + member.getMemberId());

		int gId = Integer.parseInt(gameId);
		int hasLiked = indexServiceMapper.hasLiked(member.getMemberId(), gId);

		if (hasLiked == 1) {
			indexServiceMapper.removeLikeInfo(member.getMemberId(), gId);

			ajaxResponse = new AjaxResponse("ok", "좋아요가 삭제되었습니다.", indexServiceMapper.getGameLikeCount(gId));
		} else {
			indexServiceMapper.addLikeInfo(member.getMemberId(), gId);
			ajaxResponse = new AjaxResponse("ok", "좋아요가 추가되었습니다.", indexServiceMapper.getGameLikeCount(gId));
		}

		log.info("result: {}", ajaxResponse.getResult2());
		log.info("message: {}", ajaxResponse.getMessage2());
		log.info("likeCount: {}", ajaxResponse.getLikecount());

		return ResponseEntity.ok().body(ajaxResponse);
	}


}  
