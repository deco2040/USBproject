package jptflog.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.model.user.LoginDAO;
import jptflog.service.Action;

public class UserLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String useremail = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		String checkbox = request.getParameter("remember");
		
		
		LoginDAO dao = LoginDAO.getInstance();
		int row =0;
		row = dao.userLogin(useremail, passwd);
		
		Cookie cookie = new Cookie("useremail", useremail);// 일단 쿠키 생성

		
		if (checkbox != null) { // 체크박스 체크여부에 따라 쿠키 저장 확인
			// 체크박스 체크 되었을 때
			// 쿠키 저장
			response.addCookie(cookie);
		} else {
			// 체크박스 체크 해제되었을 때
			// 쿠키 유효시간 0으로 해서 브라우저에서 삭제하게 한다.
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		
		if(row == 1) {
			
			session.setAttribute("email",useremail);
			//dao.userLastTime(id);
			RequestDispatcher rd = request.getRequestDispatcher("/user?cmd=home");
			rd.forward(request, response);
			
		}
		else {
			request.setAttribute("row", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/user?cmd=login");
			rd.forward(request, response);
		}

	}

}
