package com.fiesta.controller.handler;

import com.fiesta.controller.Controller;
import com.fiesta.controller.component.TestController;

public class HandlerMapping {
	private static HandlerMapping handler = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return handler;
	}

	public Controller createController(String command) {
		Controller controller = null;

		if(command.equals("test.do")) { //주석 표시
			controller = new TestController();
		}

		return controller;
	}
}