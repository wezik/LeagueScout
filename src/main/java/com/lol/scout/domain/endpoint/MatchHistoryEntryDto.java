package com.lol.scout.domain.endpoint;

import com.lol.scout.domain.match.ParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchHistoryEntryDto {
    private long gameEnd;
    private long gameDuration;
    private String gameMode;
    private String patch;
    private ParticipantDto summoner;
    private List<ParticipantDto> team1;
    private List<ParticipantDto> team2;
}
