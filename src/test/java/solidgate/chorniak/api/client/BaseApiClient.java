package solidgate.chorniak.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import solidgate.chorniak.utils.PropertyReader;
import solidgate.chorniak.utils.SignatureGenerator;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiClient {
    private static final String PUBLIC_KEY = PropertyReader.get("public.key");
    private static final String SECRET_KEY = PropertyReader.get("secret.key");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    protected RequestSpecification specWithAuth(Object payload) {
        String requestJson = objectMapper.writeValueAsString(payload);
        String signature = SignatureGenerator.generateSignature(PUBLIC_KEY, requestJson, SECRET_KEY);

        Map<String, String> headers = new HashMap<>();
        headers.put("merchant", PUBLIC_KEY);
        headers.put("Signature", signature);
        return RestAssured
                .given()
                .headers(headers);

    }
}
