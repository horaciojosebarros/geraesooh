package br.com.jway.util;

import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.inject.Named;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.jway.geraesooh.model.BiSemana;
import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.model.Pi;
import br.com.jway.geraesooh.service.PiService;

@Named
public class GeneratorPI {
	
	private Pi pi;

	public static void main(String[] args) {
		// criação do documento

		//GeneratorPI g = new GeneratorPI();
		//g.geraPdf(Long.valueOf(15));

	}

	public void geraPdf(Pi pi) {
		
		this.pi = pi; 
		
		Pessoa exibidor = pi.getPessoaExibidor();
		Pessoa anunciante = pi.getPessoaAnunciante();
		
		Document document = new Document(PageSize.A0, 0f, 0f, 0f, 0f);
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
			
			// Anunciante
			table = new PdfPTable(3);
			table.setTotalWidth(new float[] { 325, 325, 200 });

			Paragraph dadosAnunciante = new Paragraph(getDadosAnunciante(anunciante));
			table.addCell(dadosAnunciante);
			
			Paragraph dadosExibidor = new Paragraph(getDadosExibidor(exibidor));
			table.addCell(dadosExibidor);
			
			Paragraph dadosBiSemana = new Paragraph(getDadosBiSemana(pi.getBiSemana()));
			table.addCell(dadosBiSemana);
			
			document.add(table);
			
			// --
			table = new PdfPTable(3);
			table.setTotalWidth(new float[] { 215, 215, 215, 200 });

			
			
			

		} catch (Exception de) {
			System.err.println(de.getMessage());
		}
		document.close();

	}

	private Chunk getDadosBiSemana(BiSemana biSemana) {
		Font font = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
		Chunk chunk = new Chunk("", font);
		chunk.append("Período/Veiculação:\n\n");
		chunk.append("Bi-sem." + biSemana.getNumero() + " / " + biSemana.getAno() + "\n\n");
		chunk.append("Emissão: " + Util.formataData(pi.getData()) + "\n\n");
		return chunk;
	}

	private Chunk getDadosExibidor(Pessoa exibidor) {
		Font font = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
		Chunk chunk = new Chunk("", font);
		chunk.append("Anunciante :" + exibidor.getNomeFantasia() + "\n");
		chunk.append("Razão Social :" + exibidor.getRazaoSocial() + "\n");
		chunk.append("Endereço: " + exibidor.getEndereco() + "." + exibidor.getNumero() + "- " + exibidor.getComplemento() + " " + exibidor.getBairro() + "\n");
		chunk.append("Cidade/UF: " + exibidor.getCidade() + " - " + exibidor.getUf() + "\n");
		chunk.append("Cidade/UF: " + exibidor.getCidade() + " - " + exibidor.getUf() + "\n");
		chunk.append("CNPJ: " + exibidor.getCnpj() + "\n");
		chunk.append("Insc.Municipal: " + exibidor.getInscricao() + "  Fone:" + exibidor.getFone1() + "\n");
		chunk.append("Contato: " + exibidor.getContato() + "  Fone:" + exibidor.getFone1() + "\n");

		return chunk;
	}

	private Chunk getDadosAnunciante(Pessoa anunciante) {
		Font font = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
		Chunk chunk = new Chunk("", font);
		chunk.append("Anunciante :" + anunciante.getNomeFantasia() + "\n");
		//chunk.append("Razão Social :" + anunciante.getRazaoSocial() + "\n");
		//chunk.append("Agência: \n");
		//chunk.append("Endereço: " + anunciante.getEndereco() + "." + anunciante.getNumero() + "- " + anunciante.getComplemento() + " " + anunciante.getBairro() + "\n");
		//chunk.append("Cidade/UF: " + anunciante.getCidade() + " - " + anunciante.getUf() + "\n");
		//chunk.append("Cidade/UF: " + anunciante.getCidade() + " - " + anunciante.getUf() + "\n");
		//chunk.append("CNPJ: " + anunciante.getCnpj() + "\n");
		//chunk.append("Insc.Estadual: " + anunciante.getInscricao() + "  Fone:" + anunciante.getFone1() + "\n");

		return chunk;
	}

	private String getNumeroPi() {
		return pi.getNumeroPi() + "/" + pi.getData().getYear();
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