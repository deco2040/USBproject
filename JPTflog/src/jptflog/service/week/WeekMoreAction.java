package jptflog.service.week;

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
import jptflog.model.week.MemberWeekDAO;
import jptflog.service.Action;

public class WeekMoreAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
	
		
		RgraphPrint r = RgraphPrint.getInstance();
		MemberWeekDAO memdao = MemberWeekDAO.getInstnce();
		 
	    List<HashMap<Integer, Double>> incorrectParsent2 = memdao.partAnalysis(email);
	    List<HashMap<Integer, Integer>> incorrectParsent3 = memdao.partAnalysis2(email);
	    List<HashMap<Integer, Double>> incorrectParsent4 = memdao.partAnalysis3(email);
	    List<HashMap<Integer, Integer>> incorrectParsent5 = memdao.partAnalysis4(email);
		
		try {
     	
				request.setAttribute("part", Base64.encode(r.Partgraph(incorrectParsent2, "틀린 비율")));
				request.setAttribute("totpart", Base64.encode(r.totPart(incorrectParsent3, "틀린 횟수")));
				
				request.setAttribute("part2", Base64.encode(r.Partgraph(incorrectParsent4, "맞은 비율")));
				request.setAttribute("totpart2", Base64.encode(r.totPart(incorrectParsent5, "맞은 횟수")));
			
		} catch (REngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/home/weekmore.jsp");
		rd.forward(request, response);
	}

}
