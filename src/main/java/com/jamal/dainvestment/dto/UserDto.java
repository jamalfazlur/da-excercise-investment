package com.jamal.dainvestment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jamal.dainvestment.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * This is a Javadoc comment
 * DTO for User Entity
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int userId;

    @JsonProperty("nama")
    @NotNull(message = "Kolom Nama Wajib Diisi")
    @NotEmpty
    @Length(min = 3, message = "Input Nama Minimal 3 Karakter")
    @Length(max = 30, message = "Input Nama Maksimal 30 Karakter")
    private String userNama;


    @JsonProperty("alamat")
    @Length(min = 3, message = "Input Alamat Minimal 3 Karakter")
    private String userAlamat;

    @JsonProperty("saldo")
    private int userSaldo;

    /**
     * Constructor for UserDto
     * @param user user obj
     */
    public UserDto(User user) {
        this.userNama = user.getUserNama();
        this.userAlamat = user.getUserAlamat();
        this.userSaldo = user.getUserSaldo();
    }
}
