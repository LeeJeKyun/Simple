package web.dao.face;

import web.dto.Member;

public interface MemberDao {

	/**
	 * member를 insert하는 메소드
	 * 
	 * @param member
	 */
	public int insert(Member member);

	/**
	 * 로그인 하려는 id,pw와 일치하는 행이 있는지 조회
	 * 
	 * @param member
	 * @return
	 */
	public int selectCntMemberByIdPw(Member member);


}
