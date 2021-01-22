package jptflog.service.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.model.question.QuestionDAO;
import jptflog.model.question.QuestionVO;
import jptflog.service.Action;

public class IncorrectHighQuestionListViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		QuestionDAO jptqdao = QuestionDAO.getInstance();
		jptqdao.deletejptinfo();
		List<QuestionVO> incorrectHighList = jptqdao.incorrectHigh();
		
		Set<Integer> favoriteSet = jptqdao.getFavorite(email);
		
		request.setAttribute("favoriteSet", favoriteSet);
		request.setAttribute("incorrectHighList", incorrectHighList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/question/incorrectHigh.jsp");
		rd.forward(request, response);

	}

}
