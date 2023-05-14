package web.service.face;

import web.dto.Member;

public interface MemberService {

	/**
	 * 회원가입하는 메소드
	 * 
	 * @param member
	 */
	public void join(Member member);

	/**
	 * 로그인 하는 메소드
	 * 
	 * @param member
	 */
	public boolean login(Member member);

}
