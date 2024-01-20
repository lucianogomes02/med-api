package med.voli.medapi.controllers;

import jakarta.validation.Valid;
import med.voli.medapi.infra.security.TokenService;
import med.voli.medapi.domain.usuario.Usuario;
import med.voli.medapi.domain.usuario.dto.Autenticacao;
import med.voli.medapi.domain.usuario.dto.TokenJWT;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid Autenticacao autenticacao) {
        var token = new UsernamePasswordAuthenticationToken(autenticacao.login(), autenticacao.senha());
        var auth = authenticationManager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}
