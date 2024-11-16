package com.polstat.pendaftaranukm.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaderDto {
    private Long id;
    
    @NotEmpty(message = "NIM ketua wajib diisi")
    private String nim;
    
    @NotEmpty(message = "nama ketua wajib diisi")
    private String name;
    
    @NotEmpty(message = "kelas ketua wajib diisi")
    private String leaderClass;
}
