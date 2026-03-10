package com.j2ee.buildpcchecker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Request for analyzing PC build bottleneck and power consumption")
public class AnalyzeBuildRequest {

    @NotBlank(message = "CPU_ID_REQUIRED")
    @Schema(description = "CPU ID (UUID)", example = "uuid", required = true)
    String cpuId;

    @NotBlank(message = "GPU_ID_REQUIRED")
    @Schema(description = "GPU (VGA) ID (UUID)", example = "uuid", required = true)
    String gpuId;

    @NotBlank(message = "RAM_ID_REQUIRED")
    @Schema(description = "RAM ID (UUID)", example = "uuid", required = true)
    String ramId;

    @NotBlank(message = "SSD_ID_REQUIRED")
    @Schema(description = "SSD ID (UUID)", example = "uuid", required = true)
    String ssdId;
}

