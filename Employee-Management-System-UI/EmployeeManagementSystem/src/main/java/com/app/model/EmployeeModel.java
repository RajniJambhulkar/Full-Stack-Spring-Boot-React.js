package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeModel {
	
	private long id;

	private String firstname;

	private String lastname;

	private String emailId;
}
