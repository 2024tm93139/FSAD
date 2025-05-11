package com.bits.fsd.bean;

import org.springframework.http.HttpStatus;

import com.bits.fsd.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ControllerResponse<T> {
	private T response;
	private HttpStatus status;
	private ResponseStatus responseStatus;
}
