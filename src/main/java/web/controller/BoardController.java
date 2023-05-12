package web.controller;

import java.util.List;
import java.util.Map;

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
			
			) {
		
		logger.info("/board/view [GET]");
		logger.info("/board/view : boardno : " + boardno);
		
		Board board = boardService.getBoard(boardno);
		logger.info("/board/view - board : " + board);
		
		BoardFile boardFile = boardService.getBoardFile(board);
		logger.info("/board/view - boardfile : " + boardFile);
//		boolean isRecommended = boardService.isRecommended(board);
//		int recommendCnt = boardService.recommendCnt(board);
//		List<Comment> commentList = boardService.commentList(board);
		
//		System.out.println("BoardViewController - doGet : " + commentList);
		
//		model.addAttribute("commentList", commentList);
		model.addAttribute("board", board);
//		model.addAttribute("boardFile", boardFile);
//		model.addAttribute("recommended", isRecommended);
//		model.addAttribute("recommendCnt", recommendCnt);
//		System.out.println("BoardViewController doGet() boardFile : " + boardFile);

		
		return null;
		
	}
	
}
