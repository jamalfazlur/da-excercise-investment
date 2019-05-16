package com.jamal.dainvestment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;

/**
 * Entity untuk table Transaksi
 */

@Data
@Entity
public class Trx {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("id_user")
    @Column(nullable = false)
    private int idUser;

    @JsonProperty("nama_user")
    private String userNama;

    @JsonProperty("id_sbn")
    @Column(nullable = false)
    private String idSbn;

    @JsonProperty("jumlah_beli")
    @Column(nullable = false)
    private int jumlahBeli;

    @JsonProperty("total_bayar")
    private int totalBayar;

    @JsonProperty("imbalan")
    private int imbalan;

    @JsonProperty("pajak")
    private int pajak;

    @JsonProperty("imbalan_nett")
    private int imbalanNett;

    @JsonProperty("total_imbalan")
    private int totalImbalan;
}
