package med.voli.medapi.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {
        ExcecaoDTO excecaoDTO = new ExcecaoDTO("Usuário/Senha inválidos.", "401");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(excecaoDTO);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        ExcecaoDTO excecaoDTO = new ExcecaoDTO("Falha na autenticação.", "401");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(excecaoDTO);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {
        ExcecaoDTO excecaoDTO = new ExcecaoDTO("Acesso negado.", "403");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(excecaoDTO);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
