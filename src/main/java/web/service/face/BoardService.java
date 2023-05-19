package web.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import util.Paging;
import web.dto.Board;
import web.dto.BoardFile;
import web.dto.Comment;

@Service
public interface BoardService {

	/**
	 * 페이징 객체를 반환하는 메소드
	 * 
	 * @param curPage - 현재 페이지(요청에서 가져온다.)
	 * @return 페이징을 위한 객체
	 */
	public Paging getPaging(int curPage, String search);

	/**
	 * Dao를 통해 받아온 데이터를 List<Map>형태로 반환
	 * 
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> getList(Paging paging);

	/**
	 * boardno을 이용해서 Board객체를 불러온다.
	 * 
	 * @param boardno
	 * @return
	 */
	public Board getBoard(int boardno);

	/**
	 * board객체를 이용하여 BoardFile객체를 불러온다
	 * 
	 * @param board
	 * @return
	 */
	public BoardFile getBoardFile(Board board);

	/**
	 * 현재 로그인한 유저에게 추천을 받았었는지 확인하는 메소드
	 * 
	 * @param board - 로그인멤버
	 * @return 추천여부
	 */
	public boolean isRecommended(Board board);

	/**
	 * 게시글의 전체 추천수를 조회하는 메소드
	 * 
	 * @param board
	 * @return
	 */
	public int recommendCnt(Board board);

	/**
	 * 현재 게시글의 전체 댓글을 조회하여 반환하는 메소드
	 * 
	 * @param board
	 * @return
	 */
	public List<Comment> commentList(Board board);

	/**
	 * 게시글을 작성하는 메소드
	 * 
	 * @param board
	 * @param upfile
	 */
	public void write(Board board, MultipartFile upfile);
	
	
	
}
