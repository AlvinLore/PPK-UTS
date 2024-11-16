package com.polstat.pendaftaranukm.controller;

import com.polstat.pendaftaranukm.dto.RegistrationDto;
import com.polstat.pendaftaranukm.entity.Registration;
import com.polstat.pendaftaranukm.mapper.RegistrationMapper;
import com.polstat.pendaftaranukm.repository.RegistrationRepository;
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
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Operation(summary = "Get all UKM registrations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all registrations", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    
    @GetMapping("/registration")
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        List<Registration> registrationList = registrationRepository.findAll();
        if (registrationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RegistrationDto> registrationDtoList = registrationList.stream()
            .map(RegistrationMapper::mapToRegistrationDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(registrationDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Find Registration by User NIM")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Registrations by User NIM", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    @GetMapping("/registration/search/nim")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByUserNim(@RequestParam("nim") String nim) {
        List<Registration> registrationList = registrationRepository.findByUserNim(nim);
        if (registrationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RegistrationDto> registrationDtoList = registrationList.stream()
            .map(RegistrationMapper::mapToRegistrationDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(registrationDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Find Registration by User Email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Registrations by User Email", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    
    @GetMapping("/registration/search/email")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByUserEmail(@RequestParam("email") String email) {
        List<Registration> registrationList = registrationRepository.findByUserEmail(email);
        if (registrationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RegistrationDto> registrationDtoList = registrationList.stream()
            .map(RegistrationMapper::mapToRegistrationDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(registrationDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Find Registration by UKM Name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Registrations by UKM Name", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    
    @GetMapping("/registration/search/ukm")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByUkmName(@RequestParam("name") String name) {
        List<Registration> registrationList = registrationRepository.findByUkmName(name);
        if (registrationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RegistrationDto> registrationDtoList = registrationList.stream()
            .map(RegistrationMapper::mapToRegistrationDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(registrationDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Registration")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registration successfully created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content)
    })
    
    @PostMapping("/registration")
    public ResponseEntity<RegistrationDto> createRegistration(@RequestBody RegistrationDto registrationDto) {
        if (registrationDto.getUser() == null || registrationDto.getUkm() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Registration registrationToSave = RegistrationMapper.mapToRegistration(registrationDto);
        Registration savedRegistration = registrationRepository.save(registrationToSave);
        RegistrationDto responseDto = RegistrationMapper.mapToRegistrationDto(savedRegistration);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Update Registration by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registration successfully updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Registration not found", content = @Content)
    })
    
    @PutMapping("/registration/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(@PathVariable Long id, @RequestBody RegistrationDto registrationDto) {
        return registrationRepository.findById(id).map(existingRegistration -> {
            Registration registrationToUpdate = RegistrationMapper.mapToRegistration(registrationDto);
            registrationToUpdate.setId(existingRegistration.getId());
            Registration updatedRegistration = registrationRepository.save(registrationToUpdate);
            RegistrationDto responseDto = RegistrationMapper.mapToRegistrationDto(updatedRegistration);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete Registration by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Registration successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Registration not found")
    })
    @DeleteMapping("/registration/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        if (registrationRepository.existsById(id)) {
            registrationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
