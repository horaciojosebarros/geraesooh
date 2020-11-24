package br.com.jway.util;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratorPI {
	
	private String numeroPi;

	public void setNumeroPi(String numeroPi) {
		this.numeroPi = numeroPi;
	}

	public static void main(String[] args) {
		// criação do documento

		GeneratorPI g = new GeneratorPI();
		g.geraPdf(Long.valueOf(254));

	}

	private void geraPdf(Long idPi) {
		Document document = new Document(PageSize.A4.rotate(), 0f, 10f, 10f, 0f);
		try {

			PdfWriter.getInstance(document, new FileOutputStream("C:\\tmp\\PDF_DevMedia.pdf"));
			document.open();

			// adicionando um parágrafo ao documento
			//document.add(new Paragraph("Gerando um PDF usando iText em Java"));

			PdfPTable table = new PdfPTable(3);
			table.setTotalWidth(new float[] { 250, 400, 200 });

			Image image = Image.getInstance("C:\\tmp\\geraes.png");
			PdfPCell cell = new PdfPCell(image, true);
			//cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(100);
			table.addCell(cell);

			Paragraph dadosAgenciaMaster = new Paragraph(getDadosAgenciaMaster());
			table.addCell(dadosAgenciaMaster);

			
			Paragraph numeroPi = new Paragraph("\n\nP.I Número\n " + getNumeroPi());
			cell = new PdfPCell(numeroPi);
			//cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			document.add(table);

		} catch (Exception de) {
			System.err.println(de.getMessage());
		}
		document.close();

	}

	private String getNumeroPi() {
		return "254/2020";
	}

	private Chunk getDadosAgenciaMaster() {
		Font font = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
		Chunk chunk = new Chunk("Geraes Publicidade", font);
		chunk.append("\n\n");
		chunk.append("Razão Social:" + "Roberto M. da Silva\n");
		chunk.append("Rua Castelo de Lisboa, 561 - CEP 31330-340\n");
		chunk.append("Belo Horizonte/MG  (31)3037-2580  3024-6955  98871-1067\n");
		chunk.append("CNPJ:26.296.084/0001-46 Insc.Municipal: 0781895/001-0\n");
		chunk.append("roberto@geraesmidiaexterior.com.br\n");
		

		return chunk;
	}

}