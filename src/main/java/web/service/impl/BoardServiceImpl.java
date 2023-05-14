package web.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.Paging;
import web.dao.face.BoardDao;
import web.dao.face.CommentDao;
import web.dto.Board;
import web.dto.BoardFile;
import web.dto.Comment;
import web.service.face.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	@Autowired private BoardDao boardDao;
	@Autowired private CommentDao commentDao;
	
	@Override
	public Paging getPaging(int curPage, String search) {
		logger.info("getPaging() 호출");
		String keyword = search;
		
		Paging paging = null;
		
		//비어있을경우 : selectCntAll, Paging그대로진행하기
		int totalCount = boardDao.selectCntAll( keyword );
		paging = new Paging(totalCount, curPage, 10, 5);
//		System.out.println("BoardService getPaging() - : " + totalCount);
		paging.setSearch(keyword);
		
		return paging;
	}

	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		
		return boardDao.selectAll(paging);
	}

	@Override
	public Board getBoard(int boardno) {
		
		boardDao.updateHit(boardno);

		return boardDao.selectBoardByBoardno(boardno);
	}

	@Override
	public BoardFile getBoardFile(Board board) {
		return boardDao.selectBoardFileByBoardno(board);
	}

	@Override
	public boolean isRecommended(Board board) {
		
		int res = boardDao.selectRecommend(board);
		if(res > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public int recommendCnt(Board board) {
		return boardDao.selectRecommendCnt(board);
	}

	@Override
	public List<Comment> commentList(Board board) {
		return commentDao.selectCommentByBoardno(board);
	}
	
	
	
}
