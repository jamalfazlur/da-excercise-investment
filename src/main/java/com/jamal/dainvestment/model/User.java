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
    @Column(name = "user_id")
    private int userId;

    @JsonProperty("nama")
    @Column(name = "user_nama", nullable = false)
    private String userNama;

    @JsonProperty("alamat")
    @Column(name = "user_alamat")
    private String userAlamat;

    @JsonProperty("saldo")
    @Column(name = "user_saldo")
    private int userSaldo;

}
