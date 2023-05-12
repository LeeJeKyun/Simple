package web.dao.face;

import java.util.List;
import java.util.Map;

import util.Paging;
import web.dto.Board;
import web.dto.BoardFile;

public interface BoardDao {

	/**
	 * 전체 게시글 수를 조회해오는 메소드
	 * 
	 * @param keyword
	 * @return
	 */
	public int selectCntAll(String keyword);

	/**
	 * DB의 게시판 정보를 원하는 페이지 수 만큼 가져오는 메소드
	 * 
	 * @param conn
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> selectAll(Paging paging);

	/**
	 * boardno으로 DB를 조회하는 메소드
	 * 
	 * @param boardno
	 * @return
	 */
	public Board selectBoardByBoardno(int boardno);

	/**
	 * boardno으로 BoardFile을 조회하는 메소드
	 * 
	 * @param board
	 * @return
	 */
	public BoardFile selectBoardFileByBoardno(Board board);

	
}
