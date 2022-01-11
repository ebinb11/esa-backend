package com.employee.employeebackend.service;

import com.employee.employeebackend.entity.Employee;

public interface MailService {

	void sendMail(final String to, final String subject, final String text);
	
	void esafNotification(Employee employee);

}
