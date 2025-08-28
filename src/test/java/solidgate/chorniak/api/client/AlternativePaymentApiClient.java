package solidgate.chorniak.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import solidgate.chorniak.api.model.alternativepayment.CheckStatusRequest;
import solidgate.chorniak.api.model.alternativepayment.CheckStatusResponse;

public class AlternativePaymentApiClient extends BaseApiClient{
    private static final String BASE_URL = "https://pay.solidgate.com";

    @SneakyThrows
    public Response checkStatus(CheckStatusRequest payload) {
        return specWithAuth(payload)
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
