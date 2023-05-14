package web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.face.MemberDao;
import web.dto.Member;
import web.service.face.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired MemberDao memberDao;

	@Override
	public void join(Member member) {

		int res = memberDao.insert(member);
		
		if(res>0) {
			logger.info("회원가입 성공!");
		} else {
			logger.info("회원가입 실패!");
		}
		
	}

	@Override
	public boolean login(Member member) {
		
		int res = memberDao.selectCntMemberByIdPw(member);
		
		if(res > 0) {
			return true;
		}
		
		return false;
	}

	
	
}
