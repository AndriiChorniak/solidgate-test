package solidgate.chorniak.api.model.paymentpage;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
    private String orderId;
    private String orderDate;
    private String orderItems;
    private String orderDescription;
    private Integer orderNumber;
    private String currency;
    private Integer amount;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerDateOfBirth;
    private String language;
    private Boolean force3ds;
    private String type;
    private Integer settleInterval;
    private String transactionSource;
    private String purchaseCountry;
    private Integer retryAttempt;
    private String website;
    private Map<String, String> orderMetadata;
    private String trafficSource;
    private String geoCountry;
    private String geoCity;
    private List<String> googlePayAllowedAuthMethods;
}
