package com.employee.employeebackend.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {

	public String exportUserReport(String reportFormat) throws FileNotFoundException, JRException;
}
