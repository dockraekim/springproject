package com.netchus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netchus.service.SensorService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class SensorController {
	@Autowired
	private SensorService service;

	public SensorController(SensorService service) {
		super();
		this.service = service;
	}
	
	// 모델에 담아서 경로 지정하기
	@RequestMapping(value = "/sensor", method = RequestMethod.GET)
	public void getSensorData(Model model) {
		model.addAttribute("sensorList", service.getResponse());
	}
	
	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String initMap(Model model) {
		// return String경로 google로 간다.
		return "google";
	}
	
	@RequestMapping(value ="/kakao", method = RequestMethod.GET)
	public void kakaoMap(Model model) {
		// void 라 기본 view 경로가 kakap.jsp로 간다.
	}
}
