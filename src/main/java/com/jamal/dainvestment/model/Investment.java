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
    @Column(name = "harga_satuan", nullable = false)
    private int hargaSatuan;

    @JsonProperty("imbalan")
    @Column(name = "imbalan", nullable = false)
    private double imbalan;

    @JsonProperty("pajak")
    @Column(name = "pajak")
    private double pajak;
}
