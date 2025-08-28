package solidgate.chorniak.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import solidgate.chorniak.api.client.AlternativePaymentApiClient;
import solidgate.chorniak.api.client.PaymentPageApiClient;
import solidgate.chorniak.api.model.alternativepayment.CheckStatusRequest;
import solidgate.chorniak.api.model.alternativepayment.CheckStatusResponse;
import solidgate.chorniak.api.model.alternativepayment.Transaction;
import solidgate.chorniak.api.model.paymentpage.InitPaymentPageSuccessResponse;
import solidgate.chorniak.pages.PaymentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentPageTest extends BaseTest {
    private PaymentPageApiClient paymentPageApiClient = new PaymentPageApiClient();
    private AlternativePaymentApiClient alternativePaymentApiClient = new AlternativePaymentApiClient();
    private PaymentPage paymentPage = new PaymentPage();

    @Test
    @DisplayName("Verify user can create Payment page via API and complete payment via UI")
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

        CheckStatusResponse checkStatusResponse = alternativePaymentApiClient.checkStatusSuccess(
                CheckStatusRequest.builder()
                        .orderId(orderId)
                        .build()
        );

        assertThat(checkStatusResponse.getOrder().getOrderId()).isEqualTo(orderId);
        assertThat(checkStatusResponse.getOrder().getAmount()).isEqualTo(amount);
        assertThat(checkStatusResponse.getOrder().getCurrency()).isEqualTo(currency);
        assertThat(checkStatusResponse.getTransactions().values())
                .extracting(Transaction::getStatus)
                .containsOnly("success");
    }
}
