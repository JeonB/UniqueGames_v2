package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.AjaxResponse;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.IndexServiceMapper;
import com.uniqueGames.service.NoticeService;
import java.io.IOException;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@PostMapping("addlike/{gameId}/")
	@ResponseBody
	public ResponseEntity<AjaxResponse> addLike(@PathVariable("gameId") String gameId, @Login Member member) {
		if (member == null) {
			return ResponseEntity.ok().body(new AjaxResponse("no", "이거", 0));
		}
//		System.out.println("result: " + result);
//		System.out.println("message: " + message);
//		System.out.println("likeCount: " + likeCount);
		int hasLiked = indexServiceMapper.hasLiked(member.getMemberId(), gameId);

		if (hasLiked == 1) {
			indexServiceMapper.removeLikeInfo(member.getMemberId(), gameId);
			return ResponseEntity.ok().body(new AjaxResponse("ok", "좋아요가 삭제되었습니다.", indexServiceMapper.getGameLikeCount(gameId)));
		} else {
			indexServiceMapper.addLikeInfo(member.getMemberId(), gameId);
			return ResponseEntity.ok().body(new AjaxResponse("ok", "좋아요가 추가되었습니다.", indexServiceMapper.getGameLikeCount(gameId)));
		}
	}

}  
