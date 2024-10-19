package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ETransportState;
import com.ahmete.busbuscard.utility.enums.ETransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwTransport {
    private Long id;
    private ETransportType eTransportType;
    private ETransportState eTransportState;


}
