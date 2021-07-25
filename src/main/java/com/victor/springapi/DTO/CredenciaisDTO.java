package com.victor.springapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String senha;
}
