package med.voli.medapi.usuario.controller;

import jakarta.validation.Valid;
import med.voli.medapi.usuario.dto.Autenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid Autenticacao autenticacao) {
        var token = new UsernamePasswordAuthenticationToken(autenticacao.login(), autenticacao.senha());
        var auth = authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
