package br.com.projeto.api.controller.dto;

import br.com.projeto.api.model.Unit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> unitsId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Unit> units;
}
