package APIs;

import Models.UsuarioGoRestAPI;
import org.json.JSONObject;
import payloads.PayloadUsuarioDadosValidos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static utils.GerarEvidencia.*;

public class GoRestAPI implements PayloadUsuarioDadosValidos {

    UsuarioGoRestAPI user = new UsuarioGoRestAPI();
    String path;
    String response;

    public void criarUsuarioValido() {
        user.setName(payloadName);
        user.setEmail(payloadEmail);
        user.setGender(payloadGender);
        user.setStatus(payloadStatus);
    }

    public void setEndpoint(String endpoint) {
        path = endpoint;
    }

    public void adicionarUsuarioComSucesso() {
        response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .body(user)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .extract().asPrettyString();
        anexarEvidencia("Método", "POST");
        anexarEvidencia("Endpoint", APP_BASE_URL + path);
        anexarEvidencia("Response", response);
    }

    public void consultarUsuarioComSucesso() {
        String id = getId().toString();
        response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get(path+"/"+id)
                .then()
                .statusCode(200)
                .body("data.name", is(user.getName()))
                .body("data.email", is(user.getEmail()))
                .body("data.gender", is(user.getGender()))
                .body("data.status", is(user.getStatus()))
                .extract().asPrettyString();
        anexarEvidencia("Método", "GET");
        anexarEvidencia("Endpoint", APP_BASE_URL + path);
        anexarEvidencia("Response", response);
    }

    private Integer getId() {
        JSONObject jsonObject =  new JSONObject(response);
        JSONObject jsonPartialData = jsonObject.getJSONObject("data");
        return jsonPartialData.getInt("id");
    }

    public boolean validarStatus(String value) {
        JSONObject jsonObject =  new JSONObject(response);
        int keyValue = jsonObject.getInt("code");
        return keyValue == Integer.parseInt(value);
    }

    public void deletarUsuarioComSucesso() {
        String id = getId().toString();
        response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .delete(path+"/"+id)
                .then()
                .statusCode(200)
                .extract().asPrettyString();
        anexarEvidencia("Método", "DELETE");
        anexarEvidencia("Endpoint", APP_BASE_URL + path);
        anexarEvidencia("Response", response);
    }

    public void editarUsuarioComSucesso() {
        String id = getId().toString();
        user.setEmail(payloadEmailEditado);
        response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .body(user)
                .when()
                .patch(path+"/"+id)
                .then()
                .statusCode(200)
                .extract().asPrettyString();
        anexarEvidencia("Método", "PATCH");
        anexarEvidencia("Endpoint", APP_BASE_URL + path);
        anexarEvidencia("Response", response);
    }

}