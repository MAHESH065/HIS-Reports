package com.jsr.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jsr.entity.Plan;

public class PdfGenerator {
	
	private static Logger logger = (Logger)LoggerFactory.getLogger(PdfGenerator.class);
	
	public static ByteArrayInputStream reportsInPdf(List<Plan> plans) throws IOException
	{
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			
			//Add Text to PDF file->
			Font font = FontFactory.getFont(FontFactory.COURIER_BOLD,18, BaseColor.BLUE);
			Paragraph para = new Paragraph("Plan Details Record", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(6);
			
			//Add PDF Table Header-->
			Stream.of("Plan_Id","Plan_Name","Status","Start_Date","End_Date","Amount")
						.forEach(headerTitle -> {
							PdfPCell header = new PdfPCell();
							Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
							header.setBackgroundColor(BaseColor.ORANGE);
							header.setVerticalAlignment(Element.ALIGN_MIDDLE);
							header.setHorizontalAlignment(Element.ALIGN_CENTER);
							header.setBorder(2);
							header.setPhrase(new Phrase(headerTitle, headFont));
							table.addCell(header);
						});
			
			for(Plan p: plans)
			{
				String id = String.valueOf(p.getPlan_id());
				PdfPCell idCell = new PdfPCell(new Phrase(id));
				idCell.setPadding(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);
				
				PdfPCell nameCell = new PdfPCell(new Phrase(p.getPlan_name()));
				nameCell.setPaddingLeft(4);
				nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//Set Border Color-->
				nameCell.setBorderColor(BaseColor.RED);
				table.addCell(nameCell);
				
				PdfPCell statusCell = new PdfPCell(new Phrase(p.getPlan_status()));
				statusCell.setPaddingLeft(4);
				statusCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//Set Border Color-->
				statusCell.setBorderColor(BaseColor.RED);
				table.addCell(statusCell);
				
				PdfPCell sdateCell = new PdfPCell(new Phrase(p.getPlan_sdate()));
				sdateCell.setPaddingLeft(4);
				sdateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sdateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//Set Border Color-->
				sdateCell.setBorderColor(BaseColor.RED);
				table.addCell(sdateCell);
				
				PdfPCell edateCell = new PdfPCell(new Phrase(p.getPlan_edate()));
				edateCell.setPaddingLeft(4);
				edateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				edateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//Set Border Color-->
				edateCell.setBorderColor(BaseColor.RED);
				table.addCell(edateCell);
				
				String amount = String.valueOf(p.getBenefit_amt());
				PdfPCell amountCell = new PdfPCell(new Phrase(amount));
				amountCell.setPadding(4);
				amountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				amountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(amountCell);
				
			}
			document.add(table);
			document.close();
		}
		catch(DocumentException e) {
			logger.error(e.toString());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
