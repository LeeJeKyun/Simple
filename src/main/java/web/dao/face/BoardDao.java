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

	/**
	 * 현재 로그인한 멤버의 아이디와 현재 조회한 게시글의 번호로 추천된 내역이 있는지 확인하여 반환하는 메소드
	 * 
	 * @param board
	 * @return
	 */
	public int selectRecommend(Board board);

	/**
	 * 현재 게시글의 전체 추천수를 반환한다.
	 * 
	 * @param board
	 * @return
	 */
	public int selectRecommendCnt(Board board);

	
}
