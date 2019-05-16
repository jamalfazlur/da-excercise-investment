package com.jamal.dainvestment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Entity untuk table Referensi Investasi
 */

@Data
@Entity
public class Investment {

    @Id
    @JsonProperty("id_sbn")
    @Column(nullable = false, updatable = false)
    private String idSbn;

    @JsonProperty("nama_sbn")
    private String namaSbn;

    @JsonProperty("harga_satuan")
    private int hargaSatuan;

    @JsonProperty("imbalan")
    private double imbalan;

    @JsonProperty("pajak")
    private double pajak;
}
