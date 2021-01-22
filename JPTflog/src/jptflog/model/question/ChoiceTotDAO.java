package jptflog.model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChoiceTotDAO {
	private ChoiceTotDAO() {}
	private static ChoiceTotDAO instance = new ChoiceTotDAO();
	
	public static ChoiceTotDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		
		return conn;
	}
	public ChoiceTotVO JPTtot(int qidx) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select * from jptchoiceinfo where qidx=?";
		ChoiceTotVO JPTtotvo =new ChoiceTotVO();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			rs=pstmt.executeQuery();
		
			
			while(rs.next()) {
			JPTtotvo.setAtot(rs.getInt("answera"));
			JPTtotvo.setBtot(rs.getInt("answerb"));
			JPTtotvo.setCtot(rs.getInt("answerc"));
			JPTtotvo.setDtot(rs.getInt("answerd"));
			JPTtotvo.setResponses(rs.getInt("responses"));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {}
		}
		return JPTtotvo;
	}
	public int JPTtotAup(int qqidx , int at) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="UPDATE jptchoiceinfo SET answera = ? WHERE qidx = ?";
		int row =0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);

		
			
			pstmt.setInt(1, at);
			pstmt.setInt(2, qqidx);
			pstmt.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {}
		}
		return row;
	}public int JPTtotBup(int qidx , int bt) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="UPDATE jptchoiceinfo SET answerb = ? WHERE qidx = ?";
		int row =0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
		
			
			pstmt.setInt(1, bt);
			pstmt.setInt(2, qidx);
			rs=pstmt.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {}
		}
		return row;
	}public int JPTtotCup(int qidx , int ct) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="UPDATE jptchoiceinfo SET answerc = ? WHERE qidx = ?";
		int row =0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
		
			
			pstmt.setInt(1, ct);
			pstmt.setInt(2, qidx);
			rs=pstmt.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {}
		}
		return row;
	}public int JPTtotDup(int qidx , int dt) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="UPDATE jptchoiceinfo SET answerd = ? WHERE qidx = ?";
		int row =0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, dt);
			pstmt.setInt(2, qidx);
			rs=pstmt.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {}
		}
		return row;
	}
}
