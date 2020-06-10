package com.ebs.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;

public class PdfUtils {
	public final static String PROJECT_NAME = "Electricity Billing Sytem";

	public static byte[] generateBillPdf(Bill bill, CustomerDetail customer) throws IOException {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
//		PDFont fontPlain = PDType1Font.HELVETICA;
//		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
//		PDFont fontMono = PDType1Font.COURIER;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// Create a document and add a page to it
		// rect can be used to get the page width and height
		document.addPage(page);

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream cos = new PDPageContentStream(document, page);

		// Dummy Table
		float margin = 20;
		// starting y position is whole page height subtracted by top and bottom margin
		float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
		// we want table across whole page width (subtracted by left and right margin
		// ofcourse)
		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

		boolean drawContent = true;
		float bottomMargin = 30;
		// y position is your coordinate of top left corner of the table
		float yPosition = yStartNewPage;

		BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page,
				true, drawContent);

		// the parameter is the row height
		Row<PDPage> headerRow = table.createRow(50);
		// the first parameter is the cell width
		Cell<PDPage> cell = headerRow.createCell(100, PROJECT_NAME);
		cell.setFont(fontBold);
		cell.setFontSize(20);
		// vertical alignment
		cell.setValign(VerticalAlignment.MIDDLE);
		cell.setAlign(HorizontalAlignment.CENTER);
		table.addHeaderRow(headerRow);
//Bill No.
		Row<PDPage> row = table.createRow(20);
		cell = row.createCell(50, "Customer Name");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		String customerFullName = String.format("%s %s", customer.getFirstName(), customer.getLastName());
		cell = row.createCell(50, customerFullName);
		cell.setFontSize(15);
		row = table.createRow(20);
		cell = row.createCell(50, "Address");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		String customerAddress = customer.getAddress() != null ? customer.getAddress().toString() : "NA";
		cell = row.createCell(50, customerAddress);
		cell.setFontSize(15);
		row = table.createRow(20);
		cell = row.createCell(50, "Mobile No.");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, customer.getMobile());
		cell.setFontSize(15);
		row = table.createRow(20);
		cell = row.createCell(50, "Bill No.");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(bill.getId()));
		cell.setFontSize(15);

		// Billing Date
		row = table.createRow(20);
		cell = row.createCell(50, "Billing Date");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(bill.getBilldate()));
		cell.setFontSize(15);
		if (bill.getFlagpaid() == 0) {
			// Due Date
			row = table.createRow(20);
			cell = row.createCell(50, "Due Date");
			cell.setFontSize(15);
			cell.setFont(fontBold);
			cell = row.createCell(50, String.valueOf(bill.getDuedate()));
			cell.setFontSize(15);
		}
		// Units Consumed
		row = table.createRow(20);
		cell = row.createCell(50, "Units Consumed");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(bill.getUnitconsumption()));
		cell.setFontSize(15);
		// Rate per unit
		row = table.createRow(20);
		cell = row.createCell(50, "Rate Per Unit");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(bill.getUnitrate() + " Rs."));
		cell.setFontSize(15);
		// Amount Due
		row = table.createRow(20);
		cell = row.createCell(50, "Amount due");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(bill.getBillamount() + " Rs."));
		cell.setFontSize(15);
		// Late fine
		row = table.createRow(20);
		cell = row.createCell(50, "Late fine");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(bill.getBillfine()));
		cell.setFontSize(15);

		// Total amount to be paid
		double totalAmount = bill.getBillamount() + bill.getBillfine();
		row = table.createRow(20);
		cell = row.createCell(50, "Total amount to be paid");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		cell = row.createCell(50, String.valueOf(totalAmount) + " Rs.");
		cell.setFontSize(15);
		cell.setFillColor(Color.YELLOW);
		// Bill Status
		row = table.createRow(20);
		cell = row.createCell(50, "Bill Status");
		cell.setFontSize(15);
		cell.setFont(fontBold);
		String billStatus = "Not Paid";
		Color cellColor = Color.RED;
		if (bill.getFlagpaid() == 1) {
			billStatus = "Paid";
			cellColor = Color.GREEN;
		}
		cell = row.createCell(50, billStatus);
		cell.setFontSize(15);
		cell.setFillColor(cellColor);

		table.draw();

		float tableHeight = table.getHeaderAndDataHeight();
		System.out.println("tableHeight = " + tableHeight);

		// close the content stream
		cos.close();
		// Finally Let's save the PDF
		document.save(outputStream);
		document.close();

		return outputStream.toByteArray();
	}
}
