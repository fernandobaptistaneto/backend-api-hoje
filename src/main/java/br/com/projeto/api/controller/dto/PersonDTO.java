package br.com.projeto.api.controller.dto;

import java.util.List;

import br.com.projeto.api.model.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDTO {
    
    private String name;
    private String type;
    private String cpf;
    private String email;
    private String phone;
    private String surname;
    private List<String> unitIds;
    private List<Unit> units;
}