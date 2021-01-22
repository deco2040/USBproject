package jptflog.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {
	private LoginDAO() {}
	private static LoginDAO instance = new LoginDAO();
	
	public static LoginDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		
		return conn;
	}
	public int userLogin(String email, String passwd) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="select email, password from memberinfo where email = ? and password=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();		
			while(rs.next()) {	
				if(rs.getString("email") != "" && rs.getString("password") != "") {
					row = 1;
				}			
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
		return row;
	}
}
