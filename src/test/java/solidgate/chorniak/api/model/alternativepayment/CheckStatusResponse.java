package solidgate.chorniak.api.model.alternativepayment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CheckStatusResponse {
    private OrderStatus order;
    private PayForm payForm;
    private PaymentAdviser paymentAdviser;
    private OrderMetadata orderMetadata;
    private String redirectUrl;
    private DeviceInfo deviceInfo;
    private Map<String, Transaction> transactions;
    private ThreeDs threeDs;
    private Routing routing;
}
