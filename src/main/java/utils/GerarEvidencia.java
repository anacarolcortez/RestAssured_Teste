package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GerarEvidencia implements Constantes{
    static Word word;

    @Before
    public void criarDocX(Scenario scenario) throws FileNotFoundException {
        word = new Word();
        word.anexarLogotipos();
        word.criaTabela("Projeto", "Testando GoRest API");
        word.criaTabela("Caso de Teste", scenario.getName());
        SimpleDateFormat formatoDeData = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatoDeData.format(new Date());
        word.criaTabela("Data", data);
        word.criaTabela("Status", scenario.getStatus().toString());
        setUp();
    }

    @After
    public void salvarDocX(Scenario scenario) throws IOException {
        word.salvarDocumentoWord(scenario.getName());
    }

    public static void anexarEvidencia(String titulo, String evidencia) {
        if (evidencia != null) {
            GerarEvidencia.word.anexarSubtitulo(titulo);
            GerarEvidencia.word.anexarEvidenciaWord(evidencia);
        }
    }

    public void setUp(){
        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.port = APP_PORT;
        RestAssured.basePath = APP_BASE_PATH;

        RequestSpecBuilder reqbuilder = new RequestSpecBuilder();
        reqbuilder.setContentType(APP_CONTENT_TYPE);
        //reqbuilder.addHeader("Authorization", "Bearer " + TOKEN);
        //reqbuilder.addHeader("Authorization", "JWT " + TOKEN);
        RestAssured.requestSpecification = reqbuilder.build();

        ResponseSpecBuilder resbuilder = new ResponseSpecBuilder();
        resbuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resbuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
