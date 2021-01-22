package jptflog.model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QuestionCalDAO {
	
	private QuestionCalDAO() {}

	private static QuestionCalDAO instance = new QuestionCalDAO();
	
	public static QuestionCalDAO getInstance() {
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
	
	
	public int checkJPTinfo(int qidx) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT NVL(MAX(QIDX),-99)as qidx FROM JPTCHOICEINFO WHERE QIDX = ?";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("qidx") != -99) {
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
	

	
	

	public int insertJptinfo(int qidx) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="INSERT into JPTCHOICEINFO(qidx) values(?)";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
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
	
	

	
	public int updateSolvedQuestionIncorrect(String email, String incorrect) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		String sql = "UPDATE SLOVEDQUESTION SET (INCORRECTQUESTIONS) = "
				+ "(SELECT CONCAT(INCORRECTQUESTIONS, '," + incorrect + "') FROM SLOVEDQUESTION where email=?) where email=?";	
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, email);
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
	
	public int updateSolvedQuestioncorrect(String email, String correct) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		String sql = "UPDATE SLOVEDQUESTION SET (CORRECTQUESTIONS) = "
				+ "(SELECT CONCAT(CORRECTQUESTIONS, '," + correct + "') FROM SLOVEDQUESTION where email=?) where email=?";	
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
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
	
	
	public int updateSolvedQuestionfavorite(String email, Set<Integer> favorite) {
		Connection conn =null;
		PreparedStatement pstmt =null;
	
		String favorite2 = favorite.toString().replace("[", "").replace("]", "").replace(" ", "");
		
		String sql = "UPDATE SLOVEDQUESTION SET (FAVORITE) = "
				+ "(SELECT CONCAT(FAVORITE, '," + favorite2 + "') FROM SLOVEDQUESTION where email=? ) where email=?";	
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
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
	
	public int updateJPTinfo(int qidx, int choice) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String choice2 = null;
		
			if(choice ==1) {
				choice2 = "A";
			}
			if(choice ==2) {
				choice2 = "B";
			}
			if(choice ==3) {
				choice2 = "C";
			}
			if(choice ==4) {
				choice2 = "D";
			}
			
		String sql ="UPDATE jptchoiceinfo SET (RESPONSES, ANSWER" + choice2 + ") = (select RESPONSES+1, ANSWER" + choice2 + "+1 "
																	+ "from jptchoiceinfo WHERE qidx = ?) WHERE qidx = ?";	
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			pstmt.setInt(2, qidx);
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
	
	
	public String convertWeek(int qidx) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT weekname FROM week WHERE weekid = (select weekpoint from JPTQUESTION where qidx= ?);";
		
		String weekname = null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				weekname = rs.getString("weekname");			
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
		return weekname;
	}
	
	
	public int checkCorrect(int qidx, int choice) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT answer FROM JPTQUESTION WHERE QIDX = ?";
		
		String choice2 = null;
		
		if(choice ==1) {
			choice2 = "a";
		}
		if(choice ==2) {
			choice2 = "b";
		}
		if(choice ==3) {
			choice2 = "c";
		}
		if(choice ==4) {
			choice2 = "d";
		}
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("answer").equals(choice2)) {
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
	
	
	public String partResult(List<Integer> qidxs) {
		
		String part = "";
	
		if(qidxs.contains(5)) {
			part = "5";	
		}
		if(qidxs.contains(6)) {
			part = "6";	
		}
		if(qidxs.contains(7)) {
			part = "7";	
		}
		if(qidxs.contains(8)) {
			part = "8";	
		}
		if(qidxs.contains(5) && qidxs.contains(6) && qidxs.contains(7) && qidxs.contains(8)) {
			part = "allpart";	
		}
				
		return part;
	}
  
	
	
	
	public String checkCorrect(int qidx) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT answer FROM JPTQUESTION WHERE QIDX = ?";
		
		String answer = null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				answer = rs.getString("answer");
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
		return answer;
	}
	
	

	public int updateMemberWeekinfo(String email, int qidx) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		PreparedStatement pstmt2 =null;
		ResultSet rs =null;
		
		String sql = "SELECT weekname FROM week WHERE weekid = (select weekpoint from JPTQUESTION where qidx= ?)";
		
		int row = 0;
		String weekname = null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qidx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				weekname = rs.getString("weekname");
			}
			
			String sql2 ="UPDATE MEMBERWEEKINFO SET (TOTINCORRECT, " + weekname + ") = (select TOTINCORRECT+1, " +weekname +"+1 "
					+ "from MEMBERWEEKINFO WHERE email = ?) WHERE email = ?";	
					
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, email);
			pstmt2.setString(2, email);
			row = pstmt2.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				pstmt2.close();
				conn.close();	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return row;
			
	}
	
	public int updateMemberWeekCorrect(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="UPDATE MEMBERWEEKINFO SET TOTCORRECT = TOTCORRECT +1 where email = ?";	
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
	
	
	public List<Integer> calScore(List<String> correct) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		String sql ="SELECT PART,DIFFICULTY FROM JPTQUESTION WHERE QIDX = ?";	
		List<Integer> score = new ArrayList<Integer>();
		int part5 = 0;
		int part6 = 0;
		int part7 = 0;
		int part8 = 0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			
			
			for(int i =1; i<correct.size(); i++) {	
				pstmt.setInt(1, Integer.parseInt(correct.get(i)));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("PART")==5) {
						if(rs.getInt("DIFFICULTY")==1) {
							part5 += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							part5 +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							part5 +=5;
						}
					}
					if(rs.getInt("PART")==6) {
						if(rs.getInt("DIFFICULTY")==1) {
							part6 += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							part6 +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							part6 +=5;
						}					
					}
					if(rs.getInt("PART")==7) {
						if(rs.getInt("DIFFICULTY")==1) {
							part7 += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							part7 +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							part7 +=5;
						}
					}
					if(rs.getInt("PART")==8) {
						if(rs.getInt("DIFFICULTY")==1) {
							part8 += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							part8 +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							part8 +=5;
						}
					}
					
				
				}				
			}
			
			score.add(part5);
			score.add(part6);
			score.add(part7);
			score.add(part8);
			
			
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
		return score;
			
	}
	
	
	public List<Integer> calScoreWeek(List<String> correct) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		String sql ="SELECT weekpoint,DIFFICULTY FROM JPTQUESTION WHERE QIDX = ?";	
		List<Integer> scoreWeek = new ArrayList<Integer>();
		int voca = 0;
		int idiom = 0;
		int proverb = 0;
		int kanji = 0;
		int grammer = 0;
		int reading = 0;
		int info = 0;
		int sentence = 0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);	
			
			for(int i =0; i<correct.size(); i++) {	
				pstmt.setInt(1, Integer.parseInt(correct.get(i)));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("weekpoint")==1) {
						if(rs.getInt("DIFFICULTY")==1) {
							voca += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							voca +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							voca +=5;
						}
					}
					if(rs.getInt("weekpoint")==2) {
						if(rs.getInt("DIFFICULTY")==1) {
							idiom += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							idiom +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							idiom +=5;
						}
					}
					if(rs.getInt("weekpoint")==3) {
						if(rs.getInt("DIFFICULTY")==1) {
							proverb += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							proverb +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							proverb +=5;
						}
					}
					if(rs.getInt("weekpoint")==4) {
						if(rs.getInt("DIFFICULTY")==1) {
							kanji += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							kanji +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							kanji +=5;
						}					
					}
					if(rs.getInt("weekpoint")==5) {
						if(rs.getInt("DIFFICULTY")==1) {
							grammer += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							grammer +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							grammer +=5;
						}
					}
					if(rs.getInt("weekpoint")==6) {
						if(rs.getInt("DIFFICULTY")==1) {
							reading += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							reading +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							reading +=5;
						}
					}
					if(rs.getInt("weekpoint")==7) {
						if(rs.getInt("DIFFICULTY")==1) {
							info += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							info +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							info +=5;
						}
					}
					if(rs.getInt("weekpoint")==8) {
						if(rs.getInt("DIFFICULTY")==1) {
							sentence += 1;
						}
						if(rs.getInt("DIFFICULTY")==2) {
							sentence +=3;
						}
						if(rs.getInt("DIFFICULTY")==3) {
							sentence +=5;
						}
					}
					
				
				}				
			}
			
			scoreWeek.add(voca);
			scoreWeek.add(idiom);
			scoreWeek.add(proverb);
			scoreWeek.add(kanji);
			scoreWeek.add(grammer);
			scoreWeek.add(reading);
			scoreWeek.add(info);
			scoreWeek.add(sentence);
			
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
		return scoreWeek;
			
	}
	
	
	public Set<Integer> weekExsit(List<String> correct) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		String sql ="SELECT weekpoint FROM JPTQUESTION WHERE QIDX = ?";	
		Set<Integer> week = new HashSet<Integer>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);	
			
			for(int i =0; i<correct.size(); i++) {	
				pstmt.setInt(1, Integer.parseInt(correct.get(i)));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
						week.add(rs.getInt("weekpoint"));
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
		return week;
			
	}
	
	
	
}
