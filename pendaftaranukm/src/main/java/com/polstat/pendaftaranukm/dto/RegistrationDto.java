package com.polstat.pendaftaranukm.dto;

import com.polstat.pendaftaranukm.entity.Status;
import com.polstat.pendaftaranukm.entity.Ukm;
import com.polstat.pendaftaranukm.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private Long id;
    
    @NotNull(message = "User wajib diisi")
    private User user;
    
    @NotNull(message = "nama ukm wajib diisi")
    private Ukm ukm;
    
    @NotEmpty(message = "User wajib diisi")
    private Date registrationDate;
    
    @Enumerated(EnumType.STRING)
    private Status status;
}
