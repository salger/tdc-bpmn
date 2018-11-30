package br.com.tdc.bpmn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdc.bpmn.dto.OrderDTO;
import br.com.tdc.bpmn.misc.Message;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired
	private Message message;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello world";
	}

	@RequestMapping("/message")
	public String message(@RequestParam(name = "key", required = false, defaultValue = "msg1") String key) {
		return message.get(key);
	}

	@RequestMapping("/model")
	public String model(@ModelAttribute OrderDTO dto) {
		return dto.toString();
	}

}
