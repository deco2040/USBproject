package jptflog.service.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.model.question.QuestionCalDAO;
import jptflog.model.question.QuestionDAO;
import jptflog.service.Action;


public class TestEndAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QuestionCalDAO caldao = QuestionCalDAO.getInstance();
		QuestionDAO dao = QuestionDAO.getInstance();
		
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		String result = request.getParameter("arg");
		
		String result2 [] =result.split("&");
		
		
		List<String> list = new ArrayList<String>();
		
		Set<Integer> qidxs = new HashSet<Integer>();
		Set<Integer> favorite = new HashSet<Integer>();
		Set<Integer> favoriteDB = new HashSet<Integer>();
		
		
		Collections.addAll(list, result2); 
		
		favoriteDB = dao.getFavorite(email);
		
		for(Iterator<String> it = list.iterator(); it.hasNext(); ) {
			  String value = it.next();
			  if(value.startsWith("forvite")) {
				value = value.substring(value.indexOf("=")+1);
				favorite.add(Integer.parseInt(value));
			    it.remove();
			  }
			  if(value.startsWith("question")) {
					value = value.substring(value.indexOf("=")+1);
					qidxs.add(Integer.parseInt(value));
				  }
			    
			}
	
		qidxs.removeAll(favorite);
		qidxs.retainAll(favoriteDB);
		
		for (int qidx : qidxs) {
			dao.deleteFavorite(Integer.toString(qidx), email);
		}	
		
		favorite.removeAll(favoriteDB);
		
		caldao.updateSolvedQuestionfavorite(email,favorite);  // 즐겨찾기 추가
	
	
		String[] result3 = new String[list.size()];

		int size=0;
		for(String temp : list){
			result3[size++] = temp;
		}

		
		for(int i =0; i<result3.length; i+=2) {
			int qidx = Integer.parseInt(result3[i].substring(result3[i].indexOf("=")+1));
			String qidxString = result3[i].substring(result3[i].indexOf("=")+1);
			int choice = Integer.parseInt(result3[i+1].substring(result3[i+1].indexOf("=")+1));
			
			if(caldao.checkJPTinfo(qidx)==0) {  // 레코드가 만들어져있는 지 없는지 체크
				caldao.insertJptinfo(qidx);  // 없으면 먼저 만들어야함
			}
		
			
			caldao.updateJPTinfo(qidx, choice);	// 문제 정보 업데이트
			
			//  Integer.parseInt(result3[i].substring(10)) 이건 qidx를 말하는거임
			// Integer.parseInt(result3[i+1].substring(7)) 이건 정답을 말하는거
			int row = caldao.checkCorrect(qidx, choice);  // 정답체크 맞으면 1 아니면 0 반환함
			if(row ==1) {  // 정답이면
				caldao.updateMemberWeekCorrect(email);  // 멤버 약점 업데이트
				caldao.updateSolvedQuestioncorrect(email,qidxString);  // 푼 문제 중 맞은 문제 업데이트
			}else {  // 정답이 아니면
				caldao.updateMemberWeekinfo(email, qidx);			//맴버 약점 엡데이트
				caldao.updateSolvedQuestionIncorrect(email,qidxString);  // 푼 문제 중 틀린 문제 없데이트
			}
		}
		
		String part = "";
		part = dao.partResult(qidxs);
		
		if(part.equals("allpart")) {
			QuestionAnswerAction answer = new QuestionAnswerAction();
			answer.execute(request, response);
		}else {
			QuestionPartAnswerAction answer = new QuestionPartAnswerAction();
			answer.execute(request, response);
		}
			
		
		
	}
	

}
