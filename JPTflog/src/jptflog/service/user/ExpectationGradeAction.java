package jptflog.service.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jptflog.model.question.QuestionDAO;
import jptflog.model.question.QuestionVO;
import jptflog.service.Action;

public class ExpectationGradeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		QuestionDAO dao = QuestionDAO.getInstance();
		List<QuestionVO> expectationQuestions = new ArrayList<QuestionVO>();
		
		expectationQuestions = dao.expectationQuestions();
		
		request.setAttribute("expectationQuestions", expectationQuestions);
		RequestDispatcher rd = request.getRequestDispatcher("/home/expectationQuestion.jsp");
		rd.forward(request, response);

	}

}
