package com.jamal.dainvestment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * This is a Javadoc comment
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int userId;
    @JsonProperty("nama")
    private String userNama;
    @JsonProperty("alamat")
    private String userAlamat;
    @JsonProperty("saldo")
    private int userSaldo;

}
