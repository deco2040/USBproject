package jptflog.service.question;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jptflog.service.Action;

public class QuestionInfoViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qidxs = request.getParameter("qidxs");
		
		System.out.println(qidxs);
		
		RequestDispatcher rd = request.getRequestDispatcher("/question/questionInfo.jsp");
		rd.forward(request, response);

	}

}
