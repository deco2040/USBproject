package jptflog.model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class QuestionPartDAO {
	private QuestionPartDAO() {}

	private static QuestionPartDAO instance = new QuestionPartDAO();
	
	private static final Pattern PATTERN_BRACKET = Pattern.compile("\\（[^\\（\\）]+\\）");
    private static final String VOID = "";

	public static QuestionPartDAO getInstance() {
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
	
	
	  private static String findBracketTextByPattern(String text){
	       String bracketTextList = "";
	 
	        Matcher matcher = PATTERN_BRACKET.matcher(text);
	        
	        String pureText = text;
	        String findText = new String();
	        
	        while(matcher.find()) {
	            int startIndex = matcher.start();
	            int endIndex = matcher.end();
	            
	            findText = pureText.substring(startIndex+1, endIndex-1);
	            pureText = pureText.replace(findText, VOID);
	            matcher = PATTERN_BRACKET.matcher(pureText);
	 
	            /** 추출된 괄호 데이터를 삽입  **/
	            bracketTextList += findText + "/";
	        }
	        return bracketTextList;
	    }
	  
	
	public List<QuestionVO> JPTpart5(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select B.* from(select A.* from(select * from jptquestion where difficulty=1 and part = 5)A order by dbms_random.random)B where rownum<=5 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=2 and part = 5)A order by dbms_random.random)B where rownum<=6 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=3 and part = 5)A order by dbms_random.random)B where rownum<=4";
		List<QuestionVO> part5list = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			QuestionVO part5VO =new QuestionVO();	
			part5VO.setChoice(rs.getString("choice").split("/"));
			part5VO.setAnswer(rs.getString("answer"));
			part5VO.setDifficulty(rs.getInt("difficulty"));
			part5VO.setPart(rs.getInt("part"));
			part5VO.setQidx(rs.getInt("qidx"));
			part5VO.setQuestion(rs.getString("question"));
			part5list.add(part5VO);
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
		return part5list;
	}
	
	public List<QuestionVO> JPTpart6(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		String sql ="select B.* from(select A.* from(select * from jptquestion where difficulty=1 and part = 6)A order by dbms_random.random)B where rownum<=5 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=2 and part = 6)A order by dbms_random.random)B where rownum<=6 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=3 and part = 6)A order by dbms_random.random)B where rownum<=4";
			
		List<QuestionVO> part6list = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
	
			rs=pstmt.executeQuery();
			
		while(rs.next()) {
			QuestionVO part6VO =new QuestionVO();		
			part6VO.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));
			part6VO.setAnswer(rs.getString("answer"));
			part6VO.setDifficulty(rs.getInt("difficulty"));
			part6VO.setPart(rs.getInt("part"));
			part6VO.setQidx(rs.getInt("qidx"));
			part6VO.setQuestion(rs.getString("question"));
			part6list.add(part6VO);
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
		return part6list;
	}
	
	public List<QuestionVO> JPTpart8(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		Random rd = new Random();
		int part8 = rd.nextInt(7) +1;
		String reading = 'q' + Integer.toString(part8);
		
		String sql = "select B.* from(select A.* from(select * from jptquestion where reading = ?)A order by dbms_random.random)B";
		List<QuestionVO> JPTreading = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reading);
			rs=pstmt.executeQuery();
			
		while(rs.next()) {
			QuestionVO JPTreadingVO =new QuestionVO();
		
			JPTreadingVO.setChoice(rs.getString("choice").split("/"));
			JPTreadingVO.setAnswer(rs.getString("answer"));
			JPTreadingVO.setDifficulty(rs.getInt("difficulty"));
			JPTreadingVO.setPart(rs.getInt("part"));
			JPTreadingVO.setQidx(rs.getInt("qidx"));
			JPTreadingVO.setReading(rs.getString("reading"));
			JPTreadingVO.setQuestion(rs.getString("question"));
			JPTreading.add(JPTreadingVO);
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
		return JPTreading;
	}
	
	public List<QuestionVO> JPTpart7(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select B.* from(select A.* from(select * from jptquestion where difficulty=1 and part = 7)A order by dbms_random.random)B where rownum<=5 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=2 and part = 7)A order by dbms_random.random)B where rownum<=6 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=3 and part = 7)A order by dbms_random.random)B where rownum<=4";
		List<QuestionVO> part7list = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			QuestionVO part7VO =new QuestionVO();
					
			part7VO.setChoice(rs.getString("choice").split("/"));
			part7VO.setAnswer(rs.getString("answer"));
			part7VO.setDifficulty(rs.getInt("difficulty"));
			part7VO.setPart(rs.getInt("part"));
			part7VO.setQidx(rs.getInt("qidx"));
			part7VO.setQuestion(rs.getString("question"));
			part7list.add(part7VO);
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
		return part7list;
	}
	
	
	
	public Set<Integer> getFavorite(String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="SELECT FAVORITE FROM SLOVEDQUESTION WHERE email = ?";
		
		String favorite = null;
		String[] favoriteArray = null;
		Set<Integer> favoriteList=new HashSet<Integer>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				favorite = rs.getString("FAVORITE");			
			}
			
			if(favorite != null && favorite.length() !=0) {
				favoriteArray = favorite.split(",");
			
		
			for(int i=1; i<favoriteArray.length; i++) {
				if(!favoriteArray[i].equals("")) {
					favoriteList.add(Integer.parseInt(favoriteArray[i]));
					}
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
		return favoriteList;
	}
	
	public int deleteFavorite(String qidx, String email) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="UPDATE SLOVEDQUESTION SET (FAVORITE) = (SELECT REPLACE(FAVORITE, '," + qidx + "', '') FROM SLOVEDQUESTION where email = ?) where email=? ";
		
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
	
	
	
	
	
}
