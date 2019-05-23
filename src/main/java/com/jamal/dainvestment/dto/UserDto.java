package com.jamal.dainvestment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is a Javadoc comment
 * DTO for User Entity
 */

@Data
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int userId;

    @JsonProperty("nama")
    @NotNull
    private String userNama;

    @JsonProperty("alamat")
    private String userAlamat;

    @JsonProperty("saldo")
    private int userSaldo;

}
