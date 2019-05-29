package com.jamal.dainvestment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * This is a Javadoc comment
 * DTO for transaction
 */

@Data
public class TrxDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("id_user")
    @NotNull
    private int idUser;

    @JsonProperty("nama_user")
    private String userNama;

    @JsonProperty("id_sbn")
    @NotNull
    private String idSbn;

    @JsonProperty("jumlah_beli")
    @NotNull
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

    @JsonProperty("created_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+7")
    private Timestamp createdTime;

}
