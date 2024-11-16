package com.polstat.pendaftaranukm.repository;

import com.polstat.pendaftaranukm.entity.Leader;
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

@RepositoryRestResource(collectionResourceRel = "leader", path = "leader")
public interface LeaderRepository extends PagingAndSortingRepository<Leader,Long>,CrudRepository<Leader,Long>{
    
    @Operation(summary = "Find Leader UKM by NIM")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Leader", content = {
                @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Leader.class))}),
            @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
        })
    List<Leader> findByNim(@Param("nim") String nim);
    
    @Operation(summary = "Find Leader UKM by name")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Leader", content = {
                @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Leader.class))}),
            @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
        })
    List<Leader> findByName(@Param("name") String name);
}
