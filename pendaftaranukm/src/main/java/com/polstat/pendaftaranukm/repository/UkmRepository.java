package com.polstat.pendaftaranukm.repository;

import com.polstat.pendaftaranukm.dto.UkmDto;
import com.polstat.pendaftaranukm.entity.Ukm;
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

@RepositoryRestResource(collectionResourceRel = "ukm", path = "ukm")
public interface UkmRepository extends PagingAndSortingRepository<Ukm,Long>,CrudRepository<Ukm,Long>{
    @Operation(summary = "Find all UKM")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all UKM", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UkmDto.class))}),
        @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "No UKM found", content = @Content)
    })
    List<Ukm> findAll();
    
    @Operation(summary = "Find UKM by name")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of UKM", content = {
                @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = UkmDto.class))}),
            @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
        })
    List<Ukm> findByName(@Param("name") String name); 
}
