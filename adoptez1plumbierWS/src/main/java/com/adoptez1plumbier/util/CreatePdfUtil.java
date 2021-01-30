package com.adoptez1plumbier.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.adoptez1plumbier.beans.Entity;
import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

public class CreatePdfUtil {
	private static final Logger logger = LoggerFactory.getLogger(CreatePdfUtil.class);

	public ByteArrayInputStream createPdf(Entity datas) throws DocumentException, MalformedURLException, IOException {
		Font blueFont = FontFactory.getFont("Helvetica", 18.0F, 1, (BaseColor) new CMYKColor(0, 0, 0, 255));
		Font header = FontFactory.getFont("Helvetica", 14.0F, 1, (BaseColor) new CMYKColor(0, 0, 0, 255));
		String IMG1 = "img/bien.png";
		String IMG2 = "img/moyen.png";
		String IMG3 = "img/mauvais.png";
		String IMGTik = "img/tik.png";
		BufferedImage image = null;
		Document document = new Document(PageSize.A4, 20.0F, 20.0F, 40.0F, 80.0F);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		Entity en = new Entity();
		en.setDate(formatter.format(date));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			document.open();
			Paragraph paragraph = new Paragraph("QUITUS TRAVAUX DE PLOMBERIE", blueFont);
			paragraph.setAlignment(1);
			document.add((Element) new Paragraph(  paragraph));
			PdfPTable table = null;
			PdfPTable table1 = null;
			PdfPTable table2 = null;
			PdfPTable table4 = null;
			List<SectionData> sectiondatas = null;
			image = new BufferedImage(50, 50, 2);
			if (!datas.equals(null)) {
				for (int j = 0; j < datas.getSections().size(); j++) {
					if (datas.getSections().size() > 0) {
						int sectiontype = ((Section) datas.getSections().get(j)).getSectionType();
						sectiondatas = ((Section) datas.getSections().get(j)).getSectionData();
						if (sectiontype == 0) {
							if (sectiondatas.size() > 0) {
								table = new PdfPTable(2);
								table.setWidthPercentage(100.0F);
								table.setSpacingBefore(10.0F);
								table.setSpacingAfter(10.0F);
								for (int d = 0; d < sectiondatas.size(); d++) {
									PdfPCell cellname = new PdfPCell(
											  new Paragraph(((SectionData) sectiondatas.get(d)).getFieldName()));
									cellname.setBorderColor(BaseColor.WHITE);
									cellname.setHorizontalAlignment(0);
									cellname.setPadding(5.0F);
									table.addCell(cellname);
									PdfPCell cellInput = new PdfPCell(  new Paragraph(
											((SectionData) sectiondatas.get(d)).getFieldInput()));
									cellInput.setBorderColor(BaseColor.BLACK);
									cellInput.setHorizontalAlignment(1);
									cellInput.setPadding(5.0F);
									table.addCell(cellInput);
								}
							}
						} else if (sectiontype == 1) {
							if (sectiondatas.size() > 0) {
								table1 = new PdfPTable(4);
								table1.setHeaderRows(1);
								table1.setWidthPercentage(100.0F);
								table1.setSpacingBefore(10.0F);
								table1.setSpacingAfter(10.0F);
								PdfPCell cellHeader = new PdfPCell(  new Paragraph(" "));
								cellHeader.setBorderColor(BaseColor.WHITE);
								cellHeader.setHorizontalAlignment(0);
								cellHeader.setPadding(5.0F);
								table1.addCell(cellHeader);
								PdfPCell cellHeaderOu = new PdfPCell(  new Paragraph("OUI/NOM", header));
								cellHeaderOu.setBorderColor(BaseColor.BLACK);
								cellHeaderOu.setHorizontalAlignment(1);
								cellHeaderOu.setVerticalAlignment(1);
								cellHeaderOu.setPadding(5.0F);
								table1.addCell(cellHeaderOu);
								PdfPCell cellHeaderNombre = new PdfPCell(  new Paragraph("NOMBRE", header));
								cellHeaderNombre.setBorderColor(BaseColor.BLACK);
								cellHeaderNombre.setHorizontalAlignment(1);
								cellHeaderNombre.setPadding(5.0F);
								table1.addCell(cellHeaderNombre);
								PdfPCell cellHeaderImage = new PdfPCell(  new Paragraph("IMAGE", header));
								cellHeaderImage.setBorderColor(BaseColor.BLACK);
								cellHeaderImage.setHorizontalAlignment(1);
								cellHeaderImage.setPadding(5.0F);
								table1.addCell(cellHeaderImage);
								for (int d = 0; d < sectiondatas.size(); d++) {
									PdfPCell cellname = new PdfPCell(
											  new Paragraph((sectiondatas.get(d)).getFieldName()));
									cellname.setBorderColor(BaseColor.WHITE);
									cellname.setHorizontalAlignment(0);
									cellname.setPadding(10.0F);
									table1.addCell(cellname);
									PdfPCell cellfirstNumb = new PdfPCell();
									if ((sectiondatas.get(d)).getFieldNumberFirstSection() == 0) {
										cellfirstNumb = new PdfPCell(new Paragraph(OuiNon.NON.getMessage()));
									} else {
										cellfirstNumb = new PdfPCell(new Paragraph(OuiNon.OUI.getMessage()));
									}
									cellfirstNumb.setBorderColor(BaseColor.BLACK);
									cellfirstNumb.setHorizontalAlignment(1);
									cellfirstNumb.setPadding(5.0F);
									table1.addCell(cellfirstNumb);
									PdfPCell cellsecondou = new PdfPCell();
									if ((sectiondatas.get(d)).getIsFieldIsNumberSecondSection()) {
										cellsecondou = new PdfPCell(new Paragraph(OuiNon.NOMBRETRUE.getMessage()));
									} else {
										cellsecondou = new PdfPCell(new Paragraph(OuiNon.NOMBREFALSE.getMessage()));
									}
									cellsecondou.setBorderColor(BaseColor.BLACK);
									cellsecondou.setHorizontalAlignment(1);
									cellsecondou.setPadding(5.0F);
									table1.addCell(cellsecondou);
									
									PdfPCell cellSecondImg;
									if(sectiondatas.get(d).getFieldsImageSecondSection() != null) {
										if(sectiondatas.get(d).getIsFieldIsNumberSecondSection())
											cellSecondImg = new PdfPCell(Image.getInstance(Base64.decode(sectiondatas.get(d).getFieldsImageSecondSection())));
										else
											cellSecondImg = new PdfPCell(  new Paragraph(" "));
									}
									else
										cellSecondImg = new PdfPCell(  new Paragraph(" "));
									
									cellSecondImg.setBorderColor(BaseColor.BLACK);
									cellSecondImg.setHorizontalAlignment(1);
									cellSecondImg.setPadding(5.0F);
									cellSecondImg.setFixedHeight(50.0F);
									table1.addCell(cellSecondImg);
								}
							}
						} else if (sectiontype == 2 && sectiondatas.size() > 0) {
							table2 = new PdfPTable(4);
							table2.setWidthPercentage(100.0F);
							table2.setSpacingBefore(5.0F);
							table2.setSpacingAfter(10.0F);
							PdfPCell cellImg = new PdfPCell(  new Paragraph(" "));
							cellImg.setBorderColor(BaseColor.WHITE);
							cellImg.setHorizontalAlignment(0);
							cellImg.setPadding(30.0F);
							table2.addCell(cellImg);
							PdfPCell cellBienImg = new PdfPCell(createImageCell(IMG1));
							cellBienImg.setBorderColor(BaseColor.BLACK);
							cellBienImg.setHorizontalAlignment(1);
							cellBienImg.setPadding(5.0F);
							cellBienImg.setFixedHeight(30.0F);
							table2.addCell(cellBienImg);
							PdfPCell cellMoyenImg = new PdfPCell(createImageCell(IMG2));
							cellMoyenImg.setBorderColor(BaseColor.BLACK);
							cellMoyenImg.setHorizontalAlignment(1);
							cellMoyenImg.setPadding(5.0F);
							cellMoyenImg.setFixedHeight(30.0F);
							table2.addCell(cellMoyenImg);
							PdfPCell cellMaviusImg = new PdfPCell(createImageCell(IMG3));
							cellMaviusImg.setBorderColor(BaseColor.BLACK);
							cellMaviusImg.setHorizontalAlignment(1);
							cellMaviusImg.setPadding(5.0F);
							cellMaviusImg.setFixedHeight(30.0F);
							table2.addCell(cellMaviusImg);
							for (int d = 0; d < sectiondatas.size(); d++) {
								PdfPCell cellBien, cellMoyen, cellMavius, cellname = new PdfPCell(
										new Paragraph(((SectionData) sectiondatas.get(d)).getFieldName()));
								cellname.setBorderColor(BaseColor.WHITE);
								cellname.setHorizontalAlignment(0);
								cellname.setPadding(5.0F);
								table2.addCell(cellname);
								if (((SectionData) sectiondatas.get(d)).getFieldsFeeling() == 0) {
									cellBien = new PdfPCell(createImageCell(IMGTik));
								} else {
									cellBien = new PdfPCell(new Paragraph(""));
								}
								cellBien.setBorderColor(BaseColor.BLACK);
								cellBien.setHorizontalAlignment(1);
								cellBien.setPadding(5.0F);
								cellBien.setFixedHeight(15.0F);
								table2.addCell(cellBien);
								if (((SectionData) sectiondatas.get(d)).getFieldsFeeling() == 1) {
									cellMoyen = new PdfPCell(createImageCell(IMGTik));
								} else {
									cellMoyen = new PdfPCell(new Paragraph(""));
								}
								cellMoyen.setBorderColor(BaseColor.BLACK);
								cellMoyen.setHorizontalAlignment(1);
								cellMoyen.setPadding(5.0F);
								cellMoyen.setFixedHeight(15.0F);
								table2.addCell(cellMoyen);
								if (((SectionData) sectiondatas.get(d)).getFieldsFeeling() == 2) {
									cellMavius = new PdfPCell(createImageCell(IMGTik));
								} else {
									cellMavius = new PdfPCell(  new Paragraph(""));
								}
								cellMavius.setBorderColor(BaseColor.BLACK);
								cellMavius.setHorizontalAlignment(1);
								cellMavius.setPadding(5.0F);
								cellMavius.setFixedHeight(15.0F);
								table2.addCell(cellMavius);
							}
						}
					}
				}

				table4 = new PdfPTable(2);
				table4.setWidthPercentage(100.0F);
				table4.setSpacingBefore(10.0F);
				table4.setSpacingAfter(10.0F);
				PdfPCell cellDate = new PdfPCell(new Paragraph("DATE"));
				cellDate.setBorderColor(BaseColor.WHITE);
				cellDate.setHorizontalAlignment(0);
				cellDate.setPadding(5.0F);
				table4.addCell(cellDate);
				PdfPCell cellDateData = new PdfPCell(new Paragraph(en.getDate()));
				cellDateData.setBorderColor(BaseColor.BLACK);
				cellDateData.setHorizontalAlignment(1);
				cellDateData.setPadding(5.0F);
				table4.addCell(cellDateData);
				PdfWriter.getInstance(document, out);
				document.open();
				document.add((Element) paragraph);
				document.add((Element) Chunk.NEWLINE);
				document.add((Element) table4);
				document.add((Element) table);
				document.add((Element) table1);
				document.add((Element) table2);
				document.close();
			}

		} catch (DocumentException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String paths = classLoader.getResource(path).getPath();
		Image img = Image.getInstance(paths);
		PdfPCell cell = new PdfPCell(img, true);
		return cell;
	}

	public static void main(String[] args) throws MalformedURLException, IOException, DocumentException {

		String IMG1 = "bien.png";
		String IMG2 = "WebContent/moyen.png";
		String IMG3 = "WebContent/WEB-INF/template/mauvais.png";

		PdfPCell cellBienImg = new PdfPCell();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date));
		Image img1 = Image.getInstance(IMG1);
		Image img2 = Image.getInstance(IMG2);
		Image img3 = Image.getInstance(IMG3);

	}
}
