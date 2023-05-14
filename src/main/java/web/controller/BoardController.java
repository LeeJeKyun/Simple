package web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.Paging;
import web.dto.Board;
import web.dto.BoardFile;
import web.dto.Comment;
import web.service.face.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void BoardList(
			
			@RequestParam(value = "curPage", defaultValue = "1") int curPage 
			, @RequestParam(value = "search", defaultValue = "") String search
			, Model model
			
			) {
		logger.info("/board/list [GET]");
		
		Paging paging = boardService.getPaging(curPage, search);
		
		logger.info("/board/list paging:" + paging);
		
		List<Map<String, Object>> list = boardService.getList(paging);
		
		logger.info("/board/list list: " + list.get(0) );
		logger.info("/board/list list: " + list.get(0).get("BOARDNO"));
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}
	
	@GetMapping("/view")
	public String BoardView(
				
			@RequestParam(value="boardno") int boardno
			, Model model
			, HttpSession session
			
			) {
		
		logger.info("/board/view [GET]");
		logger.info("/board/view : boardno : {}", boardno);
		
		Board board = boardService.getBoard(boardno);
		logger.info("/board/view - board : {}", board);
		
		BoardFile boardFile = boardService.getBoardFile(board);
		logger.info("/board/view - boardfile : {}", boardFile);
		
		model.addAttribute("board", board);
		//현재 로그인 중인 유저아이디로 변환. 이전까지는 게시글 작성자 userid
		board.setUserid((String)session.getAttribute("userid"));
		boolean isRecommended = boardService.isRecommended(board);
		logger.info("/board/view - recommended : {}", isRecommended);
		
		//추천수 조회하여 반환
		int recommendCnt = boardService.recommendCnt(board);
		logger.info("/board/view - recommendCnt : {}" , recommendCnt);
		
		
		List<Comment> commentList = boardService.commentList(board);
		logger.info("/board/view - commentList : {}", commentList);
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("boardFile", boardFile);
		model.addAttribute("recommended", isRecommended);
		model.addAttribute("recommendCnt", recommendCnt);
		
		
		return null;
		
	}
	
}
