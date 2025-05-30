package com.thiagoleite.GastroHubSolo.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthOutput {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;

    public AuthOutput(String token, Long id, String name, String email) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
