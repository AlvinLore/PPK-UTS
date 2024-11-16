package com.polstat.pendaftaranukm.mapper;

import com.polstat.pendaftaranukm.dto.LeaderDto;
import com.polstat.pendaftaranukm.entity.Leader;

public class LeaderMapper {
    public static Leader mapToLeader(LeaderDto leaderDto){
        return Leader.builder()
            .id(leaderDto.getId())
            .nim(leaderDto.getNim())
            .name(leaderDto.getName())
            .leaderClass(leaderDto.getLeaderClass())
            .build();
    }
    public static LeaderDto mapToLeaderDto(Leader leader){
        return LeaderDto.builder()
            .id(leader.getId())
            .nim(leader.getNim())
            .name(leader.getName())
            .leaderClass(leader.getLeaderClass())
            .build();
    }
}
