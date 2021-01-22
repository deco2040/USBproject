package jptflog.service.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jptflog.model.question.QuestionCalDAO;
import jptflog.service.Action;

public class QuestionPartAnswerAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionCalDAO caldao = QuestionCalDAO.getInstance();
	
		String result = request.getParameter("arg");
	
		String result2 [] =result.split("&");
		
		List<String> correct = new ArrayList<String>();
		List<String> incorrect = new ArrayList<String>();
		
		List<Integer> scoreWeek = new ArrayList<Integer>();
		Set<Integer> week = new HashSet<Integer>();
		
		List<Integer> qidxs = new ArrayList<Integer>();
		List<String> list = new ArrayList<String>();
		List<String> favorite = new ArrayList<String>();
		
		List<String> answerList = new ArrayList<String>();
		
		Collections.addAll(list, result2); 

		for(Iterator<String> it = list.iterator(); it.hasNext(); ) {
			  String value = it.next();
			  if(value.startsWith("forvite")) {
				value = value.substring(value.indexOf("=")+1);
				favorite.add(value);
			    it.remove();
			  }
			  if(value.startsWith("question")) {
					value = value.substring(value.indexOf("=")+1);
					qidxs.add(Integer.parseInt(value));
				  }
			  
			}
		
		String[] result3 = new String[list.size()];

		int size=0;
		for(String temp : list){
			result3[size++] = temp;
		}
			
			for(int i =0; i<result3.length; i+=2) {
				int qidx = Integer.parseInt(result3[i].substring(result3[i].indexOf("=")+1));
				int choice = Integer.parseInt(result3[i+1].substring(result3[i+1].indexOf("=")+1));
				
				if(caldao.checkCorrect(qidx, choice)==1) {
					correct.add(Integer.toString(qidx));
				}
				else {
					incorrect.add(Integer.toString(qidx));
					answerList.add(Integer.toString(choice));
				}
				
			
			}
			
			scoreWeek = caldao.calScoreWeek(correct);	
			week = caldao.weekExsit(correct);
			
			String part = "";
			part = caldao.partResult(qidxs);
			
			
			request.setAttribute("week", week);
			request.setAttribute("scoreWeek", scoreWeek);	
			request.setAttribute("part", part);	
			
			request.setAttribute("correct", correct);	
			request.setAttribute("incorrect", incorrect);	
			request.setAttribute("answerList", answerList);
			
		
		RequestDispatcher rd = request.getRequestDispatcher("/question/testend2.jsp");
		rd.forward(request, response);

	}

}