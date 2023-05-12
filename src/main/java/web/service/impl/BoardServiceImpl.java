package web.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.Paging;
import web.dao.face.BoardDao;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	@Autowired private BoardDao boardDao;
	
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

		return boardDao.selectBoardByBoardno(boardno);
	}

	@Override
	public BoardFile getBoardFile(Board board) {
		return boardDao.selectBoardFileByBoardno(board);
	}
	
	
	
}
