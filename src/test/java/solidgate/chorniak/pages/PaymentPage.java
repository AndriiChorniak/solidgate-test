package solidgate.chorniak.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    private final SelenideElement paymentForm = $x("//main[contains(@class, 'PaymentPage_form')]");
    private final SelenideElement cardNumberInput = $x("//input[@id='ccnumber']");
    private final SelenideElement cardExpiryInput = $x("//input[@data-testid='cardExpiryDate']");
    private final SelenideElement cvcInput = $x("//input[@id='cvv2']");
    private final SelenideElement cardHolderInput = $x("//input[@data-testid='cardHolder']");
    private final SelenideElement zipCodeInput = $x("//input[@data-testid='zip']");
    private final SelenideElement payButton = $x("//button[@data-testid='submit']");
    private final SelenideElement paymentResult = $x("//h1[@data-testid='status-title']");
    private final SelenideElement confirmationPaymentPrice = $x("//div[@data-testid='status-order-price-header']//div[@data-testid='price_major']");

    public void openPaymentPage(String url) {
        Selenide.open(url);
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        paymentForm.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void fillCardNumber(String cardNumber) {
        cardNumberInput.setValue(cardNumber);
    }

    public void fillCardExpiry(String expiry) {
        cardExpiryInput.setValue(expiry);
    }

    public void fillCVC(String cvc) {
        cvcInput.setValue(cvc);
    }

    public void fillZipCode(String zipCode) {
        zipCodeInput.setValue(zipCode);
    }

    public void fillCardHolder(String cardHolder) {
        cardHolderInput.setValue(cardHolder);
    }

    public void clickPayButton() {
        payButton.click();
        paymentForm.shouldNotBe(Condition.visible);
    }

    public String getPaymentResultMessage() {
        return paymentResult.shouldBe(Condition.visible)
                .getText();
    }

    public String getConfirmationPaymentPrice() {
        return confirmationPaymentPrice.shouldBe(Condition.visible)
                .getText();
    }
}
