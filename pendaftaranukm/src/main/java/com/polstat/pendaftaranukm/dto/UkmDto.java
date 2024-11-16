package com.polstat.pendaftaranukm.dto;

import com.polstat.pendaftaranukm.entity.Leader;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UkmDto {
    private Long id;
    
    @NotEmpty(message = "nama ukm wajib diisi")
    private String name;
    
    @NotNull(message = "ketua ukm wajib diisi")
    private Leader leader;
    
    @NotEmpty(message = "deskripsi ukm wajib diisi")
    private String description;
}
