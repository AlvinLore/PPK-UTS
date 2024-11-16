package com.polstat.pendaftaranukm.auth;

import com.polstat.pendaftaranukm.service.UserService;
import com.polstat.pendaftaranukm.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    JwtUtil jwtUtil;
    
    @Autowired
    UserService userService;
    
    @Operation(summary = "User login to get access token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                description = "Email and access token", 
                content = {@Content(mediaType = "application/json", 
                        schema = @Schema(implementation = AuthResponse.class))}), 
        @ApiResponse(responseCode = "401", 
                description = "Invalid credentials", 
                content = @Content)})

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String accessToken = jwtUtil.generateAccessToken(authentication);
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @Operation(summary = "Register for new User.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", 
                description = "User Created", 
                content = {@Content(mediaType = "application/json", 
                        schema = @Schema(implementation = UserDto.class))}), 
        @ApiResponse(responseCode = "401", 
                description = "Invalid credentials", 
                content = @Content)})
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto request) {
        UserDto user = userService.createUser(request);
        return ResponseEntity.ok().body(user);
    }
    
    @Operation(summary = "Get user profile.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                description = "User profile data", 
                content = {@Content(mediaType = "application/json", 
                        schema = @Schema(implementation = UserDto.class))}),
        @ApiResponse(responseCode = "401", 
                description = "Unauthorized", 
                content = @Content)})
    
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(Authentication authentication) {
        String email = authentication.getName(); // Email dari JWT
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }
    
    @Operation(summary = "Edit user profile.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                description = "Profile updated successfully", 
                content = @Content),
        @ApiResponse(responseCode = "401", 
                description = "Unauthorized", 
                content = @Content)})
    
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid UserDto userDto, Authentication authentication) {
        String email = authentication.getName();
        userService.updateUserProfile(email, userDto);
        return ResponseEntity.ok("Profile updated successfully");
    }
    
    @Operation(summary = "Change user password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Password changed successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect old password",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content)})
    
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest request, Authentication authentication) {
        String email = authentication.getName();
        userService.changePassword(email, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }
    
    @Operation(summary = "Delete user account.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                description = "Account deleted successfully", 
                content = @Content),
        @ApiResponse(responseCode = "401", 
                description = "Unauthorized", 
                content = @Content)})
    
    @DeleteMapping("/profile")
    public ResponseEntity<?> deleteAccount(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteUser(email);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
