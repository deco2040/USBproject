package jptflog.controller;

import jptflog.service.Action;
import jptflog.service.user.ExpectationGradeAction;
import jptflog.service.user.ExpectationResultAction;
import jptflog.service.user.HomeAction;
import jptflog.service.user.LoginpageAction;
import jptflog.service.user.PwsearchAction;
import jptflog.service.user.UserLoginAction;
import jptflog.service.user.UserMypageAction;
import jptflog.service.user.UserRegisterAction;
import jptflog.service.user.UserRegisterSendAction;

public class UserActionFactory {
	
		private static UserActionFactory instance =new UserActionFactory();
		public static UserActionFactory getInstance() {
			return instance;
		}
			public Action getAction(String cmd) {
				
				Action action =null;	
				if(cmd.equals("login")) {
					action = new LoginpageAction();
				}if(cmd.equals("logincheck")) {
					action = new UserLoginAction();
				}if(cmd.equals("home")) {
					action = new HomeAction();
				}if(cmd.equals("register")) {
					action = new UserRegisterAction();
				}if(cmd.equals("registersend")) {
					action = new UserRegisterSendAction();
				}if(cmd.equals("pwsearch")) {
					action = new PwsearchAction();
				}if(cmd.equals("mypage")) {
					action = new UserMypageAction();
				}if(cmd.equals("expectation")) {
					action = new ExpectationGradeAction();
				}if(cmd.equals("testend")) {
					action = new ExpectationResultAction();
				}
				
				
				return action;
			}
}
