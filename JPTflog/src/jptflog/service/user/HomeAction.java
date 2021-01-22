package jptflog.service.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import jptflog.R.RgraphPrint;
import jptflog.model.user.UserDAO;
import jptflog.model.user.UserGoalVO;
import jptflog.model.week.MemberWeekDAO;
import jptflog.model.week.MemberWeekVO;
import jptflog.service.Action;

public class HomeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	  	String email = (String) session.getAttribute("email");
	     
	     RgraphPrint con = RgraphPrint.getInstance();
	     UserDAO dao = UserDAO.getInstance();
	     MemberWeekDAO memdao = MemberWeekDAO.getInstnce();
	     	
	    int newSolved = 0;
	    int newWeek = 0;
	    int newGoal = 0;
	    
	    if(dao.checksolvedQuestion(email) == 0) {  // 레코드가 만들어져있는 지 없는지 체크
	    	 dao.insertSolvedQuestion(email);  // 없으면 먼저 만들어야함
	    	 newSolved = 1;
			}
		if(dao.checkMemberWeek(email)==0) {  // 레코드가 만들어져있는 지 없는지 체크
				dao.insertMemberWeek(email);  // 없으면 먼저 만들어야함
				newWeek = 1;
			}
		if(dao.checkGoal(email)==0) {  // 레코드가 만들어져있는 지 없는지 체크
			dao.insertGoal(email);  // 없으면 먼저 만들어야함
			newGoal =1;
		}
	     
	     UserGoalVO goalvo = dao.mygoal(email);
	     
	     MemberWeekVO memvo = memdao.myweek(email);
	     HashMap<Integer, Double> correctParsent = memdao.correctParsent(email);
	     List<HashMap<Integer, Double>> incorrectParsent2 = memdao.partAnalysis(email);
	     List<HashMap<Integer, Integer>> incorrectParsent3 = memdao.partAnalysis2(email); 
     	
	    UserDAO user = UserDAO.getInstance();
		int goal = -99;
		goal = user.getMemberGoal(email);
		request.setAttribute("goal", goal);
	     
     	try {
     		
     		if(newGoal ==1 || (goalvo.getDay1() == null || goalvo.getDay1().length() ==0 )) {
     			request.setAttribute("goalgraph", "none");
     			request.setAttribute("expert", -99);
     		}else {
     			request.setAttribute("goalgraph", Base64.encode(con.goalgraph(goalvo)));
     			 request.setAttribute("expert", dao.getMemberExpect(email));
     		}
     		
     		if(newWeek ==1 || (memvo.getTotcorrect()==0 && memvo.getTotincorrect()==0 )) {
     			request.setAttribute("pie", "none");
     		}else {
     			request.setAttribute("pie", Base64.encode(con.pie(memvo)));
     		}
     	    		
			if(newSolved ==1 || (dao.nullchecksolvedQuestion(email) == 1 )) {
				request.setAttribute("rader", "none");
				request.setAttribute("part", "none");
				request.setAttribute("totpart", "none");
			}else {
				request.setAttribute("rader", Base64.encode(con.radarchart(correctParsent)));
				request.setAttribute("part", Base64.encode(con.Partgraph(incorrectParsent2, "틀린 비율")));
				request.setAttribute("totpart", Base64.encode(con.totPart(incorrectParsent3, "틀린 횟수")));
			}
			

		} catch (REngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
		RequestDispatcher rd = request.getRequestDispatcher("/home/home.jsp");
		rd.forward(request, response);

	}

}
