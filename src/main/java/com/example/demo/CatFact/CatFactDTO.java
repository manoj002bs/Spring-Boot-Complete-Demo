package com.example.demo.CatFact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatFactDTO {
    private String catFact;

    public CatFactDTO(CatFact catFact) {
        this.catFact = catFact.getFact();
    }
}
