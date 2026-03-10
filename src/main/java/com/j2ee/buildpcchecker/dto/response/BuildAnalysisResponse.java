package com.j2ee.buildpcchecker.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Build analysis result containing bottleneck, balance status, and power consumption")
public class BuildAnalysisResponse {

    @Schema(description = "Bottleneck percentage between CPU and GPU", example = "12.5")
    double bottleneck;

    @Schema(description = "Balance status of the build", example = "Good Balance")
    String balanceStatus;

    @Schema(description = "Estimated total power consumption in watts", example = "450")
    int estimatedWattage;
}

