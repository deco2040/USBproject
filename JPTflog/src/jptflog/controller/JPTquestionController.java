package jptflog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jptflog.service.Action;


/**
 * Servlet implementation class JPTquestionController
 */
@WebServlet("/question")
public class JPTquestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JPTquestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		System.out.println("JPTquestioncontroller :" + cmd);
		
		Action action = null;
		JPTquestionActionFactory jptq = JPTquestionActionFactory.getInstance();
		action = jptq.getAction(cmd);
		
		if(action != null) {
			action.execute(request, response);
			}
		
		}	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain"); 
		response.setCharacterEncoding("UTF-8"); 
			
		String cmd = request.getParameter("cmd");
		if(cmd != null) {
			System.out.println("JPTquestioncontroller2 :" + cmd);
			Action action = null;
			JPTquestionActionFactory jptq = JPTquestionActionFactory.getInstance();
			action = jptq.getAction(cmd);
			
			if(action != null) {
				action.execute(request, response);
				}
		}	
		
	}
			

	

}





