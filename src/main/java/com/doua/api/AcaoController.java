package com.doua.api;

import com.doua.domain.acao.Acao;
import com.doua.domain.acao.AcaoService;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/acoes")
public class AcaoController {
    //injecao de dependencia
    @Autowired
    private AcaoService service;


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Acao>> get() {
        return new ResponseEntity<>(service.getAcoes(), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Acao acao) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (acaoInvalida(acao)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            Acao post = service.save(acao);
            if (post == null) {
                map.put(Strings.ERRO, Strings.ERRO_INCLUIR_ACAO);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("id", post.getId().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_ACAO);
                statusResponse = new ResponseEntity<>(map, HttpStatus.OK);
            }
        }
        return statusResponse;
    }


    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {
        HashMap<String, String> map = new HashMap<>();
        HttpStatus statusResponse;

        service.delete(id);

        map.put(Strings.STATUS, Strings.SUCESSO_EXCLUSAO_ACAO);
        statusResponse = HttpStatus.OK;

        return new ResponseEntity<>(map, statusResponse);
    }

    private boolean acaoInvalida(Acao acao) {
        return acao.getDescricao() == null || acao.getDescricao().equals("") ||
         acao.getTitulo() == null || acao.getTitulo().equals("") ||
                acao.getLocalizacao() == null;
    }
}
