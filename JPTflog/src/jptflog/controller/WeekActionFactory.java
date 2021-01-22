package jptflog.controller;

import jptflog.service.Action;
import jptflog.service.week.WeekMoreAction;

public class WeekActionFactory{
private static WeekActionFactory instance =new WeekActionFactory();
public static WeekActionFactory getInstance() {
	return instance;
}
	public Action getAction(String cmd) {
		
		Action action =null;	
		if(cmd.equals("weekmore")) {
			action = new WeekMoreAction();
		}
		
		
		return action;
	}

}

