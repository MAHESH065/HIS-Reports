package com.jsr.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jsr.entity.Plan;

public class ExcelGenerator {
	public static ByteArrayInputStream reportsInExcel(List<Plan> plans) throws IOException
	{
		String[] columns = {"Plan_Id","Plan_Name","Plan_Status","Start_Date","End_Date","Benefit_Amount"};
		try(
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			){
			Sheet sheet = workbook.createSheet("Excel-Report");
			
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			
			//Row for Header-->
			Row headerRow = sheet.createRow(0);
			
			//Header
			for(int col=0; col<columns.length; col++)
			{
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			
			int rowIdx =1;
			for(Plan p: plans)
			{
				Row row = sheet.createRow(rowIdx++);
				
				row.createCell(0).setCellValue(p.getPlan_id());
				row.createCell(1).setCellValue(p.getPlan_name());
				row.createCell(2).setCellValue(p.getPlan_status());
				row.createCell(3).setCellValue(p.getPlan_sdate());
				row.createCell(4).setCellValue(p.getPlan_edate());
				row.createCell(5).setCellValue(p.getBenefit_amt());

			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}
