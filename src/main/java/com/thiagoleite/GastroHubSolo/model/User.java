package com.thiagoleite.GastroHubSolo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "O login é obrigatório")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,20}$",
            message = "Login deve conter apenas letras, números e os caracteres . _ -")
    @Column(unique = true)
    private String login;

    @NotBlank(message = "A senha é obrigatória")
    @Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "A senha deve conter pelo menos uma letra e um número")
    private String password;

    @Column(name = "last_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    @NotBlank(message = "O endereço é obrigatório")
    @Length(max = 200, message = "O endereço não pode ter mais que 200 caracteres")
    private String address;
}

