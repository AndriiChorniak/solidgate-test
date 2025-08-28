package solidgate.chorniak.api.model.alternativepayment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderStatus {
    private String orderId;
    private Long amount;
    private String currency;
    private String mid;
    private String descriptor;
    private Boolean fraudulent;
    private Long marketingAmount;
    private String marketingCurrency;
    private Long processingAmount;
    private String processingCurrency;
    private String status;
    private Long refundedAmount;
    private String orderDescription;
    private String trafficSource;
    private String customerEmail;
    private String authCode;
    private String ipAddress;
    private String pspOrderId;
    private String providerPaymentId;
}
