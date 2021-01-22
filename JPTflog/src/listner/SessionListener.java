package listner;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent arg0) {
        VisitCountDAO dao = VisitCountDAO.getInstance();
        
        // 전체 방문자 수 +1
        dao.setVisitTotalCount();
         
        // 오늘 방문자 수
        int todayCount = dao.getVisitTodayCount();
         
        // 전체 방문자 수
        int totalCount = dao.getVisitTotalCount();
         
        HttpSession session = arg0.getSession();
         
        // 세션 속성에 담아준다.
        session.setAttribute("totalCount", totalCount); // 전체 방문자 수
        session.setAttribute("todayCount", todayCount);
         
        System.out.println("리스너 확인");
    }
 
     @Override
     public void sessionDestroyed(HttpSessionEvent arg0) {
      // TODO Auto-generated method stub
     }
}

