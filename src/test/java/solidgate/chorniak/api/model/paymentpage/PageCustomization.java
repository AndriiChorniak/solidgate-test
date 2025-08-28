package solidgate.chorniak.api.model.paymentpage;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageCustomization {
    private String publicName;
    private String orderTitle;
    private String orderDescription;
    private List<String> paymentMethods;
    private String buttonFontColor;
    private String buttonColor;
    private String fontName;
    private Boolean isCardholderVisible;
    private String termsUrl;
    private String backUrl;
}
