package com.doua.api;

import com.doua.domain.tutorial.Tutorial;
import com.doua.domain.tutorial.TutorialService;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/tutoriais")
public class TutorialController {

    @Autowired
    private TutorialService service;


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Tutorial>> get() {
        return new ResponseEntity<>(service.getTutoriais(), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Tutorial tutorial) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (tutorialInvalido(tutorial)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            Tutorial post = service.save(tutorial);
            if (post == null) {
                map.put(Strings.ERRO, Strings.ERRO_CPF_EXISTENTE);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("id", post.getId().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_TUTORIAL);
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

    private boolean tutorialInvalido(Tutorial tutorial) {
        return tutorial.getTitulo().equals("") | tutorial.getDescricao().equals("") | tutorial.getIcon().equals("")
                | tutorial.getTitulo() == null | tutorial.getDescricao() == null | tutorial.getIcon() == null;
    }
}
