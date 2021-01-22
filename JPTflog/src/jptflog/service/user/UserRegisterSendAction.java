package jptflog.service.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.model.user.UserDAO;
import jptflog.model.user.UserVO;
import jptflog.service.Action;

public class UserRegisterSendAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("useremail");
		String passwd = request.getParameter("passwd");
		String tel = request.getParameter("tel");
		String addr1 = request.getParameter("roadAddress") + "," + request.getParameter("jibunAddress");
		String addr2 = request.getParameter("detailAddress") + "," + request.getParameter("extraAddress");
		String gender = request.getParameter("gender");
		
		
		UserVO vo = new UserVO();
		
		vo.setEmail(email);
		vo.setPassword(passwd);
		vo.setTel(tel);
		vo.setAddress1(addr1);
		vo.setAddress2(addr2);
		vo.setGender(gender);
		
		UserDAO dao = UserDAO.getInstance();
		int row = dao.insertUser(vo);
		
		if(row ==1) {
			HttpSession session = request.getSession();
			session.setAttribute("email",email);
			
			HomeAction home = new HomeAction();
			home.execute(request, response);
			
		}else{
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('회원가입에 실패하였습니다.'); location.href='/user/user_register.jsp';</script>"); 
			writer.close();
		}
		

	}

}
