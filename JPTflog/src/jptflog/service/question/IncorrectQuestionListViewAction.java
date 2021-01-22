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

public class IncorrectQuestionListViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		String incorrect = request.getParameter("incorrect");
		String answers = request.getParameter("answerList");
		answers = answers.replace("[", "").replace("]", "").replace(" ", "");
		String [] answerArray = answers.split(",");
		
		ArrayList<String> answerList = new ArrayList<String>(Arrays.asList(answerArray));
		List<QuestionVO> incorrectQuestions = new ArrayList<QuestionVO>();
		
		QuestionDAO jptqdao = QuestionDAO.getInstance();
		incorrectQuestions = jptqdao.incorrectQuestion(incorrect);
		
				
		Set<Integer> favoriteSet = jptqdao.getFavorite(email);
		
		request.setAttribute("favoriteSet", favoriteSet);
		request.setAttribute("incorrectQuestions", incorrectQuestions);
		request.setAttribute("answerList", answerList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/question/incorrect.jsp");
		rd.forward(request, response);

	}

}
