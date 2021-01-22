package jptflog.service.week;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

import jptflog.R.RgraphPrint;
import jptflog.model.week.MemberWeekDAO;
import jptflog.model.week.MemberWeekVO;
import jptflog.service.Action;

public class WeekViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberWeekDAO memdao = MemberWeekDAO.getInstnce();
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		MemberWeekVO memvo = memdao.myweek(email);
		
		
		
		request.setAttribute("memvo", memvo);
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberweek.jsp");
		rd.forward(request, response);
	}

}
