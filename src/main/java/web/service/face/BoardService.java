package web.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import util.Paging;
import web.dto.Board;
import web.dto.BoardFile;

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
	
	
	
}