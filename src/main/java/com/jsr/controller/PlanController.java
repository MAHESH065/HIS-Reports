package com.jsr.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsr.entity.Plan;
import com.jsr.report.ExcelGenerator;
import com.jsr.report.PdfGenerator;
import com.jsr.service.PlanService;

@Controller
public class PlanController {
	
	@Autowired
	private PlanService service;
	
	@RequestMapping("/")
	public String displayHome() {
		return "home";
	}
	
	@RequestMapping("/viewPlans")
	public String viewPlanDetails(Model model) {
	    List<Plan> details = service.getDetails();
	    model.addAttribute("list", details);
	     
	    return "viewDetails";
	}
	
	//Export Excel File
	
	@RequestMapping("download/excelreport")
	public ResponseEntity<InputStreamResource> excelPlanReport() throws IOException
	{
		List<Plan> list = service.getDetails();
		
		ByteArrayInputStream in = ExcelGenerator.reportsInExcel(list);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=ExcelPlan.xlsx");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(in));
	}
	
	//Export Pdf File
	
	@RequestMapping(value="/download/pdfreport")
	public ResponseEntity<InputStreamResource> pdfPlanReport()throws IOException
	{
		List<Plan> list = service.getDetails();
		
		ByteArrayInputStream bis = PdfGenerator.reportsInPdf(list);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; file = PdfPlan.pdf");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
				
	}
}
