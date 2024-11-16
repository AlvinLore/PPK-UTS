package com.polstat.pendaftaranukm.mapper;

import com.polstat.pendaftaranukm.dto.UkmDto;
import com.polstat.pendaftaranukm.entity.Ukm;

public class UkmMapper {
    public static Ukm mapToUkm(UkmDto ukmDto){
        return Ukm.builder()
            .id(ukmDto.getId())
            .name(ukmDto.getName())
            .leader(ukmDto.getLeader())
            .description(ukmDto.getDescription())
            .build();
    }
    public static UkmDto mapToUkmDto(Ukm ukm){
        return UkmDto.builder()
            .id(ukm.getId())
            .name(ukm.getName())
            .leader(ukm.getLeader())
            .description(ukm.getDescription())
            .build();
    }
}
