package com.jamal.dainvestment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is a Javadoc comment
 * DTO for invest entity reference
 */

@Data
public class InvestDto {
    @Id
    @JsonProperty("id_sbn")
    @NotNull
    private String idSbn;

    @JsonProperty("nama_sbn")
    private String namaSbn;

    @JsonProperty("harga_satuan")
    @NotNull
    private int hargaSatuan;

    @JsonProperty("imbalan")
    @NotNull
    private double imbalan;

    @JsonProperty("pajak")
    private double pajak;
}
