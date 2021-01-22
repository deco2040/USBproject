package jptflog.controller;

import jptflog.service.Action;
import jptflog.service.center.ServiceCenterAction;


public class ServiceActionFactory{
	
private static ServiceActionFactory instance =new ServiceActionFactory();
public static ServiceActionFactory getInstance() {
	return instance;
}
	public Action getAction(String cmd) {
		
		Action action =null;	
		if(cmd.equals("center")) {
			action = new ServiceCenterAction();
		}else if (cmd.equals("testEnd")) {
			
		}
		
		
		
		return action;
	}

}

