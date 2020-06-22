package com.fiesta.controller.handler;

import com.fiesta.controller.Controller;

public class HandlerMapping {
	private static HandlerMapping handler = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return handler;
	}

	public Controller createController(String command) {
		Controller controller = null;

		// 작업 영역

		return controller;
	}
}