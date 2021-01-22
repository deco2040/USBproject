package jptflog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.mail.SendMail;
import jptflog.model.user.UserDAO;

/**
 * Servlet implementation class PasswordSearchController
 */
@WebServlet("/password")
public class PasswordSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String email = request.getParameter("useremail");
	        
	        //먼저 아이디로 회원정보를 받아오고 가져온 데이터에서 email값을 비교하여 존재하지 않으면 인증메일 보내지 못함
		 	UserDAO dao = UserDAO.getInstance();
		 	int row = 0;
		 	row = dao.idCheck(email);
		 	
	        if(row != 0)
	        {
	        	response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				writer.println("<script>alert('이메일이 존재하지 않습니다.'); location.href='/user/user_register.jsp';</script>"); 
				writer.close();
	        }
	        	
	        
	          
	                //인증 번호 생성기
	                StringBuffer temp =new StringBuffer();
	                Random rnd = new Random();
	                for(int i=0;i<10;i++)
	                {
	                    int rIndex = rnd.nextInt(3);
	                    switch (rIndex) {
	                    case 0:
	                        // a-z
	                        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	                        break;
	                    case 1:
	                        // A-Z
	                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                        break;
	                    case 2:
	                        // 0-9
	                        temp.append((rnd.nextInt(10)));
	                        break;
	                    }
	                }
	                String AuthenticationKey = temp.toString();
	               
	                //email 전송
	                SendMail send = new SendMail();
	            	send.sendMail(email,AuthenticationKey);
	       		
	                HttpSession saveKey = request.getSession();
	                saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
	                //패스워드 바꿀때 뭘 바꿀지 조건에 들어가는 id
	                request.setAttribute("email", email);
	                request.getRequestDispatcher("/user/user_pwsearchPro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	     	String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");	   	     		     	
	        String AuthenticationUser = request.getParameter("AuthenticationUser");
	                   
	        UserDAO dao = UserDAO.getInstance();
	        
	        if(!AuthenticationKey.equals(AuthenticationUser))
	        {
	        	response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				writer.println("<script>alert('인증번호가 맞지 않습니다.'); location.href='/user/user_pwsearchPro.jsp';</script>"); 
				writer.close();
	        }else {
	        	String passwd = request.getParameter("passwd");
	        	String email = request.getParameter("useremail");
	        	
	        	System.out.println("email: " + email);
	        	System.out.println("passwd: " + passwd);
	        	
	        	int row = 0;
	        	row = dao.updatePasswd(email, passwd);
	        	
	        	if(row == 1) {
	    
	        		response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter writer = response.getWriter(); 
					writer.println("<script>alert('비밀번호를 변경하였습니다.'); location.href='/user?cmd=login'</script>"); 
					writer.close();
	        		
	        	}else {
	        		response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter writer = response.getWriter(); 
					writer.println("<script>alert('비밀번호 변경에 실패하였습니다.'); location.href='/user/user_pwsearchPro.jsp';</script>"); 
					writer.close();
	        	}
	        	
	        	
	        	
	        	
	        }

		
		
		
	}

}
