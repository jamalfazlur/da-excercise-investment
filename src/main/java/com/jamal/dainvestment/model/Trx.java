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
    @Column(name = "id")
    private int id;

    @JsonProperty("id_user")
    @Column(name = "id_user", nullable = false)
    private int idUser;

    @JsonProperty("nama_user")
    @Column(name = "nama_user")
    private String userNama;

    @JsonProperty("id_sbn")
    @Column(name = "id_sbn", nullable = false)
    private String idSbn;

    @JsonProperty("jumlah_beli")
    @Column(name = "jumlah_beli", nullable = false)
    private int jumlahBeli;

    @JsonProperty("total_bayar")
    @Column(name = "total_bayar")
    private int totalBayar;

    @JsonProperty("imbalan")
    @Column(name="imbalan")
    private int imbalan;

    @JsonProperty("pajak")
    @Column(name = "pajak")
    private int pajak;

    @JsonProperty("imbalan_nett")
    @Column(name = "imbalan_nett")
    private int imbalanNett;

    @JsonProperty("total_imbalan")
    @Column(name = "total_imbalan")
    private int totalImbalan;
}
