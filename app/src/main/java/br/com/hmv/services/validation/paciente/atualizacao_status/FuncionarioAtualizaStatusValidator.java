package br.com.hmv.services.validation.paciente.atualizacao_status;

import br.com.hmv.dtos.request.FuncionarioAtualizaStatusRequestDTO;
import br.com.hmv.exceptions.FieldMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class FuncionarioAtualizaStatusValidator implements ConstraintValidator<FuncionarioAtualizaStatusValid, FuncionarioAtualizaStatusRequestDTO> {


    @Override
    public void initialize(FuncionarioAtualizaStatusValid ann) {
    }

    @Override
    public boolean isValid(FuncionarioAtualizaStatusRequestDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        //Retorno booleano (true/false) de acordo com a resposta da pergunta se a lista esta preenchida ou nao
        return list.isEmpty();
    }
}
