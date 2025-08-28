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
public class Card {
    private String bin;
    private String brand;
    private String number;
    private String cardHolder;
    private String bank;
    private String country;
    private String cardExpMonth;
    private Integer cardExpYear;
    private String cardType;
    private CardToken cardToken;
    private String cardId;
}
