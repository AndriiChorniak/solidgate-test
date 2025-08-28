package solidgate.chorniak.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import solidgate.chorniak.api.client.PaymentPageApiClient;
import solidgate.chorniak.api.model.paymentpage.InitPaymentPageSuccessResponse;
import solidgate.chorniak.pages.PaymentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentPageTest extends BaseTest {
    private PaymentPageApiClient paymentPageApiClient = new PaymentPageApiClient();
    private PaymentPage paymentPage = new PaymentPage();

    @Test
    @DisplayName("")
    void testCreatePaymentPage() {
        int amount = 1500;
        String currency = "EUR";
        String orderId = "test-order-" + System.currentTimeMillis();

        InitPaymentPageSuccessResponse response = paymentPageApiClient.createSimplePaymentPage(orderId, amount, currency);

        paymentPage.openPaymentPage(response.getUrl());
        paymentPage.fillCardNumber("4532456618142692");
        paymentPage.fillCardExpiry("12/27");
        paymentPage.fillCVC("123");
        paymentPage.fillZipCode("04208");
        paymentPage.fillCardHolder("Test user");
        paymentPage.clickPayButton();

        assertThat(paymentPage.getPaymentResultMessage()).isEqualTo("Payment successful!");
        assertThat(paymentPage.getConfirmationPaymentPrice()).isEqualTo("€15.00");
    }
}
