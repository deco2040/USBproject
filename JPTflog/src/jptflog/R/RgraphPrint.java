package jptflog.R;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

import jptflog.model.question.ChoiceTotDAO;
import jptflog.model.question.ChoiceTotVO;
import jptflog.model.question.QuestionDAO;
import jptflog.model.user.UserGoalVO;
import jptflog.model.week.ExperdataVO;
import jptflog.model.week.MemberWeekDAO;
import jptflog.model.week.MemberWeekVO;

public class RgraphPrint {

	private RgraphPrint() {
	}

	private static RgraphPrint instance = new RgraphPrint();

	public static RgraphPrint getInstance() {
		return instance;
	}

	public RConnection conn = null;
	public REXP x = null;
	public String retStr = "";

	public byte[] radarchart(HashMap<Integer, Double> correctParsent) throws REngineException, REXPMismatchException {

		try {
			conn = new RConnection();
			
			String grammer = "0";
			String idiom = "0";
			String info = "0";
			String kanji = "0";
			String proverb = "0";
			String reading = "0";
			String sentence = "0";
			String voca =  "0";
			
			Double max = 0.0;
			for (Entry<Integer, Double> entry : correctParsent.entrySet()) {
			     int key = entry.getKey();
			     Double value = entry.getValue();
			          
			     if(key ==1) {
			    	 if(value > max) {max = value;}
			    	 voca = Double.toString(value);
			     }
			     if(key ==2) {
			    	 if(value > max) {max = value;}
			    	 idiom = Double.toString(value);
			     }
			     if(key ==3) {
			    	 if(value > max) {max = value;}
			    	 proverb = Double.toString(value);
			     }
			     if(key ==4) {
			    	 if(value > max) {max = value;}
			    	 kanji = Double.toString(value);
			     }
			     if(key ==5) {
			    	 if(value > max) {max = value;}
			    	 grammer = Double.toString(value);
			     }
			     if(key ==6) {
			    	 if(value > max) {max = value;}
			    	 reading = Double.toString(value);
			     }
			     if(key ==7) {
			    	 if(value > max) {max = value;}
			    	 info = Double.toString(value);
			     }
			     if(key ==8) {
			    	 if(value > max) {max = value;}
			    	 sentence = Double.toString(value);
			     }
			     
			 }
			
			String caxis = Integer.toString((int)(max/5));
			String maxString = Double.toString(max+5);
					
			conn.parseAndEval("library(fmsb)");
			x = conn.parseAndEval("try(jpeg('test.jpg',quality=90))");
			conn.parseAndEval("data= data.frame(matrix( c(" + kanji + "," + voca + "," + info + "," + idiom + "," + proverb + ","
															+ grammer + "," + reading + "," + sentence +") , ncol=8))");
			conn.parseAndEval("colnames(data)=c(\"한자력\" , \"어휘력\" , \"정보력\" , \"숙어력\" , \"속담력\", \"문법력\" , \"독해력\" , \"문장력\")");
			conn.parseAndEval("data=rbind(rep(" + maxString + ") , rep(0,8) , data)");

			conn.parseAndEval("radarchart( data  , axistype=1 , "
					+ " pcol='black' , pfcol=rgb(0.2,0.5,0.6,0.7) , plwd=1 , plty=1,"
					+ " cglcol=\"grey\", cglty=1, axislabcol=\"grey\", caxislabels=seq(" + caxis + "," + maxString + "," + caxis  +"), cglwd=1,"
					+ "vlcex=1 )");

			conn.parseAndEval("graphics.off();");
			x = conn.parseAndEval("r=readBin('test.jpg','raw',1024*1024); unlink('test.jpg'); r");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asBytes();
	}
	
	public byte[] questionpiechart(int qidx) throws REngineException, REXPMismatchException {

		try {
			conn = new RConnection();
			
			ChoiceTotVO JPTtotvo =new ChoiceTotVO();
			ChoiceTotDAO dao = ChoiceTotDAO.getInstance();
			JPTtotvo = dao.JPTtot(qidx);
			
			String totA= Integer.toString(JPTtotvo.getAtot());
			String totB= Integer.toString(JPTtotvo.getBtot());
			String totC= Integer.toString(JPTtotvo.getCtot());
			String totD= Integer.toString(JPTtotvo.getDtot());
			
			String choices = "'a번','b번','c번','d번'";
			String choicetot =  totA + "," + totB + "," + totC + "," + totD;
			
			
			if(JPTtotvo.getAtot()==0) {
				choices = choices.replace("'a번',", "");
				choicetot = choicetot.replace(totA + ",", "");
			}
			if(JPTtotvo.getBtot()==0) {
				choices = choices.replace("'b번',", "");
				choicetot = choicetot.replace(totB + ",", "");
			}
			if(JPTtotvo.getCtot()==0) {
				choices = choices.replace("'c번',", "");
				choicetot = choicetot.replace(totC + ",", "");
			}
			if(JPTtotvo.getDtot()==0) {
				choices = choices.replace("'d번'", "");
				choicetot = choicetot.replace("," + totD, "");
			}
			
			
			if((choices == null || choices.length() ==0) || (choicetot == null || choicetot.length() ==0) ) {
				byte[] none = {-99};
				return none;
			}
			
			if(choices.endsWith(",")) {
				choices = choices.substring(0,choices.length()-1);
			}

			
			x = conn.parseAndEval("try(jpeg('test.jpg',quality=90))");
			conn.parseAndEval("data= data.frame(\"응답통계\" = c(" + choices + "), \"선택한수\"= c(" + choicetot + "))");
			conn.parseAndEval("data$'상대도수'<-prop.table(data$\"선택한수\")");
			
			conn.parseAndEval("pie(data$'상대도수', labels=data$\"응답통계\")");
			conn.parseAndEval("pie(data$'상대도수', labels=paste(data$\"응답통계\", round(data$상대도수*100,1),\"%\"))");

			conn.parseAndEval("graphics.off();");
			x = conn.parseAndEval("r=readBin('test.jpg','raw',1024*1024); unlink('test.jpg'); r");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asBytes();
	}
	
	
	public byte[] Partgraph(List<HashMap<Integer, Double>> Parsent, String ylab) throws REngineException, REXPMismatchException {

		try {
			conn = new RConnection();
			
			HashMap<Integer, Double> part5 = Parsent.get(0);
			HashMap<Integer, Double> part6 = Parsent.get(1);
			HashMap<Integer, Double> part7 = Parsent.get(2);
			HashMap<Integer, Double> part8 = Parsent.get(3);
		
			String part5Week = part5.keySet().toString().replace("1", "'어휘력'").replace("2", "'숙어력'").replace("3", "'속담력'").replace("4", "'한자력'")
			.replace("5", "'문법력'").replace("6", "'독해력'").replace("7", "'정보력'").replace("8", "'문장력'").replace("[", "").replace("]", "");
			
			String part6Week = part6.keySet().toString().replace("1", "'어휘력'").replace("2", "'숙어력'").replace("3", "'속담력'").replace("4", "'한자력'")
					.replace("5", "'문법력'").replace("6", "'독해력'").replace("7", "'정보력'").replace("8", "'문장력'").replace("[", "").replace("]", "");
			
			String part7Week = part7.keySet().toString().replace("1", "'어휘력'").replace("2", "'숙어력'").replace("3", "'속담력'").replace("4", "'한자력'")
					.replace("5", "'문법력'").replace("6", "'독해력'").replace("7", "'정보력'").replace("8", "'문장력'").replace("[", "").replace("]", "");
			
			String part8Week = part8.keySet().toString().replace("1", "'어휘력'").replace("2", "'숙어력'").replace("3", "'속담력'").replace("4", "'한자력'")
					.replace("5", "'문법력'").replace("6", "'독해력'").replace("7", "'정보력'").replace("8", "'문장력'").replace("[", "").replace("]", "");
			
	
			
			String part5pasent = part5.values().toString().replace("[", "").replace("]", "");
			String part6pasent = part6.values().toString().replace("[", "").replace("]", "");
			String part7pasent = part7.values().toString().replace("[", "").replace("]", "");
			String part8pasent = part8.values().toString().replace("[", "").replace("]", "");
			
			
			x = conn.parseAndEval("try(jpeg('test.jpg',quality=90))");
			
			conn.parseAndEval("library('patchwork')");
			conn.parseAndEval("library('Rcpp')");
			conn.parseAndEval("library('ggplot2')");
			
			conn.parseAndEval("weekpart5 <- c(" + part5Week + ")");
			conn.parseAndEval("weekparsent5 <- c(" + part5pasent + ")");
			conn.parseAndEval("histogram5 <- data.frame(weekpart5,weekparsent5)");
			conn.parseAndEval("ggplot(data=histogram5,aes(x=weekpart5, y=weekparsent5, fill=weekpart5)) +"
							+ "xlab('파트5 취약점') + ylab('" + ylab + "') +"
							+ "scale_x_discrete(breaks = NULL) +"
							+ "labs(fill = \"취약점 정보\") +"
							+ "geom_bar(stat='identity') -> p5");

			conn.parseAndEval("weekpart6 <- c(" + part6Week + ")");
			conn.parseAndEval("weekparsent6 <- c(" + part6pasent + ")");
			conn.parseAndEval("histogram6 <- data.frame(weekpart6,weekparsent6)");
			conn.parseAndEval("ggplot(data=histogram6,aes(x=weekpart6, y=weekparsent6, fill=weekpart6)) +"
							+ "xlab('파트6 취약점') + ylab('" + ylab + "') +"
							+ "labs(fill = \"취약점 정보\") +"
							+ "scale_x_discrete(breaks = NULL) +"
							+ "geom_bar(stat='identity') -> p6");
			
			conn.parseAndEval("weekpart7 <- c(" + part7Week + ")");
			conn.parseAndEval("weekparsent7 <- c(" + part7pasent + ")");
			conn.parseAndEval("histogram7 <- data.frame(weekpart7,weekparsent7)");
			conn.parseAndEval("ggplot(data=histogram7,aes(x=weekpart7, y=weekparsent7, fill=weekpart7)) +"
							+ "xlab('파트7 취약점') + ylab('" + ylab + "') +"
							+ "labs(fill = \"취약점 정보\") +"
							+ "scale_x_discrete(breaks = NULL) +"
							+ "geom_bar(stat='identity') -> p7");
			
			conn.parseAndEval("weekpart8 <- c(" + part8Week + ")");
			conn.parseAndEval("weekparsent8 <- c(" + part8pasent + ")");
			conn.parseAndEval("histogram8 <- data.frame(weekpart8,weekparsent8)");
			conn.parseAndEval("ggplot(data=histogram8,aes(x=weekpart8, y=weekparsent8, fill=weekpart8)) +"
							+ "xlab('파트8 취약점') + ylab('" + ylab + "') +"
							+ "labs(fill = \"취약점 정보\") +"
							+ "scale_x_discrete(breaks = NULL) +"
							+ "geom_bar(stat='identity') -> p8");


			
			conn.parseAndEval("print((p5+p6)/(p7+p8))");
	
			conn.parseAndEval("graphics.off();");
			x = conn.parseAndEval("r=readBin('test.jpg','raw',2048*2048); unlink('test.jpg'); r");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asBytes();
	}
	
	
	public double expectGrade(List<Integer> testresult) throws REngineException, REXPMismatchException {
		
	
		try {
			conn = new RConnection();
			
			String result ="";
			
			MemberWeekDAO dao = MemberWeekDAO.getInstnce();
			List<ExperdataVO> data = new ArrayList<ExperdataVO>(); 
			data = dao.getExperdata();
			
			List<Integer> resultList = new ArrayList<Integer>(); 
			resultList = dao.resultData(testresult);
	
			result =  resultList.toString().replace("[", "").replace("]", "").replace(" ", "");
			String expectgrades = "";
					
			conn.parseAndEval("logiGrade <- matrix(ncol = 15,nrow= " + Integer.toString(data.size()) + ");");
			
			for(int i=1; i<=data.size(); i++) {		
				for(int j=1; j<=15; j++) {		
					if(j==1) {
						conn.parseAndEval("logiGrade["+ i + ",1] <- " + data.get(i-1).getQ1() );
					}
					if(j==2) {
						conn.parseAndEval("logiGrade[" + i + ",2] <- " + data.get(i-1).getQ2() );
					}
					if(j==3) {
						conn.parseAndEval("logiGrade[" + i + ",3] <- " + data.get(i-1).getQ3() );
					}
					if(j==4) {
						conn.parseAndEval("logiGrade[" + i + ",4] <- " + data.get(i-1).getQ4() );
					}
					if(j==5) {
						conn.parseAndEval("logiGrade[" + i + ",5] <- " + data.get(i-1).getQ5() );
					}
					if(j==6) {
						conn.parseAndEval("logiGrade[" + i + ",6] <- " + data.get(i-1).getQ6() );
					}
					if(j==7) {
						conn.parseAndEval("logiGrade[" + i + ",7] <- " + data.get(i-1).getQ7() );
					}
					if(j==8) {
						conn.parseAndEval("logiGrade[" + i + ",8] <- " + data.get(i-1).getQ8() );
					}
					if(j==9) {
						conn.parseAndEval("logiGrade[" + i + ",9] <- " + data.get(i-1).getQ9() );
					}
					if(j==10) {
						conn.parseAndEval("logiGrade[" + i + ",10] <- " + data.get(i-1).getQ10() );
					}
					if(j==11) {
						conn.parseAndEval("logiGrade[" + i + ",11] <- " + data.get(i-1).getQ11() );
					}
					if(j==12) {
						conn.parseAndEval("logiGrade[" + i + ",12] <- " + data.get(i-1).getQ12() );
					}
					if(j==13) {
						conn.parseAndEval("logiGrade[" + i + ",13] <- " + data.get(i-1).getQ13() );
					}
					if(j==14) {
						conn.parseAndEval("logiGrade[" + i + ",14] <- " + data.get(i-1).getQ14() );
					}
					if(j==15) {
						conn.parseAndEval("logiGrade[" + i + ",15] <- " + data.get(i-1).getQ15() );
					}
					
				}
			}
			
			for(int i=0; i<data.size(); i++) {
				expectgrades = expectgrades + Integer.toString(data.get(i).getExpectgrade()) + ",";
			}
			
			conn.parseAndEval("exgrade <- c(" + expectgrades.substring(0,expectgrades.length()-1) + ")");	
			conn.parseAndEval("expectdata <-data.frame(cbind(logiGrade,exgrade))");
			conn.parseAndEval("mod.grade <- glm(exgrade ~., data=expectdata)");
			
			conn.parseAndEval("exdata <- c(" + result + ")");
			
			conn.parseAndEval("exgrade <- data.frame(rbind(exdata));");
			conn.parseAndEval("names(exgrade) <- c(\"V1\",\"V2\",\"V3\",\"V4\",\"V5\",\"V6\",\"V7\",\"V8\",\"V9\",\"V10\",\"V11\",\"V12\",\"V13\",\"V14\",\"V15\")");
			conn.parseAndEval("pred <- predict(mod.grade,exgrade);");
						
			x = conn.parseAndEval("pred");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asDouble();
	}
	
	
	public byte[] totPart(List<HashMap<Integer, Integer>> Parsent, String ylab) throws REngineException, REXPMismatchException {

		try {
			conn = new RConnection();
		
			HashMap<Integer, Integer> part5 = Parsent.get(0);
			HashMap<Integer, Integer> part6 = Parsent.get(1);
			HashMap<Integer, Integer> part7 = Parsent.get(2);
			HashMap<Integer, Integer> part8 = Parsent.get(3);
			
			String part5pasent = part5.values().toString().replace("[", "").replace("]", "");
			String part6pasent = part6.values().toString().replace("[", "").replace("]", "");
			String part7pasent = part7.values().toString().replace("[", "").replace("]", "");
			String part8pasent = part8.values().toString().replace("[", "").replace("]", "");
			
			
			x = conn.parseAndEval("try(jpeg('test.jpg',quality=90))");
			
			conn.parseAndEval("library('Rcpp')");
			conn.parseAndEval("library('ggplot2')");
			
			conn.parseAndEval("part <- c(5,5,5,5,5,5,6,6,6,6,6,6,7,7,7,7,7,7,7,7,8,8,8)");
			conn.parseAndEval("weekpoint <- c(\"어휘력\",\"숙어력\",\"한자력\",\"문법력\",\"정보력\",\"문장력\","
											+ "\"어휘력\",\"숙어력\",\"문법력\",\"독해력\",\"정보력\",\"문장력\","
											+ "\"어휘력\",\"숙어력\",\"속담력\",\"한자력\",\"문법력\",\"독해력\",\"정보력\",\"문장력\","
											+ "\"독해력\",\"정보력\",\"문장력\")");
			conn.parseAndEval("week <- c(" + part5pasent + "," + part6pasent + "," + part7pasent + "," + part8pasent + ")");
			conn.parseAndEval("data <- data.frame(week,part,weekpoint)");
			conn.parseAndEval("print(ggplot(data, aes(x=part, y= week, fill=as.factor(weekpoint))) +"
							+ "scale_color_discrete(name=\"취약점 정보\") +"
							+ "geom_bar(stat=\"identity\", position=\"stack\") +"
							+ "xlab('파트별 취약점') + ylab('" + ylab + "') +"
							+ "labs(fill = \"취약점 정보\") +"
							+ "coord_flip())");

			conn.parseAndEval("graphics.off();");
			x = conn.parseAndEval("r=readBin('test.jpg','raw',2048*2048); unlink('test.jpg'); r");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asBytes();
	}
	
	public byte[] pie(MemberWeekVO vo) throws REngineException, REXPMismatchException {

		try {
			conn = new RConnection();
			
			String grammer = Integer.toString(vo.getGrammer());
			String idiom =  Integer.toString(vo.getIdiom());
			String info =  Integer.toString(vo.getInfo());
			String kanji =  Integer.toString(vo.getKanji());
			String proverb =  Integer.toString(vo.getProverb());
			String reading =  Integer.toString(vo.getReading());
			String sentence =  Integer.toString(vo.getSentence());
			String voca =  Integer.toString(vo.getVoca());
			
			x = conn.parseAndEval("try(jpeg('test.jpg',quality=90))");
			conn.parseAndEval("data= data.frame(\"취약점\" = c(\"한자력\" , \"어휘력\" , \"정보력\" , \"숙어력\" , \"속담력\", \"문법력\" , \"독해력\" , \"문장력\"),"
												+ "\"정도\"= c(" + kanji + "," + voca + "," + info + "," + idiom + "," + proverb + ","
												+ grammer + "," + reading + "," + sentence +"))");
			conn.parseAndEval("data$상대도수<-prop.table(data$정도)");
			conn.parseAndEval("pie(data$상대도수, labels=data$취약점)");

			conn.parseAndEval("pie(data$상대도수, labels=paste(data$취약점, round(data$상대도수*100,1),\"%\"))");

			conn.parseAndEval("graphics.off();");
			x = conn.parseAndEval("r=readBin('test.jpg','raw',1024*1024); unlink('test.jpg'); r");

		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asBytes();
	}
	
	
	public byte[] goalgraph(UserGoalVO vo) throws REngineException, REXPMismatchException {

		try {
			conn = new RConnection();
	
			String goal = Integer.toString(vo.getGoal());
			
			String day = null;
			String grade = null;

			
				if((vo.getDay1() == null || vo.getDay1().length() ==0 )&& (vo.getDay2() == null ||vo.getDay2().length() ==0)) {
					day = "";
					grade = "";
				}
				if((vo.getDay2() == null || vo.getDay2().length() ==0 )&& (vo.getDay1() != null &&vo.getDay1().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1());
				}
				if((vo.getDay3() == null || vo.getDay3().length() ==0 )&& (vo.getDay2() != null &&vo.getDay2().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2());
				}
				if((vo.getDay4() == null || vo.getDay4().length() ==0 )&& (vo.getDay3() != null &&vo.getDay3().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3());
				}
				if((vo.getDay5() == null || vo.getDay5().length() ==0 )&& (vo.getDay4() != null &&vo.getDay4().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4());
				}
				if((vo.getDay6() == null || vo.getDay6().length() ==0 )&& (vo.getDay5() != null &&vo.getDay5().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10)
							+ "," + vo.getDay5().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4())
							+ "," + Integer.toString(vo.getGrade5());
				}
				if((vo.getDay7() == null || vo.getDay7().length() ==0 )&& (vo.getDay6() != null &&vo.getDay6().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10)
							+ "," + vo.getDay5().substring(0,10) + "\"),as.Date(\"" + vo.getDay6().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4())
					+ "," + Integer.toString(vo.getGrade5()) + "," + Integer.toString(vo.getGrade6());
				}
				if((vo.getDay8() == null || vo.getDay8().length() ==0 )&& (vo.getDay7() != null &&vo.getDay7().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10)
							+ "," + vo.getDay5().substring(0,10) + "\"),as.Date(\"" + vo.getDay6().substring(0,10) + "\"),as.Date(\"" + vo.getDay7().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4())
					+ "," + Integer.toString(vo.getGrade5()) + "," + Integer.toString(vo.getGrade6()) + "," + Integer.toString(vo.getGrade7());
				}
				if((vo.getDay9() == null || vo.getDay9().length() ==0 )&& (vo.getDay8() != null &&vo.getDay8().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10)
							+ "," + vo.getDay5().substring(0,10) + "\"),as.Date(\"" + vo.getDay6().substring(0,10) + "\"),as.Date(\"" + vo.getDay7().substring(0,10) + "\"),as.Date(\"" + vo.getDay8().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4())
					+ "," + Integer.toString(vo.getGrade5()) + "," + Integer.toString(vo.getGrade6()) + "," + Integer.toString(vo.getGrade7()) + "," + Integer.toString(vo.getGrade8());
				}
				if((vo.getDay10() == null || vo.getDay10().length() ==0 )&& (vo.getDay9() != null &&vo.getDay9().length() !=0)) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10)
							+ "," + vo.getDay5().substring(0,10) + "\"),as.Date(\"" + vo.getDay6().substring(0,10) + "\"),as.Date(\"" + vo.getDay7().substring(0,10) + "\"),as.Date(\"" + vo.getDay8().substring(0,10) + "\"),as.Date(\"" 
							+ vo.getDay9().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4())
					+ "," + Integer.toString(vo.getGrade5()) + "," + Integer.toString(vo.getGrade6()) + "," + Integer.toString(vo.getGrade7()) + "," + Integer.toString(vo.getGrade8())
					+ "," + Integer.toString(vo.getGrade9());
				}
				if(vo.getDay10() != null && vo.getDay10().length() !=0) {
					day = "as.Date(\"" + vo.getDay1().substring(0,10) + "\"),as.Date(\"" + vo.getDay2().substring(0,10) + "\"),as.Date(\"" + vo.getDay3().substring(0,10) + "\"),as.Date(\"" + vo.getDay4().substring(0,10)
							+ "," + vo.getDay5().substring(0,10) + "\"),as.Date(\"" + vo.getDay6().substring(0,10) + "\"),as.Date(\"" + vo.getDay7().substring(0,10) + "\"),as.Date(\"" + vo.getDay8().substring(0,10) + "\"),as.Date(\"" 
							+ vo.getDay9().substring(0,10) + "\"),as.Date(\"" + vo.getDay10().substring(0,10) + "\")";
					grade = Integer.toString(vo.getGrade1()) + "," + Integer.toString(vo.getGrade2()) + "," + Integer.toString(vo.getGrade3()) + "," + Integer.toString(vo.getGrade4())
					+ "," + Integer.toString(vo.getGrade5()) + "," + Integer.toString(vo.getGrade6()) + "," + Integer.toString(vo.getGrade7()) + "," + Integer.toString(vo.getGrade8())
					+ "," + Integer.toString(vo.getGrade9()) + "," + Integer.toString(vo.getGrade10());
				}
				
			x = conn.parseAndEval("try(jpeg('test.jpg',quality=120))");
			
			conn.parseAndEval("library('Rcpp')");
			conn.parseAndEval("library('ggplot2')");
			conn.parseAndEval("library('ggrepel')");
				
			conn.parseAndEval("day <- c(" + day + ")");
			conn.parseAndEval("grade <- c(" + grade + ")");
			conn.parseAndEval("goals <- data.frame(day,grade)");

			conn.parseAndEval("print(ggplot(data=goals) +"
							+ "geom_line(aes(x=day, y=grade),color=\"blue\",linetype = 1,size=1) + "
							+ "geom_point(aes(x=day, y=grade),size=4,color=\"red\")+"
							+ "xlab(\"날짜\") + "
							+ "ylab(\"점수\") +"
							+ "theme_minimal() +"
							+ "geom_label_repel(aes(label = grade, x=day, y=grade), size = 4)+"
							+ "geom_hline(yintercept=" + goal +", size =1)+"
							+ "annotate(\"label\", x=as.Date(\"" + vo.getDay1().substring(0,10) + "\"), y=" + goal +", color='darkgrey', label= \"목표점수\", size=3)+"
							+ "scale_y_continuous(breaks = sort(c(seq(min(goals$grade), max(goals$grade), length.out=5), " + goal +"))))");
			

			conn.parseAndEval("graphics.off();");	
			x = conn.parseAndEval("r=readBin('test.jpg','raw',1024*1024); unlink('test.jpg'); r");


		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return x.asBytes();
	}
	
	
	
	
	
	

}