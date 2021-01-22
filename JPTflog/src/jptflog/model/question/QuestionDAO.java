package jptflog.model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class QuestionDAO {
	private QuestionDAO() {}

	private static QuestionDAO instance = new QuestionDAO();
	
	private static final Pattern PATTERN_BRACKET = Pattern.compile("\\（[^\\（\\）]+\\）");
    private static final String VOID = "";

	public static QuestionDAO getInstance() {
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

	
	public String partResult(Set<Integer> qidxs) {
			
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
	  
	
	public List<QuestionVO> expectationQuestions(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select * from EXPECTATIONQUESTION";
		List<QuestionVO> exeptionQuestionList = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			QuestionVO exeption =new QuestionVO();
			
			if(6==rs.getInt("part")) {
				exeption.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));	
			}else {
				exeption.setChoice(rs.getString("choice").split("/"));
			}
			exeption.setAnswer(rs.getString("answer"));
			exeption.setPart(rs.getInt("part"));
			exeption.setQuestion(rs.getString("question"));
			exeptionQuestionList.add(exeption);
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
		return exeptionQuestionList;
	}
	
	
	public List<QuestionVO> JPTqListDi1(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select B.* from(select A.* from(select * from jptquestion where difficulty=1 and part = 5)A order by dbms_random.random)B where rownum<=1 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=1 and part = 6)A order by dbms_random.random)B where rownum<=1 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=1 and part = 7)A order by dbms_random.random)B where rownum<=1";
		List<QuestionVO> JPTqList1 = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			QuestionVO JPTq1VO =new QuestionVO();
			
			if(6==rs.getInt("part")) {
				JPTq1VO.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));	
			}else {
				JPTq1VO.setChoice(rs.getString("choice").split("/"));
			}
			
			JPTq1VO.setAnswer(rs.getString("answer"));
			JPTq1VO.setDifficulty(rs.getInt("difficulty"));
			JPTq1VO.setPart(rs.getInt("part"));
			JPTq1VO.setQidx(rs.getInt("qidx"));
			JPTq1VO.setQuestion(rs.getString("question"));
			JPTqList1.add(JPTq1VO);
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
		return JPTqList1;
	}
	
	public List<QuestionVO> JPTqListDi2(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		String sql ="select B.* from(select A.* from(select * from jptquestion where difficulty=2 and part = 5)A order by dbms_random.random)B where rownum<=1 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=2 and part = 6)A order by dbms_random.random)B where rownum<=1 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=2 and part = 7)A order by dbms_random.random)B where rownum<=1";
			
		List<QuestionVO> JPTqList2 = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
	
			rs=pstmt.executeQuery();
			
		while(rs.next()) {
			QuestionVO JPTq2VO =new QuestionVO();
			
			if(6==rs.getInt("part")) {
				JPTq2VO.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));
				
			}else {
				JPTq2VO.setChoice(rs.getString("choice").split("/"));
			}
			
			JPTq2VO.setAnswer(rs.getString("answer"));
			JPTq2VO.setDifficulty(rs.getInt("difficulty"));
			JPTq2VO.setPart(rs.getInt("part"));
			JPTq2VO.setQidx(rs.getInt("qidx"));
			JPTq2VO.setQuestion(rs.getString("question"));
			JPTqList2.add(JPTq2VO);
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
		return JPTqList2;
	}
	
	public List<QuestionVO> JPTreadingList(){
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
	
	public List<QuestionVO> JPTqListDi3(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select B.* from(select A.* from(select * from jptquestion where difficulty=3 and part = 5)A order by dbms_random.random)B where rownum<=1 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=3 and part = 6)A order by dbms_random.random)B where rownum<=1 union "
				+ "select B.* from(select A.* from(select * from jptquestion where difficulty=3 and part = 7)A order by dbms_random.random)B where rownum<=1";
		List<QuestionVO> JPTqList3 = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			QuestionVO JPTq3VO =new QuestionVO();
					
			if(6==rs.getInt("part")) {
				JPTq3VO.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));
				
			}else {
				JPTq3VO.setChoice(rs.getString("choice").split("/"));
			}
			
			JPTq3VO.setAnswer(rs.getString("answer"));
			JPTq3VO.setDifficulty(rs.getInt("difficulty"));
			JPTq3VO.setPart(rs.getInt("part"));
			JPTq3VO.setQidx(rs.getInt("qidx"));
			JPTq3VO.setQuestion(rs.getString("question"));
			JPTqList3.add(JPTq3VO);
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
		return JPTqList3;
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
	
	public int deletejptinfo() {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql ="delete from JPTCHOICEINFO where RESPONSES =0";
		
		int row = 0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
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
	
	
	public List<QuestionVO> incorrectHigh(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		ResultSet rs2 =null;
		String sql ="select * from (select a.qidx,DECODE(a.answer,'a',(B.ANSWERA/B.RESPONSES),"
															+ "'b',(B.ANSWERB/B.RESPONSES),"
															+ "'c',(B.ANSWERC/B.RESPONSES),"
															+ "'d',(B.ANSWERD/B.RESPONSES)) incorrectrate from JPTQUESTION a, JPTCHOICEINFO b where a.qidx=b.qidx order by incorrectrate,dbms_random.random)"
															+ " where rownum<=15";
		
		String sql2 ="select * from JPTQUESTION where qidx = ?";
		
		List<Integer> incorrectHighQidx = new ArrayList<Integer>();
		List<QuestionVO> incorrectHighList = new ArrayList<QuestionVO>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				incorrectHighQidx.add(rs.getInt("qidx"));
			}
			
			pstmt=conn.prepareStatement(sql2);
			
			for(int i=0; i<incorrectHighQidx.size(); i++) {
				pstmt.setInt(1, incorrectHighQidx.get(i));
				rs2=pstmt.executeQuery();
				
				while(rs2.next()) {
					QuestionVO incorrectHighVO =new QuestionVO();
					if(6==rs2.getInt("part")) {
						incorrectHighVO.setChoice(findBracketTextByPattern(rs2.getString("question")).split("/"));
						
					}else {
						incorrectHighVO.setChoice(rs2.getString("choice").split("/"));
					}
					
					incorrectHighVO.setAnswer(rs2.getString("answer"));
					incorrectHighVO.setDifficulty(rs2.getInt("difficulty"));
					incorrectHighVO.setPart(rs2.getInt("part"));
					incorrectHighVO.setQidx(rs2.getInt("qidx"));
					incorrectHighVO.setQuestion(rs2.getString("question"));
					
					incorrectHighList.add(incorrectHighVO);
				}
						
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
		return incorrectHighList;
	}
	
	
	

	public List<QuestionVO> weekquestion(String email){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		ResultSet rs2 =null;
		
		String sql2 ="select B.* from(select A.* from(select * from jptquestion where weekpoint= ?)A order by dbms_random.random)B where rownum<=13 union "
					+ "select B.* from(select A.* from(select * from jptquestion where weekpoint= ?)A order by dbms_random.random)B where rownum<=9 union "
					+ "select B.* from(select A.* from(select * from jptquestion where weekpoint= ?)A order by dbms_random.random)B where rownum<=5 union "
					+ "select B.* from(select A.* from(select * from jptquestion where weekpoint= ?)A order by dbms_random.random)B where rownum<=3 ";
		
		String sql = "select * from MEMBERWEEKINFO where email =?";
		
		List<QuestionVO> weekquestionList = new ArrayList<QuestionVO>();
		Map<Integer, Integer> weekinfo = new HashMap<Integer, Integer>();
		List<Integer> mostweek = new ArrayList<Integer>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				weekinfo.put(1,rs.getInt("voca"));
				weekinfo.put(2,rs.getInt("idiom"));
				weekinfo.put(3,rs.getInt("proverb"));
				weekinfo.put(4,rs.getInt("kanji"));
				weekinfo.put(5,rs.getInt("grammer"));
				weekinfo.put(6,rs.getInt("reading"));
				weekinfo.put(7,rs.getInt("info"));
				weekinfo.put(8,rs.getInt("sentence"));
			}
			
			
			List<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(weekinfo.entrySet());

			// 비교함수 Comparator를 사용하여 내림 차순으로 정렬
			Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2)
				{
					// 내림 차순으로 정렬
					return obj2.getValue().compareTo(obj1.getValue());
				}			
			});
			
			for(Entry<Integer, Integer> entry : list_entries) {
				mostweek.add(entry.getKey());
			}
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, mostweek.get(0));
			pstmt.setInt(2, mostweek.get(1));
			pstmt.setInt(3, mostweek.get(2));
			pstmt.setInt(4, mostweek.get(3));
			
			rs2=pstmt.executeQuery();
			
		while(rs2.next()) {
			QuestionVO weekquestion =new QuestionVO();
			
			if(6==rs2.getInt("part")) {
				weekquestion.setChoice(findBracketTextByPattern(rs2.getString("question")).split("/"));
				
			}else {
				weekquestion.setChoice(rs2.getString("choice").split("/"));
			}
			
			weekquestion.setAnswer(rs2.getString("answer"));
			weekquestion.setDifficulty(rs2.getInt("difficulty"));
			weekquestion.setPart(rs2.getInt("part"));
			weekquestion.setQidx(rs2.getInt("qidx"));
			weekquestion.setQuestion(rs2.getString("question"));
			weekquestionList.add(weekquestion);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				rs2.close();
				pstmt.close();
				conn.close();	
			}catch (Exception e) {}
		}
		return weekquestionList;
	}
	
	public List<QuestionVO> correctQuestion(String correct){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select * from jptquestion where qidx = ?";
				
		List<QuestionVO> correctQuestions = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);

			correct = correct.replace("[", "").replace("]", "").replace(" ", "");
			String [] correctArray = correct.split(",");
			
			
			for(int i=0; i<correctArray.length; i++) {
				
				pstmt.setInt(1, Integer.parseInt(correctArray[i]));
				rs=pstmt.executeQuery();	
				
				while(rs.next()) {
					QuestionVO correctQuestion =new QuestionVO();
					
					if(6==rs.getInt("part")) {
						correctQuestion.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));	
					}else {
						correctQuestion.setChoice(rs.getString("choice").split("/"));
					}
					
					correctQuestion.setAnswer(rs.getString("answer"));
					correctQuestion.setDifficulty(rs.getInt("difficulty"));
					correctQuestion.setPart(rs.getInt("part"));
					correctQuestion.setQidx(rs.getInt("qidx"));
					correctQuestion.setQuestion(rs.getString("question"));
					correctQuestions.add(correctQuestion);
			}
		
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
		return correctQuestions;
	}
	
	
	public List<QuestionVO> incorrectQuestion(String incorrect){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql ="select * from jptquestion where qidx = ?";
				
		List<QuestionVO> incorrectQuestions = new ArrayList<QuestionVO>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);

			incorrect = incorrect.replace("[", "").replace("]", "").replace(" ", "");
			String [] incorrectArray = incorrect.split(",");
			
			
			for(int i=0; i<incorrectArray.length; i++) {
				
				pstmt.setInt(1, Integer.parseInt(incorrectArray[i]));
				rs=pstmt.executeQuery();	
				
				while(rs.next()) {
					QuestionVO incorrectQuestion =new QuestionVO();
					
					if(6==rs.getInt("part")) {
						incorrectQuestion.setChoice(findBracketTextByPattern(rs.getString("question")).split("/"));	
					}else {
						incorrectQuestion.setChoice(rs.getString("choice").split("/"));
					}
					
					incorrectQuestion.setAnswer(rs.getString("answer"));
					incorrectQuestion.setDifficulty(rs.getInt("difficulty"));
					incorrectQuestion.setPart(rs.getInt("part"));
					incorrectQuestion.setQidx(rs.getInt("qidx"));
					incorrectQuestion.setQuestion(rs.getString("question"));
					incorrectQuestions.add(incorrectQuestion);
			}
		
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
		return incorrectQuestions;
	}
	
	
	
}
