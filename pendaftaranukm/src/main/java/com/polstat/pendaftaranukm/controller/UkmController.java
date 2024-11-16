package com.polstat.pendaftaranukm.controller;

import com.polstat.pendaftaranukm.dto.UkmDto;
import com.polstat.pendaftaranukm.entity.Ukm;
import com.polstat.pendaftaranukm.mapper.UkmMapper;
import com.polstat.pendaftaranukm.repository.UkmRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UkmController {
    
    @Autowired
    private UkmRepository ukmRepository;
    
    @Operation(summary = "Get all UKM")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all UKM", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UkmDto.class))}),
        @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "No UKM found", content = @Content)
    })
    
    @GetMapping("/ukm")
    public ResponseEntity<List<UkmDto>> getAllUkm() {
        List<Ukm> ukmList = ukmRepository.findAll();
        if (ukmList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UkmDto> ukmDtoList = ukmList.stream()
            .map(UkmMapper::mapToUkmDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(ukmDtoList, HttpStatus.OK);
    }
    
    @Operation(summary = "Find UKM by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of UKM", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UkmDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "No UKM found with the given name", content = @Content)
    })
    
    @GetMapping("/ukm/search")
    public ResponseEntity<List<UkmDto>> getUkmByName(@RequestParam("name") String name) {
        List<Ukm> ukmList = ukmRepository.findByName(name);
        if (ukmList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UkmDto> ukmDtoList = ukmList.stream()
            .map(UkmMapper::mapToUkmDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(ukmDtoList, HttpStatus.OK);
    }
    
    @Operation(summary = "Create a new UKM")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "UKM successfully created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UkmDto.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content)
    })
    
    @PostMapping("/ukm")
    public ResponseEntity<UkmDto> createUkm(@RequestBody UkmDto ukmDto) {
        if (ukmDto.getLeader() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ukm ukmToSave = UkmMapper.mapToUkm(ukmDto);
        Ukm savedUkm = ukmRepository.save(ukmToSave);
        UkmDto responseDto = UkmMapper.mapToUkmDto(savedUkm);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Update UKM by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UKM successfully updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UkmDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "UKM not found", content = @Content)
    })
    @PutMapping("/ukm/{id}")
    public ResponseEntity<UkmDto> updateUkm(@PathVariable Long id, @RequestBody UkmDto ukmDto) {
        return ukmRepository.findById(id).map(existingUkm -> {
            Ukm ukmToUpdate = UkmMapper.mapToUkm(ukmDto);
            ukmToUpdate.setId(existingUkm.getId());
            Ukm updatedUkm = ukmRepository.save(ukmToUpdate);
            UkmDto responseDto = UkmMapper.mapToUkmDto(updatedUkm);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete UKM by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "UKM successfully deleted"),
        @ApiResponse(responseCode = "404", description = "UKM not found")
    })
    @DeleteMapping("/ukm/{id}")
    public ResponseEntity<Void> deleteUkm(@PathVariable Long id) {
        if (ukmRepository.existsById(id)) {
            ukmRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
