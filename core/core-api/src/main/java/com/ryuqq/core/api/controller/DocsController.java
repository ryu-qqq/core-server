package com.ryuqq.core.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {

	@GetMapping("/docs")
	public String redirectToDocs() {
		return "redirect:/docs/index.html";
	}

}
