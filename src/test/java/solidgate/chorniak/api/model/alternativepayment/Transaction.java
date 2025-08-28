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
public class Transaction {
    private String id;
    private String operation;
    private String status;
    private String descriptor;
    private Long amount;
    private String currency;
    private String createdAt;
    private String updatedAt;
    private Long marketingAmount;
    private String marketingCurrency;
    private BillingDetails billingDetails;
    private Card card;
    private CardToken cardToken;
    private String schemeTransactionId;
}
