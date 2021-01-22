package jptflog.service.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

import jptflog.R.RgraphPrint;
import jptflog.model.user.UserDAO;
import jptflog.service.Action;

public class ExpectationResultAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = request.getParameter("arg");
		String result2 [] =result.split("&");
		
		List<String> resultList = new ArrayList<String>();
		List<Integer> resultList2 = new ArrayList<Integer>();
		
		Collections.addAll(resultList, result2); 
		
		for(Iterator<String> it = resultList.iterator(); it.hasNext(); ) {
			  String value = it.next();
			  if(value.startsWith("group")) {
					value = value.substring(value.indexOf("=")+1);
					resultList2.add(Integer.parseInt(value));
				  }
			  
			}
		 
		RgraphPrint r = RgraphPrint.getInstance();
		double expert = 0;
	
		try {
			expert = r.expectGrade(resultList2);
		} catch (REngineException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		}
		
		expert = Math.round(expert*10)/10.0;
		
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		UserDAO user = UserDAO.getInstance();
		user.updateMemberExpect(email,expert);
		
		request.setAttribute("expert", expert);
		
		RequestDispatcher rd = request.getRequestDispatcher("/home/result.jsp");
		rd.forward(request, response);

	}

}
