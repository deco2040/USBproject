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

public class AllpartQuestionListViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		QuestionDAO jptqdao = QuestionDAO.getInstance();
		List<QuestionVO> jptq1 = jptqdao.JPTqListDi1();
		List<QuestionVO> jptq2 = jptqdao.JPTqListDi2();
		List<QuestionVO> jptq3 = jptqdao.JPTqListDi3();
		List<QuestionVO> reading = jptqdao.JPTreadingList();
		
		Set<Integer> favoriteSet = jptqdao.getFavorite(email);
		
		List<QuestionVO> jpttot= new ArrayList<QuestionVO>();
		for(int i = 0; i < jptq1.size(); i++) {
			jpttot.add(jptq1.get(i));
		}
		for(int i = 0; i < jptq2.size(); i++) {
			jpttot.add(jptq2.get(i));
		}
		for(int i = 0; i < jptq3.size(); i++) {
			jpttot.add(jptq3.get(i));
		}
		
		Collections.shuffle(jpttot);
		
		request.setAttribute("favoriteSet", favoriteSet);
		request.setAttribute("reading", reading);
		request.setAttribute("jpttot", jpttot);
		RequestDispatcher rd = request.getRequestDispatcher("/question/allparts.jsp");
		rd.forward(request, response);

	}

}
