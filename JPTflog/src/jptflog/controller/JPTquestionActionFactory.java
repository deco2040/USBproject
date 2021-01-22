package jptflog.controller;

import jptflog.service.Action;
import jptflog.service.question.TestEndAction;
import jptflog.service.question.QuestionInfoViewAction;
import jptflog.service.question.AllpartQuestionListViewAction;
import jptflog.service.question.CorrectQuestionListViewAction;
import jptflog.service.question.IncorrectHighQuestionListViewAction;
import jptflog.service.question.IncorrectQuestionListViewAction;
import jptflog.service.question.MyweekQuestionAction;
import jptflog.service.question.Part5QuestionListViewAction;
import jptflog.service.question.Part6QuestionListViewAction;
import jptflog.service.question.Part7QuestionListViewAction;
import jptflog.service.question.Part8QuestionListViewAction;

public class JPTquestionActionFactory{
	
private static JPTquestionActionFactory instance =new JPTquestionActionFactory();
public static JPTquestionActionFactory getInstance() {
	return instance;
}
	public Action getAction(String cmd) {
		
		Action action =null;	
		if(cmd.equals("allpart")) {
			action = new AllpartQuestionListViewAction();
		}else if (cmd.equals("testEnd")) {
			action = new TestEndAction();
		}else if (cmd.equals("questionInfo")) {
			action = new QuestionInfoViewAction();
		}else if (cmd.equals("correct")) {
			action = new CorrectQuestionListViewAction();
		}else if (cmd.equals("incorrect")) {
			action = new IncorrectQuestionListViewAction();
		}else if (cmd.equals("part5")) {
			action = new Part5QuestionListViewAction();
		}else if (cmd.equals("part6")) {
			action = new Part6QuestionListViewAction();
		}else if (cmd.equals("part7")) {
			action = new Part7QuestionListViewAction();
		}else if (cmd.equals("part8")) {
			action = new Part8QuestionListViewAction();
		}else if (cmd.equals("incorrecthigh")) {
			action = new IncorrectHighQuestionListViewAction();
		}else if (cmd.equals("myweek")) {
			action = new MyweekQuestionAction();
		}
		
		
		
		return action;
	}

}

