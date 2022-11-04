package com.doua.api;

import com.doua.domain.localizacao.Localizacao;
import com.doua.domain.localizacao.LocalizacaoService;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Localizacao>> get() {
        return new ResponseEntity<>(service.getLocalizacoes(), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Localizacao localizacao) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (localizacaoInvalida(localizacao)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            Localizacao post = service.save(localizacao);
            if (post == null) {
                map.put(Strings.ERRO, Strings.ERRO_INCLUIR_LOCALIZACAO);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("id", post.getId().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_LOCALIZACAO);
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

        map.put(Strings.STATUS, Strings.SUCESSO_EXCLUSAO_TUTORIAL);
        statusResponse = HttpStatus.OK;

        return new ResponseEntity<>(map, statusResponse);
    }

    private boolean localizacaoInvalida(Localizacao localizacao) {
        return localizacao.getLatitude() == null || localizacao.getLongitude() == null;
    }
}
