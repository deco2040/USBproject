package listner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class VisitCountDAO {
	private VisitCountDAO() {}
	private static VisitCountDAO instance = new VisitCountDAO();
	
	public static VisitCountDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		
		return conn;
	}
	
	public int getVisitTodayCount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select count(*) AS count from SAM_COUNT where substr(to_char(today), 1, 9) = to_date(sysdate, 'yy/MM/dd')";
		int count=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count=rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		
		
		return count;
		
	}
	
	
	public void setVisitTotalCount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query="INSERT INTO SAM_COUNT (today) VALUES (sysdate)";
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		
	}
	
	
	public int getVisitTotalCount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="SELECT COUNT(*) AS count from SAM_COUNT";
		int count=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count=rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		
		
		return count;
		
	}
	
}
