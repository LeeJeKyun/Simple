package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.dto.Comment;

public interface CommentDao {

	/**
	 * 전체 댓글을 조회해오는 메소드
	 * 
	 * @param board
	 * @return
	 */
	public List<Comment> selectCommentByBoardno(Board board);
	
}
