package com.jay.pdf.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jay.pdf.persistence.model.City;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;

@Slf4j
public class GeneratePdfReport {

  public static InputStreamResource citiesReport(List<City> cities) {

    try {
      PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(60);
      table.setWidths(new int[]{1, 3, 3});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

      PdfPCell hcell;
      hcell = new PdfPCell(new Phrase("Id", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Name", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Population", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      for (City city : cities) {
        PdfPCell cell;

        cell = new PdfPCell(new Phrase(city.getId().toString()));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(city.getName()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(city.getPopulation())));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingRight(5);
        table.addCell(cell);
      }

      Document document = new Document();
      ByteArrayOutputStream out = new ByteArrayOutputStream();

      PdfWriter.getInstance(document, out);
      document.open();
      document.add(table);

      document.close();

      return new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));
    } catch (DocumentException ex) {
      log.error("Error occurred: {0}", ex);
      throw new AssertionError("Something went wrong");
    }
  }
}
