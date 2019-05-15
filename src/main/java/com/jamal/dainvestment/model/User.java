package com.jamal.dainvestment.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Entity untuk table User
 */

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(nullable = false, updatable = false)
    private int userId;

    @JsonProperty("nama")
    @Column(nullable = false)
    private String userNama;

    @JsonProperty("alamat")
    private String userAlamat;

    @JsonProperty("saldo")
    private int userSaldo;

}
