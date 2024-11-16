package com.polstat.pendaftaranukm.controller;

import com.polstat.pendaftaranukm.dto.LeaderDto;
import com.polstat.pendaftaranukm.entity.Leader;
import com.polstat.pendaftaranukm.mapper.LeaderMapper;
import com.polstat.pendaftaranukm.repository.LeaderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaderController {
    @Autowired
    private LeaderRepository leaderRepository;

    @Operation(summary = "Update Leader by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Leader successfully updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LeaderDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Leader not found", content = @Content)
    })
    @PutMapping("/leader/{id}")
    public ResponseEntity<LeaderDto> updateLeader(@PathVariable Long id, @RequestBody LeaderDto leaderDto) {
        return leaderRepository.findById(id).map(existingLeader -> {
            Leader leaderToUpdate = LeaderMapper.mapToLeader(leaderDto);
            leaderToUpdate.setId(existingLeader.getId());
            Leader updatedLeader = leaderRepository.save(leaderToUpdate);
            LeaderDto responseDto = LeaderMapper.mapToLeaderDto(updatedLeader);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete Leader by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Leader successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Leader not found")
    })
    @DeleteMapping("/leader/{id}")
    public ResponseEntity<Void> deleteLeader(@PathVariable Long id) {
        if (leaderRepository.existsById(id)) {
            leaderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
