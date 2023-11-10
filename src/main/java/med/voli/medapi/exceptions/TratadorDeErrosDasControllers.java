package med.voli.medapi.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrosDasControllers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarEntidadeNaoEncontrada() {
        ExcecaoDTO excecaoDTO = new ExcecaoDTO("Médico/Paciente não encontrado.", "500");
        return ResponseEntity.badRequest().body(excecaoDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarArgumentosDaRequisicaoInvalidos(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarInconsistenciaDeDadosNaRequisicao(DataIntegrityViolationException exception) {
        ExcecaoDTO excecaoDTO = new ExcecaoDTO("Médico/Paciente já cadastrado.", "400");
        return ResponseEntity.badRequest().body(excecaoDTO);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
