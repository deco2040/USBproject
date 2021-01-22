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

public class Part7QuestionListViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		QuestionPartDAO jptqdao = QuestionPartDAO.getInstance();
		List<QuestionVO> part7 = jptqdao.JPTpart7();
	
		Set<Integer> favoriteSet = jptqdao.getFavorite(email);
			
		request.setAttribute("favoriteSet", favoriteSet);
		request.setAttribute("part7", part7);
		RequestDispatcher rd = request.getRequestDispatcher("/question/part7.jsp");
		rd.forward(request, response);

	}

}
