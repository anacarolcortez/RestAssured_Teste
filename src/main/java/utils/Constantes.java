package utils;

import io.restassured.http.ContentType;

public interface Constantes {
    String APP_BASE_URL = "https://gorest.co.in/public-api";
    int APP_PORT = 443;
    String APP_BASE_PATH = "";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 5000L;
    String TOKEN = "f0f4c66eabf9af23a740847b94f218eb9e3e251f133ae0b38169a50aa9bb76f6";
}
