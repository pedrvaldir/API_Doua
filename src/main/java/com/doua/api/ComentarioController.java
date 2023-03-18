package com.doua.api;

import com.doua.domain.comentario.Comentario;
import com.doua.domain.comentario.ComentarioService;
import com.doua.domain.tutorial.Tutorial;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService service;


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Comentario>> get() {
        return new ResponseEntity<>(service.getComentarios(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/acao/{id}")
    public ResponseEntity<HashMap<String, String>> get(@PathVariable("id") Long id) {

        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        List<Comentario> comentarios = service.getComentariosPorAcao(id);

        if (comentarios.isEmpty())
        {
            map.put(Strings.ERRO, Strings.ERRO_CLIENTE_NAO_ENCONTRADO);
            statusResponse =  new ResponseEntity(map, HttpStatus.OK);
        }else {
            statusResponse =  new ResponseEntity(comentarios, HttpStatus.OK);
        }
        return statusResponse;
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Comentario comentario) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (comentarioInvalido(comentario)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            Comentario post = service.save(comentario);
            if (post == null) {
                map.put(Strings.ERRO, Strings.ERRO_INCLUIR_COMENTARIO);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("id", post.getId().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_COMENTARIO);
                statusResponse = new ResponseEntity<>(map, HttpStatus.OK);
            }
        }
        return statusResponse;
    }

    private boolean comentarioInvalido(Comentario comentario) {
        return comentario.getDescricao().equals("") || comentario.getDescricao() == null;
    }
}
