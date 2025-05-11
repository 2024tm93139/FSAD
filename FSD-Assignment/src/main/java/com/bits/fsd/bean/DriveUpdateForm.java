package com.bits.fsd.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriveUpdateForm {
	@NotNull
	private Integer id;
	@Nullable
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String name;
}
