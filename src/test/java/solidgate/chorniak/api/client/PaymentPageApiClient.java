package solidgate.chorniak.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import solidgate.chorniak.api.model.paymentpage.InitPaymentPageRequest;
import solidgate.chorniak.api.model.paymentpage.InitPaymentPageSuccessResponse;
import solidgate.chorniak.api.model.paymentpage.Order;
import solidgate.chorniak.api.model.paymentpage.PageCustomization;
import solidgate.chorniak.utils.SignatureGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentPageApiClient {
    private static final String PUBLIC_KEY = "api_pk_96fb6cd4_b85f_4578_abe5_91b90efeebdb"; //TODO move to config
    private static final String SECRET_KEY = "api_sk_5bcdd1fd_3b40_4122_a06f_6965b6ed3721"; //TODO move to config
    private static final String BASE_URL = "https://payment-page.solidgate.com";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public Response initPaymentPage(InitPaymentPageRequest payload) {
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
                .post(BASE_URL + "/api/v1/init")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .response();
    }

    public InitPaymentPageSuccessResponse initPaymentPageSuccess(InitPaymentPageRequest request) {
        return initPaymentPage(request).as(InitPaymentPageSuccessResponse.class);
    }

    public InitPaymentPageSuccessResponse createSimplePaymentPage(String orderId, int amount, String currency) {
        Order order = Order.builder()
                .orderId(orderId)
                .orderDate("2015-12-21 11:21:30")
                .orderItems("item 1 x 10, item 2 x 30")
                .orderDescription("Premium package")
                .orderNumber(9)
                .currency(currency)
                .amount(amount)
                .customerEmail("test@solidgate.com")
                .customerFirstName("Nikola")
                .customerLastName("Tesla")
                .customerPhone("+10111111111")
                .customerDateOfBirth("1988-11-21")
                .language("en")
                .force3ds(false)
                .type("auth")
                .settleInterval(0)
                .transactionSource("main_menu")
                .purchaseCountry("USA")
                .retryAttempt(3)
                .website("https://solidgate.com")
                .orderMetadata(Map.of("coupon_code", "NY2018", "partner_id", "123989"))
                .trafficSource("facebook")
                .geoCountry("USA")
                .geoCity("New Castle")
                .googlePayAllowedAuthMethods(List.of("PAN_ONLY"))
                .build();

        PageCustomization pageCustomization = PageCustomization.builder()
                .publicName("Public Name")
                .orderTitle("Order Title")
                .orderDescription("Premium package")
                .paymentMethods(List.of("paypal"))
                .buttonFontColor("#FFFFFF")
                .buttonColor("#000000")
                .fontName("Open Sans")
                .isCardholderVisible(true)
                .termsUrl("https://solidgate.com/terms")
                .backUrl("https://solidgate.com")
                .build();

        InitPaymentPageRequest request = InitPaymentPageRequest.builder()
                .order(order)
                .pageCustomization(pageCustomization)
                .build();

        return initPaymentPageSuccess(request);
    }

}
