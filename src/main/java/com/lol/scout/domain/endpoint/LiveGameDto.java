package com.lol.scout.domain.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LiveGameDto {
    private String queueName;
    private long time;
    private List<LiveGameParticipantDto> team1;
    private List<LiveGameParticipantDto> team2;
}
