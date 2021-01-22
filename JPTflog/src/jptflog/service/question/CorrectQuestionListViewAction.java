package jptflog.service.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

public class CorrectQuestionListViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		String correct = request.getParameter("correct");

		List<QuestionVO> correctQuestions = new ArrayList<QuestionVO>();
		
		QuestionDAO jptqdao = QuestionDAO.getInstance();
		correctQuestions = jptqdao.correctQuestion(correct);
		
		Set<Integer> favoriteSet = jptqdao.getFavorite(email);
		
		request.setAttribute("favoriteSet", favoriteSet);
		request.setAttribute("correctQuestions", correctQuestions);

		
		RequestDispatcher rd = request.getRequestDispatcher("/question/correct.jsp");
		rd.forward(request, response);

	}

}
