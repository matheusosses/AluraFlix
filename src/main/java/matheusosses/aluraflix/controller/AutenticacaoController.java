package matheusosses.aluraflix.controller;

import jakarta.validation.Valid;
import matheusosses.aluraflix.dto.security.DadosAutenticacao;
import matheusosses.aluraflix.dto.security.TokenDto;
import matheusosses.aluraflix.model.Usuario;
import matheusosses.aluraflix.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService service;

    public AutenticacaoController(AuthenticationManager manager, TokenService service) {
        this.manager = manager;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid DadosAutenticacao dados){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        Authentication authentication = manager.authenticate(token);

        String tokenJWT = service.getToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }
}
