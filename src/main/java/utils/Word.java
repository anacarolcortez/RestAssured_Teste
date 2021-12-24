package utils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class Word {

    XWPFDocument documentoWord;
    XWPFParagraph paragrafoWord;
    XWPFTable tabelaWord;
    XWPFRun run;

    public Word() {
        documentoWord = new XWPFDocument();
    }

    public void criaParagrafo() {
        paragrafoWord = documentoWord.createParagraph();
        run = paragrafoWord.createRun();
    }

    public void criaTabela(String identificador, String textoDescricao) {
        tabelaWord = documentoWord.createTable();
        tabelaWord.setTableAlignment(TableRowAlign.LEFT);
        tabelaWord.setWidth(8000);
        XWPFTableRow linhaTabela;
        linhaTabela = tabelaWord.getRow(0);
        linhaTabela.getCell(0).setText(identificador);
        linhaTabela.createCell();
        linhaTabela.getCell(1).setText(textoDescricao);
    }

    public void anexarEvidenciaWord(String evidencia) {
        try {
            paragrafoWord.setAlignment(ParagraphAlignment.LEFT);
            evidencia = evidencia.replaceAll("[{]", "{#");
            evidencia = evidencia.replaceAll("[}]", "#}");
            evidencia = evidencia.replaceAll(",", ",#");
            String[] evidenciaJson = evidencia.split("#");
            for (int i = 0; i < evidenciaJson.length; i++){
                criaParagrafo();
                run.setText(evidenciaJson[i]);
            }
        } catch (Exception ex) {
            run.setText("Não foi possível gerar evidência");
            ex.printStackTrace();
        }

    }

    public void anexarSubtitulo(String titulo) {
        try {
            criaParagrafo();
            criaParagrafo();
            paragrafoWord.setAlignment(ParagraphAlignment.LEFT);
            run.setBold(true);
            run.setText(titulo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void salvarDocumentoWord(String nomeDocumento) throws IOException {
        try {
            OutputStream documentoFinalWord = new FileOutputStream("evidencias/" + nomeDocumento + ".docx");
            documentoWord.write(documentoFinalWord);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        documentoWord.close();
    }

    public void anexarLogotipos() throws FileNotFoundException {
        String logo = "imagens/logo.jpeg";
        try {
            FileInputStream logoFake = new FileInputStream(logo);
            criaParagrafo();
            paragrafoWord.setAlignment(ParagraphAlignment.CENTER);
            run.addPicture(logoFake, XWPFDocument.PICTURE_TYPE_JPEG, logo, Units.toEMU(140), Units.toEMU(70));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}