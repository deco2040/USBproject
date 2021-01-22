package jptflog.model.week;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jptflog.model.question.QuestionVO;

public class MemberWeekDAO {
	private MemberWeekDAO() {
	}

	private static MemberWeekDAO instance = new MemberWeekDAO();

	public static MemberWeekDAO getInstnce() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();

		return conn;
	}

	public MemberWeekVO qweekch(int qqidx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select weekpoint from jptquestion where qidx=?";

		MemberWeekVO memvo = new MemberWeekVO();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qqidx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memvo.setWeekcheck(rs.getInt("weekpoint"));
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
		return memvo;
	}

	public int weekup(String email, String weekch) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE memberweekinfo set " + weekch + " = " + weekch
				+ "+1 where memberid=(select memberid from memberinfo where email=?)";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return row;
	}

	public int totcorrup(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE memberweekinfo set totcorrect = totcorrect+1 where memberid=(select memberid from memberinfo where email=?)";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return row;
	}

	public int totincorrup(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE memberweekinfo set totincorrect = totincorrect+1 where memberid=(select memberid from memberinfo where email=?)";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return row;
	}

	public MemberWeekVO myweek(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memberweekinfo where idx=(select idx from memberinfo where email=?)";
		MemberWeekVO memvo = new MemberWeekVO();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memvo.setIdx(rs.getInt("idx"));
				memvo.setGrammer(rs.getInt("grammer"));
				memvo.setIdiom(rs.getInt("idiom"));
				memvo.setInfo(rs.getInt("info"));
				memvo.setKanji(rs.getInt("kanji"));
				memvo.setProverb(rs.getInt("proverb"));
				memvo.setReading(rs.getInt("reading"));
				memvo.setSentence(rs.getInt("sentence"));
				memvo.setVoca(rs.getInt("voca"));

				memvo.setTotcorrect(rs.getInt("totcorrect"));
				memvo.setTotincorrect(rs.getInt("totincorrect"));
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
		return memvo;
	}
	
	
	public List<HashMap<Integer, Double>> partAnalysis(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ResultSet rs2 = null;
		
		String sql = "select INCORRECTQUESTIONS from SLOVEDQUESTION where email= ?";
		String sql2 = "select part,weekpoint from JPTQUESTION where qidx= ?";
		
		 HashMap<Integer, Double> hashtemp1  = new HashMap<Integer, Double>();
		 HashMap<Integer, Double> hashtemp2  = new HashMap<Integer, Double>();
		 HashMap<Integer, Double> hashtemp3  = new HashMap<Integer, Double>();
		 HashMap<Integer, Double> hashtemp4  = new HashMap<Integer, Double>();
		 ArrayList<HashMap<Integer, Double>> partIncorrect = new ArrayList<HashMap<Integer, Double>>();
		
		ArrayList<Integer> part5 = new ArrayList<Integer>();
		ArrayList<Integer> part6 = new ArrayList<Integer>();
		ArrayList<Integer> part7 = new ArrayList<Integer>();
		ArrayList<Integer> part8 = new ArrayList<Integer>();
		
		HashMap<Integer, Integer> duplicate_count5 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count6 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count7 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count8 = new HashMap<Integer, Integer>();
		
		String incorrects = null;
		String[] incorrectArray = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				incorrects = rs.getString("INCORRECTQUESTIONS");
			}
			
			if(incorrects ==null || incorrects.length() ==0) {
				return null;
			}
			
			incorrectArray = incorrects.split(",");
			pstmt = conn.prepareStatement(sql2);
			
			for(int i =1; i<incorrectArray.length; i++) {	
				pstmt.setInt(1, Integer.parseInt(incorrectArray[i]));
				rs2 = pstmt.executeQuery();
				
				while(rs2.next()) {
					if(rs2.getInt("part")==5) {
						part5.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==6) {
						part6.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==7) {
						part7.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==8) {
						part8.add(rs2.getInt("weekpoint"));			
					}
				}				
			}
			
			duplicate_count5.put(1, 0);
			duplicate_count5.put(2, 0);
			duplicate_count5.put(4, 0);
			duplicate_count5.put(5, 0);
			duplicate_count5.put(7, 0);
			duplicate_count5.put(8, 0);
			
			duplicate_count6.put(1, 0);
			duplicate_count6.put(2, 0);
			duplicate_count6.put(5, 0);
			duplicate_count6.put(6, 0);
			duplicate_count6.put(7, 0);
			duplicate_count6.put(8, 0);
			
			duplicate_count7.put(1, 0);
			duplicate_count7.put(2, 0);
			duplicate_count7.put(3, 0);
			duplicate_count7.put(4, 0);
			duplicate_count7.put(5, 0);
			duplicate_count7.put(6, 0);
			duplicate_count7.put(7, 0);
			duplicate_count7.put(8, 0);
			
			duplicate_count8.put(6, 0);
			duplicate_count8.put(7, 0);
			duplicate_count8.put(8, 0);
	
			for(int i = 0 ; i < part5.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count5.put(part5.get(i), duplicate_count5.get(part5.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part6.size() ; i++){ // ArrayList 만큼 반복 
			    duplicate_count6.put(part6.get(i), duplicate_count6.get(part6.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part7.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count7.put(part7.get(i), duplicate_count7.get(part7.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part8.size() ; i++){ // ArrayList 만큼 반복
			    	duplicate_count8.put(part8.get(i), duplicate_count8.get(part8.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for (Entry<Integer, Integer> entry : duplicate_count5.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			    
			     hashtemp1.put(key, (double)value/part5.size()*100);    		    
			 }
			 partIncorrect.add(hashtemp1);	

			for (Entry<Integer, Integer> entry : duplicate_count6.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     
			     hashtemp2.put(key, (double)value/part6.size()*100);    
			 }
			partIncorrect.add(hashtemp2);

	
			for (Entry<Integer, Integer> entry : duplicate_count7.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     
			     hashtemp3.put(key, (double)value/part7.size()*100);    
			}
			partIncorrect.add(hashtemp3);
		
			for (Entry<Integer, Integer> entry : duplicate_count8.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     
			     hashtemp4.put(key, (double)value/part8.size()*100);    			    			    
			 }
			partIncorrect.add(hashtemp4);
			
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
		return partIncorrect;
	}
	
	
	public List<HashMap<Integer, Integer>> partAnalysis2(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sql = "select INCORRECTQUESTIONS from SLOVEDQUESTION where email= ?";
		String sql2 = "select part,weekpoint from JPTQUESTION where qidx= ?";
		
		ArrayList<HashMap<Integer, Integer>> partIncorrect = new ArrayList<HashMap<Integer, Integer>>();
		
		ArrayList<Integer> part5 = new ArrayList<Integer>();
		ArrayList<Integer> part6 = new ArrayList<Integer>();
		ArrayList<Integer> part7 = new ArrayList<Integer>();
		ArrayList<Integer> part8 = new ArrayList<Integer>();
		
		HashMap<Integer, Integer> duplicate_count5 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count6 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count7 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count8 = new HashMap<Integer, Integer>();
		
		String incorrects = null;
		String[] incorrectArray = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				incorrects = rs.getString("INCORRECTQUESTIONS");
			}
			
			if(incorrects ==null || incorrects.length() ==0) {
				return null;
			}
			
			incorrectArray = incorrects.split(",");
			pstmt = conn.prepareStatement(sql2);
			
			for(int i =1; i<incorrectArray.length; i++) {	
				pstmt.setInt(1, Integer.parseInt(incorrectArray[i]));
				rs2 = pstmt.executeQuery();
				
				while(rs2.next()) {
					if(rs2.getInt("part")==5) {
						part5.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==6) {
						part6.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==7) {
						part7.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==8) {
						part8.add(rs2.getInt("weekpoint"));			
					}
				}				
			}
			
			duplicate_count5.put(1, 0);
			duplicate_count5.put(2, 0);
			duplicate_count5.put(4, 0);
			duplicate_count5.put(5, 0);
			duplicate_count5.put(7, 0);
			duplicate_count5.put(8, 0);
			
			duplicate_count6.put(1, 0);
			duplicate_count6.put(2, 0);
			duplicate_count6.put(5, 0);
			duplicate_count6.put(6, 0);
			duplicate_count6.put(7, 0);
			duplicate_count6.put(8, 0);
			
			duplicate_count7.put(1, 0);
			duplicate_count7.put(2, 0);
			duplicate_count7.put(3, 0);
			duplicate_count7.put(4, 0);
			duplicate_count7.put(5, 0);
			duplicate_count7.put(6, 0);
			duplicate_count7.put(7, 0);
			duplicate_count7.put(8, 0);
			
			duplicate_count8.put(6, 0);
			duplicate_count8.put(7, 0);
			duplicate_count8.put(8, 0);
	
			for(int i = 0 ; i < part5.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count5.put(part5.get(i), duplicate_count5.get(part5.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part6.size() ; i++){ // ArrayList 만큼 반복 
			    duplicate_count6.put(part6.get(i), duplicate_count6.get(part6.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part7.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count7.put(part7.get(i), duplicate_count7.get(part7.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part8.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count8.put(part8.get(i), duplicate_count8.get(part8.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
		
			partIncorrect.add(duplicate_count5);	
			partIncorrect.add(duplicate_count6);
			partIncorrect.add(duplicate_count7);
			partIncorrect.add(duplicate_count8);
			
			
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
		return partIncorrect;
	}
	
	
	
	public HashMap<Integer, Double> correctParsent(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ResultSet rs2 = null;
		
		String sql = "select CORRECTQUESTIONS from SLOVEDQUESTION where email= ?";
		String sql2 = "select weekpoint from JPTQUESTION where qidx= ?";
		
		ArrayList<Integer> power = new ArrayList<Integer>();
		HashMap<Integer, Integer> duplicate_count = new HashMap<Integer, Integer>();
		HashMap<Integer, Double> power_parsent = new HashMap<Integer, Double>();
		
		String corrects = null;
		String[] correctArray = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				corrects = rs.getString("CORRECTQUESTIONS");
			}
			
			if(corrects ==null || corrects.length() ==0) {
				return null;
			}
			correctArray = corrects.split(",");
			
			pstmt = conn.prepareStatement(sql2);
			
			for(int i =1; i<correctArray.length; i++) {	
				pstmt.setInt(1, Integer.parseInt(correctArray[i]));
				rs2 = pstmt.executeQuery();
				
				while(rs2.next()) {
					power.add(rs2.getInt("weekpoint"));
				}				
			}
			
			for(int i = 0 ; i < power.size() ; i++){ // ArrayList 만큼 반복
			    if (duplicate_count.containsKey(power.get(i))) { // HashMap 내부에 이미 key 값이 존재하는지 확인
			    	duplicate_count.put(power.get(i), duplicate_count.get(power.get(i))  + 1);  // key가 이미 있다면 value에 +1
			    } else { // key값이 존재하지 않으면
			    	duplicate_count.put(power.get(i) , 1); // key 값을 생성후 value를 1로 초기화
			    }
			}
						
			for (Entry<Integer, Integer> entry : duplicate_count.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     power_parsent.put(key, (double)value/power.size()*100);
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
		return power_parsent;
	}
	
	public List<ExperdataVO> getExperdata() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sql = "select * from EXPECTTABLE";
		String sql2 = "select answer from EXPECTATIONQUESTION";
		
		ArrayList<ExperdataVO> expertList = new ArrayList<ExperdataVO>();
		ArrayList<String> answer = new ArrayList<String>();
		ArrayList<ExperdataVO> logigrade = new ArrayList<ExperdataVO>();
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {		
				ExperdataVO expert = new ExperdataVO();
				
				expert.setExpectgrade(rs.getInt("EXPECTGRADE"));
				expert.setQ1(rs.getString("Q1"));
				expert.setQ2(rs.getString("Q2"));
				expert.setQ3(rs.getString("Q3"));
				expert.setQ4(rs.getString("Q4"));
				expert.setQ5(rs.getString("Q5"));
				expert.setQ6(rs.getString("Q6"));
				expert.setQ7(rs.getString("Q7"));
				expert.setQ8(rs.getString("Q8"));
				expert.setQ9(rs.getString("Q9"));
				expert.setQ10(rs.getString("Q10"));
				expert.setQ11(rs.getString("Q11"));
				expert.setQ12(rs.getString("Q12"));
				expert.setQ13(rs.getString("Q13"));
				expert.setQ14(rs.getString("Q14"));
				expert.setQ15(rs.getString("Q15"));
		
				expertList.add(expert);
			}
			
			pstmt = conn.prepareStatement(sql2);
			rs2 = pstmt.executeQuery();
			
			while(rs2.next()) {
				answer.add(rs2.getString("answer"));
			}
						
			for(int i=0; i<expertList.size(); i++) {
				ExperdataVO expert2 = new ExperdataVO();
				if(answer.get(0).equals(expertList.get(i).getQ1())) {		
					expert2.setQ1("1");
				}else {
					expert2.setQ1("0");
				}
				if(answer.get(1).equals(expertList.get(i).getQ2())) {
					expert2.setQ2("1");
				}else {
					expert2.setQ2("0");
				}
				if(answer.get(2).equals(expertList.get(i).getQ3())) {
					expert2.setQ3("1");
				}else {
					expert2.setQ3("0");
				}
				if(answer.get(3).equals(expertList.get(i).getQ4())) {
					expert2.setQ4("1");
				}else {
					expert2.setQ4("0");
				}
				if(answer.get(4).equals(expertList.get(i).getQ5())) {
					expert2.setQ5("1");
				}else {
					expert2.setQ5("0");
				}
				if(answer.get(5).equals(expertList.get(i).getQ6())) {
					expert2.setQ6("1");
				}else {
					expert2.setQ6("0");
				}
				if(answer.get(6).equals(expertList.get(i).getQ7())) {
					expert2.setQ7("1");
				}else {
					expert2.setQ7("0");
				}
				if(answer.get(7).equals(expertList.get(i).getQ8())) {
					expert2.setQ8("1");
				}else {
					expert2.setQ8("0");
				}
				if(answer.get(8).equals(expertList.get(i).getQ9())) {
					expert2.setQ9("1");
				}else {
					expert2.setQ9("0");
				}
				if(answer.get(9).equals(expertList.get(i).getQ10())) {
					expert2.setQ10("1");
				}else {
					expert2.setQ10("0");
				}
				if(answer.get(10).equals(expertList.get(i).getQ11())) {
					expert2.setQ11("1");
				}else {
					expert2.setQ11("0");
				}
				if(answer.get(11).equals(expertList.get(i).getQ12())) {
					expert2.setQ12("1");
				}else {
					expert2.setQ12("0");
				}
				if(answer.get(12).equals(expertList.get(i).getQ13())) {
					expert2.setQ13("1");
				}else {
					expert2.setQ13("0");
				}
				if(answer.get(13).equals(expertList.get(i).getQ14())) {
					expert2.setQ14("1");
				}else {
					expert2.setQ14("0");
				}
				if(answer.get(14).equals(expertList.get(i).getQ15())) {
					expert2.setQ15("1");
				}else {
					expert2.setQ15("0");
				}
				expert2.setExpectgrade(expertList.get(i).getExpectgrade());
				
				logigrade.add(expert2);
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
		return logigrade;
	}
	
	public List<Integer> resultData(List<Integer> resultList2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select answer from EXPECTATIONQUESTION";
		
		ArrayList<String> resultList3 = new ArrayList<String>();
		ArrayList<Integer> resultList4 = new ArrayList<Integer>();
		
		for(int i =0; i<resultList2.size(); i++) {
			if(resultList2.get(i)==1) {
				resultList3.add("A");
			}
			if(resultList2.get(i)==2) {
				resultList3.add("B");
			}
			if(resultList2.get(i)==3) {
				resultList3.add("C");
			}
			if(resultList2.get(i)==4) {
				resultList3.add("D");
			}
		}
		
		ArrayList<String> answer = new ArrayList<String>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				answer.add(rs.getString("answer"));
			}

				if(answer.get(0).equals(resultList3.get(0))) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(1).equals(resultList3.get(1) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(2).equals(resultList3.get(2) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(3).equals(resultList3.get(3) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(4).equals(resultList3.get(4) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(5).equals(resultList3.get(5) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(6).equals(resultList3.get(6) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(7).equals(resultList3.get(7) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(8).equals(resultList3.get(8) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(9).equals(resultList3.get(9) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(10).equals(resultList3.get(10) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(11).equals(resultList3.get(11) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(12).equals(resultList3.get(12) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(13).equals(resultList3.get(13) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
				}
				if(answer.get(14).equals(resultList3.get(14) )) {
					resultList4.add(1);
				}else {
					resultList4.add(0);
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
		return resultList4;
	}
	

	public List<HashMap<Integer, Double>> partAnalysis3(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ResultSet rs2 = null;
		
		String sql = "select CORRECTQUESTIONS from SLOVEDQUESTION where email= ?";
		String sql2 = "select part,weekpoint from JPTQUESTION where qidx= ?";
		
		 HashMap<Integer, Double> hashtemp1  = new HashMap<Integer, Double>();
		 HashMap<Integer, Double> hashtemp2  = new HashMap<Integer, Double>();
		 HashMap<Integer, Double> hashtemp3  = new HashMap<Integer, Double>();
		 HashMap<Integer, Double> hashtemp4  = new HashMap<Integer, Double>();
		 ArrayList<HashMap<Integer, Double>> partcorrect = new ArrayList<HashMap<Integer, Double>>();
		
		ArrayList<Integer> part5 = new ArrayList<Integer>();
		ArrayList<Integer> part6 = new ArrayList<Integer>();
		ArrayList<Integer> part7 = new ArrayList<Integer>();
		ArrayList<Integer> part8 = new ArrayList<Integer>();
		
		HashMap<Integer, Integer> duplicate_count5 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count6 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count7 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count8 = new HashMap<Integer, Integer>();
		
		String corrects = null;
		String[] correctArray = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				corrects = rs.getString("CORRECTQUESTIONS");
			}
			
			if(corrects ==null || corrects.length() ==0) {
				return null;
			}
			
			correctArray = corrects.split(",");
			pstmt = conn.prepareStatement(sql2);
			
			for(int i =1; i<correctArray.length; i++) {	
				pstmt.setInt(1, Integer.parseInt(correctArray[i]));
				rs2 = pstmt.executeQuery();
				
				while(rs2.next()) {
					if(rs2.getInt("part")==5) {
						part5.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==6) {
						part6.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==7) {
						part7.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==8) {
						part8.add(rs2.getInt("weekpoint"));			
					}
				}				
			}
			
			duplicate_count5.put(1, 0);
			duplicate_count5.put(2, 0);
			duplicate_count5.put(4, 0);
			duplicate_count5.put(5, 0);
			duplicate_count5.put(7, 0);
			duplicate_count5.put(8, 0);
			
			duplicate_count6.put(1, 0);
			duplicate_count6.put(2, 0);
			duplicate_count6.put(5, 0);
			duplicate_count6.put(6, 0);
			duplicate_count6.put(7, 0);
			duplicate_count6.put(8, 0);
			
			duplicate_count7.put(1, 0);
			duplicate_count7.put(2, 0);
			duplicate_count7.put(3, 0);
			duplicate_count7.put(4, 0);
			duplicate_count7.put(5, 0);
			duplicate_count7.put(6, 0);
			duplicate_count7.put(7, 0);
			duplicate_count7.put(8, 0);
			
			duplicate_count8.put(6, 0);
			duplicate_count8.put(7, 0);
			duplicate_count8.put(8, 0);
	
			for(int i = 0 ; i < part5.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count5.put(part5.get(i), duplicate_count5.get(part5.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part6.size() ; i++){ // ArrayList 만큼 반복 
			    duplicate_count6.put(part6.get(i), duplicate_count6.get(part6.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part7.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count7.put(part7.get(i), duplicate_count7.get(part7.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part8.size() ; i++){ // ArrayList 만큼 반복
			    	duplicate_count8.put(part8.get(i), duplicate_count8.get(part8.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for (Entry<Integer, Integer> entry : duplicate_count5.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			    
			     hashtemp1.put(key, (double)value/part5.size()*100);    		    
			 }
			 partcorrect.add(hashtemp1);	

			for (Entry<Integer, Integer> entry : duplicate_count6.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     
			     hashtemp2.put(key, (double)value/part6.size()*100);    
			 }
			partcorrect.add(hashtemp2);

	
			for (Entry<Integer, Integer> entry : duplicate_count7.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     
			     hashtemp3.put(key, (double)value/part7.size()*100);    
			}
			partcorrect.add(hashtemp3);
		
			for (Entry<Integer, Integer> entry : duplicate_count8.entrySet()) {
			     int key = entry.getKey();
			     int value = entry.getValue();
			     
			     hashtemp4.put(key, (double)value/part8.size()*100);    			    			    
			 }
			partcorrect.add(hashtemp4);
			
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
		return partcorrect;
	}
	
	
	public List<HashMap<Integer, Integer>> partAnalysis4(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sql = "select CORRECTQUESTIONS from SLOVEDQUESTION where email= ?";
		String sql2 = "select part,weekpoint from JPTQUESTION where qidx= ?";
		
		ArrayList<HashMap<Integer, Integer>> partcorrect = new ArrayList<HashMap<Integer, Integer>>();
		
		ArrayList<Integer> part5 = new ArrayList<Integer>();
		ArrayList<Integer> part6 = new ArrayList<Integer>();
		ArrayList<Integer> part7 = new ArrayList<Integer>();
		ArrayList<Integer> part8 = new ArrayList<Integer>();
		
		HashMap<Integer, Integer> duplicate_count5 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count6 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count7 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> duplicate_count8 = new HashMap<Integer, Integer>();
		
		String corrects = null;
		String[] correctArray = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				corrects = rs.getString("CORRECTQUESTIONS");
			}
			
			if(corrects ==null || corrects.length() ==0) {
				return null;
			}
			
			correctArray = corrects.split(",");
			pstmt = conn.prepareStatement(sql2);
			
			for(int i =1; i<correctArray.length; i++) {	
				pstmt.setInt(1, Integer.parseInt(correctArray[i]));
				rs2 = pstmt.executeQuery();
				
				while(rs2.next()) {
					if(rs2.getInt("part")==5) {
						part5.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==6) {
						part6.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==7) {
						part7.add(rs2.getInt("weekpoint"));			
					}
					if(rs2.getInt("part")==8) {
						part8.add(rs2.getInt("weekpoint"));			
					}
				}				
			}
			
			duplicate_count5.put(1, 0);
			duplicate_count5.put(2, 0);
			duplicate_count5.put(4, 0);
			duplicate_count5.put(5, 0);
			duplicate_count5.put(7, 0);
			duplicate_count5.put(8, 0);
			
			duplicate_count6.put(1, 0);
			duplicate_count6.put(2, 0);
			duplicate_count6.put(5, 0);
			duplicate_count6.put(6, 0);
			duplicate_count6.put(7, 0);
			duplicate_count6.put(8, 0);
			
			duplicate_count7.put(1, 0);
			duplicate_count7.put(2, 0);
			duplicate_count7.put(3, 0);
			duplicate_count7.put(4, 0);
			duplicate_count7.put(5, 0);
			duplicate_count7.put(6, 0);
			duplicate_count7.put(7, 0);
			duplicate_count7.put(8, 0);
			
			duplicate_count8.put(6, 0);
			duplicate_count8.put(7, 0);
			duplicate_count8.put(8, 0);
	
			for(int i = 0 ; i < part5.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count5.put(part5.get(i), duplicate_count5.get(part5.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part6.size() ; i++){ // ArrayList 만큼 반복 
			    duplicate_count6.put(part6.get(i), duplicate_count6.get(part6.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part7.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count7.put(part7.get(i), duplicate_count7.get(part7.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
			for(int i = 0 ; i < part8.size() ; i++){ // ArrayList 만큼 반복
			    duplicate_count8.put(part8.get(i), duplicate_count8.get(part8.get(i))  + 1);  // key가 이미 있다면 value에 +1
			}
			
		
			partcorrect.add(duplicate_count5);	
			partcorrect.add(duplicate_count6);
			partcorrect.add(duplicate_count7);
			partcorrect.add(duplicate_count8);
			
			
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
		return partcorrect;
	}
	
	
	
	
	

}
