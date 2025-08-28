package solidgate.chorniak.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import solidgate.chorniak.api.model.alternativepayment.CheckStatusRequest;
import solidgate.chorniak.api.model.alternativepayment.CheckStatusResponse;
import solidgate.chorniak.utils.SignatureGenerator;

import java.util.HashMap;
import java.util.Map;

public class AlternativePaymentApiClient {
    private static final String PUBLIC_KEY = "api_pk_96fb6cd4_b85f_4578_abe5_91b90efeebdb"; //TODO move to config
    private static final String SECRET_KEY = "api_sk_5bcdd1fd_3b40_4122_a06f_6965b6ed3721"; //TODO move to config
    private static final String BASE_URL = "https://pay.solidgate.com";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public Response checkStatus(CheckStatusRequest payload) {
        String requestJson = objectMapper.writeValueAsString(payload);
        String signature = SignatureGenerator.generateSignature(PUBLIC_KEY, requestJson, SECRET_KEY);

        Map<String, String> headers = new HashMap<>();
        headers.put("merchant", PUBLIC_KEY);
        headers.put("Signature", signature);

        return RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL + "/api/v1/status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .response();
    }

    public CheckStatusResponse checkStatusSuccess(CheckStatusRequest request) {
        return checkStatus(request).as(CheckStatusResponse.class);
    }
}
