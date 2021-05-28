package com.lol.scout.domain.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDto {
    @JsonProperty("metadata")
    private MetaDataDto metaData;
    @JsonProperty("info")
    private MatchInfoDto info;
}
