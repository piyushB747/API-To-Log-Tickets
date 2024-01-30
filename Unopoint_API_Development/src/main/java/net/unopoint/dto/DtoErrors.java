package net.unopoint.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoErrors {

	private String message;
	
	private String messageDescription;
	
	private String httpStatus;
	
}
