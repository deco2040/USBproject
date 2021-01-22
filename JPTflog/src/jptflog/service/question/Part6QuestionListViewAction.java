package jptflog.service.question;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.model.question.QuestionPartDAO;
import jptflog.model.question.QuestionVO;
import jptflog.service.Action;

public class Part6QuestionListViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		QuestionPartDAO jptqdao = QuestionPartDAO.getInstance();
		List<QuestionVO> part6 = jptqdao.JPTpart6();
	
		Set<Integer> favoriteSet = jptqdao.getFavorite(email);
			
		request.setAttribute("favoriteSet", favoriteSet);
		request.setAttribute("part6", part6);
		RequestDispatcher rd = request.getRequestDispatcher("/question/part6.jsp");
		rd.forward(request, response);

	}

}