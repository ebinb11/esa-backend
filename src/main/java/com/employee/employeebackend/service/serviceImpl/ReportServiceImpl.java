package com.employee.employeebackend.service.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.employee.employeebackend.entity.User;
import com.employee.employeebackend.repository.UserRepository;
import com.employee.employeebackend.service.ReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	UserRepository userRepository;

	@Override
	public String exportUserReport(String reportFormat) throws FileNotFoundException, JRException {
		List<User> userList = userRepository.findAll();
		String path = "C:\\Users\\Kran-PC-1511\\Desktop";

		// Load file and compile it
		File file = ResourceUtils.getFile("classpath:user.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userList);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Ebin B");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users.html");
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users.pdf");
		}
		return "Report Generated Successfully in the path" + path;
	}
}
