package jptflog.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jptflog.model.week.MemberWeekVO;

public class UserDAO {
	private UserDAO() {}

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	// 커넥션 풀 설정
	private static Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		return conn;
	}
	
	

	public int insertMemberWeek(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="INSERT into MEMBERWEEKINFO(idx, email) select idx, email from memberinfo where email = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	public int insertSolvedQuestion(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="INSERT into SLOVEDQUESTION(idx, email) select idx, email from memberinfo where email = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	public int checkMemberWeek(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(MAX(IDX),-99)as idx FROM MEMBERWEEKINFO WHERE EMAIL = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("idx") != -99) {
					row = 1;
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	public int checksolvedQuestion(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(MAX(IDX),-99)as idx FROM SLOVEDQUESTION WHERE EMAIL = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("idx") != -99) {
					row = 1;
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	public int nullchecksolvedQuestion(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(CORRECTQUESTIONS,-99) as co,NVL(INCORRECTQUESTIONS,-99)as inco FROM SLOVEDQUESTION WHERE EMAIL = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("co").equals("-99") && rs.getString("inco").equals("-99")) {
					row = 1;
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	
	public int checkGoal(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(MAX(IDX),-99)as idx FROM MEMBERGOAL WHERE EMAIL = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("idx") != -99) {
					row = 1;
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	public int insertGoal(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="INSERT into MEMBERGOAL(idx, email) select idx, email from memberinfo where email = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	public int insertGoal(String email, int goal) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="UPDATE MEMBERGOAL SET GOAL = ? WHERE email = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, goal);
			pstmt.setString(1, email);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	
	
	public UserGoalVO mygoal(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from MEMBERGOAL where email=?";
		UserGoalVO goal = new UserGoalVO();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goal.setIdx(rs.getInt("idx"));
				goal.setEmail(rs.getString("email"));
				goal.setGoal(rs.getInt("goal"));
				
				goal.setDay1(rs.getString("day1"));
				goal.setDay2(rs.getString("day2"));
				goal.setDay3(rs.getString("day3"));
				goal.setDay4(rs.getString("day4"));
				goal.setDay5(rs.getString("day5"));
				goal.setDay6(rs.getString("day6"));
				goal.setDay7(rs.getString("day7"));
				goal.setDay8(rs.getString("day8"));
				goal.setDay9(rs.getString("day9"));
				goal.setDay10(rs.getString("day10"));
				
				goal.setGrade1(rs.getInt("grade1"));
				goal.setGrade2(rs.getInt("grade2"));
				goal.setGrade3(rs.getInt("grade3"));
				goal.setGrade4(rs.getInt("grade4"));
				goal.setGrade5(rs.getInt("grade5"));
				goal.setGrade6(rs.getInt("grade6"));
				goal.setGrade7(rs.getInt("grade7"));
				goal.setGrade8(rs.getInt("grade8"));
				goal.setGrade9(rs.getInt("grade9"));
				goal.setGrade10(rs.getInt("grade10"));
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return goal;
	}
	
	
	public int idCheck(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(MAX(EMAIL),-99)as email FROM MEMBERINFO WHERE EMAIL = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("email").equals("-99")) {
					row = 1;
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	
	public int checkMemberGoal(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(MAX(IDX),-99)as idx FROM MEMBERGOAL WHERE EMAIL = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("idx") != -99) {
					row = 1;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	public int getMemberGoal(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select NVL(MAX(goal),-99)as goal from membergoal where email = ?";
		
		int goal = -99;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				goal = rs.getInt("goal");		
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return goal;
	}
	
	public int insertMemberGoal(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="INSERT into MEMBERGOAL(idx, email) select idx, email from memberinfo where email = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	public double getMemberExpect(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		UserGoalVO vo = mygoal(email);
		
		double expert = -99;
		String sql = "";
		
		if((vo.getDay1() == null || vo.getDay1().length() ==0 )&& (vo.getDay2() == null ||vo.getDay2().length() ==0)) {
			return -99;
		}
		if((vo.getDay2() == null || vo.getDay2().length() ==0 )&& (vo.getDay1() != null &&vo.getDay1().length() !=0)) {
			sql ="select grade1 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay3() == null || vo.getDay3().length() ==0 )&& (vo.getDay2() != null &&vo.getDay2().length() !=0)) {
			sql ="select grade2 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay4() == null || vo.getDay4().length() ==0 )&& (vo.getDay3() != null &&vo.getDay3().length() !=0)) {
			sql ="select grade3 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay5() == null || vo.getDay5().length() ==0 )&& (vo.getDay4() != null &&vo.getDay4().length() !=0)) {
			sql ="select grade4 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay6() == null || vo.getDay6().length() ==0 )&& (vo.getDay5() != null &&vo.getDay5().length() !=0)) {
			sql ="select grade5 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay7() == null || vo.getDay7().length() ==0 )&& (vo.getDay6() != null &&vo.getDay6().length() !=0)) {
			sql ="select grade6 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay8() == null || vo.getDay8().length() ==0 )&& (vo.getDay7() != null &&vo.getDay7().length() !=0)) {
			sql ="select grade7 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay9() == null || vo.getDay9().length() ==0 )&& (vo.getDay8() != null &&vo.getDay8().length() !=0)) {
			sql ="select grade8 as grade from MEMBERGOAL where email = ?";	
		}
		if((vo.getDay10() == null || vo.getDay10().length() ==0 )&& (vo.getDay9() != null &&vo.getDay9().length() !=0)) {
			sql ="select grade9 as grade from MEMBERGOAL where email = ?";	
		}
		if(vo.getDay10() != null && vo.getDay10().length() !=0) {
			sql ="select grade10 as grade from MEMBERGOAL where email = ?";	
		}
			
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
					
			while(rs.next()) {
				expert = rs.getDouble("grade");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return expert;
			
	}
	
	
	public int updateMemberExpect(String email, double grade) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		UserGoalVO vo = mygoal(email);
		
		String sql = "";
		
		if((vo.getDay1() == null || vo.getDay1().length() ==0 )&& (vo.getDay2() == null ||vo.getDay2().length() ==0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY1,GRADE1) = (SELECT sysdate, ? FROM DUAL) where email = ?";	
		}
		if((vo.getDay2() == null || vo.getDay2().length() ==0 )&& (vo.getDay1() != null &&vo.getDay1().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY2,GRADE2) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay3() == null || vo.getDay3().length() ==0 )&& (vo.getDay2() != null &&vo.getDay2().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY3,GRADE3) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay4() == null || vo.getDay4().length() ==0 )&& (vo.getDay3() != null &&vo.getDay3().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY4,GRADE4) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay5() == null || vo.getDay5().length() ==0 )&& (vo.getDay4() != null &&vo.getDay4().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY5,GRADE5) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay6() == null || vo.getDay6().length() ==0 )&& (vo.getDay5() != null &&vo.getDay5().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY6,GRADE6) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay7() == null || vo.getDay7().length() ==0 )&& (vo.getDay6() != null &&vo.getDay6().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY7,GRADE7) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay8() == null || vo.getDay8().length() ==0 )&& (vo.getDay7() != null &&vo.getDay7().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY8,GRADE8) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay9() == null || vo.getDay9().length() ==0 )&& (vo.getDay8() != null &&vo.getDay8().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY9,GRADE9) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if((vo.getDay10() == null || vo.getDay10().length() ==0 )&& (vo.getDay9() != null &&vo.getDay9().length() !=0)) {
			sql ="UPDATE MEMBERGOAL SET (DAY10,GRADE10) = (SELECT sysdate, ? FROM DUAL) where email = ?";
		}
		if(vo.getDay10() != null && vo.getDay10().length() !=0) {
			sql = "UPDATE MEMBERGOAL SET (DAY1,GRADE1) = (SELECT DAY2, GRADE2 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY2,GRADE2) = (SELECT DAY3, GRADE3 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY3,GRADE3) = (SELECT DAY4, GRADE4 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY4,GRADE4) = (SELECT DAY5, GRADE5 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY5,GRADE5) = (SELECT DAY6, GRADE6 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY6,GRADE6) = (SELECT DAY7, GRADE7 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY7,GRADE7) = (SELECT DAY8, GRADE8 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY8,GRADE8) = (SELECT DAY9, GRADE9 FROM MEMBERGOAL where email = ?) where email = ? "
					+ "UPDATE MEMBERGOAL SET (DAY9,GRADE9) = (SELECT sysdate, ? FROM DUAL) where email = ? ";
		}
			
		int row = 0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			if(vo.getDay10() != null && vo.getDay10().length() !=0) {
				pstmt.setString(1, email);
				pstmt.setString(2, email);
				pstmt.setString(3, email);
				pstmt.setString(4, email);
				pstmt.setString(5, email);
				pstmt.setString(6, email);
				pstmt.setString(7, email);
				pstmt.setString(8, email);
				pstmt.setString(9, email);
				pstmt.setString(10, email);
				pstmt.setString(11, email);
				pstmt.setString(12, email);
				pstmt.setString(13, email);
				pstmt.setString(14, email);
				pstmt.setString(15, email);
				pstmt.setString(16, email);
				pstmt.setDouble(17, grade);
				pstmt.setString(18, email);
			}else {
				pstmt.setDouble(1, grade);
				pstmt.setString(2, email);		
			}
					
			row = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
			
	}
	
	
	public int insertUser(UserVO vo) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="INSERT INTO MEMBERINFO (IDX, EMAIL, GENDER, PASSWORD, ADDRESS1, ADDRESS2, TEL)"
				+ "VALUES (MEMBER_SEQ.nextval, ?, ?, ? ,?, ?, ?)";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getAddress1());
			pstmt.setString(5, vo.getAddress2());
			pstmt.setString(6, vo.getTel());
			
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	public int updatePasswd(String email, String passwd) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		String sql = "UPDATE MEMBERINFO SET PASSWORD = ? WHERE EMAIL = ?";	
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
			
	}
	
	
	
}
