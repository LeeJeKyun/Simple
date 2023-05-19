package web.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired private ServletContext context;
	
	@Override
	public Paging getPaging(int curPage, String search) {
//		logger.info("getPaging() 호출");
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

	@Override
	public void write(Board board, MultipartFile upfile) {

		board.setBoardno(boardDao.selectNextBoardSeq());
		logger.info("{}", board);
		boardDao.insertBoard(board);
		
		if( upfile.getSize() <= 0) {
			logger.info("파일의 크기가 0이다, 글만 처리");
			
			return;
		}
		
		String storedPath = context.getRealPath("upload");
		File storedFolder = new File(storedPath);
		
		//폴더가 없는 경우 생성
		storedFolder.mkdir();
		
		File dest = null;
		String storedName = null;
		
		do{
			storedName = UUID.randomUUID().toString().split("-")[0];
			
			dest = new File(storedFolder, storedName);
			
		} while( dest.exists() );
		
		try {
			upfile.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BoardFile boardFile = new BoardFile();
		boardFile.setBoardno(board.getBoardno());
		boardFile.setFileno(boardDao.selectNextBoardFileSeq());
		boardFile.setFilesize(upfile.getSize());
		boardFile.setOriginname(upfile.getOriginalFilename());
		boardFile.setStoredname(storedName);
		
		boardDao.insertBoardFile(boardFile);
		
	}
	
	
	
}
