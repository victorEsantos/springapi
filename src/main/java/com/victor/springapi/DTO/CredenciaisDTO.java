package com.victor.springapi.DTO;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String senha;
}
