package com.doua.api;

import com.doua.domain.localizacao.Localizacao;
import com.doua.domain.tipoacao.TipoAcao;
import com.doua.domain.tipoacao.TipoAcaoService;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/tipoacoes")
public class TipoAcaoController {

    @Autowired
    private TipoAcaoService service;


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<TipoAcao>> get() {
        return new ResponseEntity<>(service.getTipoAcoes(), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody TipoAcao tipoAcao) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (tipoAcaoInvalida(tipoAcao)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            TipoAcao post = service.save(tipoAcao);
            if (post == null) {
                map.put(Strings.ERRO, Strings.ERRO_INCLUIR_TIPO_ACAO);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("id", post.getId().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_TIPO_ACAO);
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

        map.put(Strings.STATUS, Strings.SUCESSO_EXCLUSAO_TIPO_ACAO);
        statusResponse = HttpStatus.OK;

        return new ResponseEntity<>(map, statusResponse);
    }

    private boolean tipoAcaoInvalida(TipoAcao tipoAcao) {
        return tipoAcao.getTipo() == null || tipoAcao.getTipo().equals("");
    }
}
