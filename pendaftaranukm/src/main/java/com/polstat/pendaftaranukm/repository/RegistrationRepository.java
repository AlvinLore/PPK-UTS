package com.polstat.pendaftaranukm.repository;

import com.polstat.pendaftaranukm.dto.RegistrationDto;
import com.polstat.pendaftaranukm.entity.Registration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "registration", path = "registration")
public interface RegistrationRepository extends PagingAndSortingRepository<Registration,Long>,CrudRepository<Registration,Long>{
    @Operation(summary = "Find all UKM registrations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all registrations", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    List<Registration> findAll();
    
    @Operation(summary = "Find Registration by User NIM")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Registrations by User NIM", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    List<Registration> findByUserNim(@Param("nim") String nim);
    
    @Operation(summary = "Find Registration by User email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Registrations by User email", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    List<Registration> findByUserEmail(@Param("email") String email);

    @Operation(summary = "Find Registration by UKM name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Registrations by UKM name", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationDto.class))}),
        @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    List<Registration> findByUkmName(@Param("name") String name);
}
