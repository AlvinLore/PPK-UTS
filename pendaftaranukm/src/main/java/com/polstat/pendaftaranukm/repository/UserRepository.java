package com.polstat.pendaftaranukm.repository;


import com.polstat.pendaftaranukm.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Operation(summary = "Find user by email")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users", content = {
                @Content(mediaType = "application/json", 
                        schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Access Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
        })
    public User findByEmail(String email);
}
