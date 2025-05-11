package com.bits.fsd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.bits.fsd.bean.ControllerResponse;
import com.bits.fsd.enums.ResponseStatus;

@Controller
public class AbstractFSDController {
	protected <T> ControllerResponse<T> generateAndReturn(T response, HttpStatus status,
			ResponseStatus responseStatus) {
		return new ControllerResponse<T>(response, status, responseStatus);
	}
}
