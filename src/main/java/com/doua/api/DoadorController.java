package com.doua.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.doua.domain.DoadorService;
import com.doua.domain.pedido.PedidoService;
import com.doua.domain.validadores.CPF;
import com.doua.utils.Strings;
import com.doua.domain.Doador;

@RestController
@RequestMapping("/api/v1/doadores")
public class DoadorController {

    @Autowired
    private DoadorService service;

    @Autowired
    private PedidoService pedidoservice;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Doador>> get() {
        return new ResponseEntity<>(service.getClientes(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Doador> get(@PathVariable("id") Long id) {
        Optional<Doador> doador = service.getDoadorPorId(id);
        ResponseEntity statusResponse;

        if (doador.isPresent()) {
            statusResponse = ResponseEntity.ok(doador.get());
        } else {
            statusResponse = ResponseEntity.notFound().build();
        }

        return statusResponse;
    }

    @CrossOrigin
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<Doador>> get(@PathVariable("cpf") String cpf) {
        List<Doador> doador = service.getDoadorPorCpf(cpf);
        ResponseEntity statusResponse;

        if (doador.isEmpty()) {
            statusResponse = ResponseEntity.noContent().build();
        } else {
            statusResponse = ResponseEntity.ok(doador);
        }
        return statusResponse;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Doador doador) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (doadorInvalido(doador)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        }
        if (CPF.valido(CPF.removePontuacao(doador.getCpf())) != true) {
            map.put(Strings.ERRO, Strings.ERRO_CPF_INVALIDO);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else {
            Doador postDoador = service.save(doador);
            if (postDoador == null) {
                map.put(Strings.ERRO, Strings.ERRO_CPF_EXISTENTE);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("idCliente", postDoador.getId().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_CRIADOR);
                statusResponse = new ResponseEntity<>(map, HttpStatus.OK);
            }
        }
        return statusResponse;
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> put(@PathVariable("id") Long id, @RequestBody Doador doador) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (doadorInvalido(doador)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else {
            Doador cli = service.update(doador, id);

            map.put("idCliente", cli.getId().toString());
            map.put(Strings.STATUS, Strings.SUCESSO_ATUALIZAR_CLIENTE);
            statusResponse = new ResponseEntity<>(map, HttpStatus.OK);
        }
        return statusResponse;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {
        HashMap<String, String> map = new HashMap<>();
        HttpStatus statusResponse;

        service.delete(id);
        map.put(Strings.STATUS, Strings.SUCESSO_EXCLUSAO_CRIADOR);
        statusResponse = HttpStatus.OK;

        return new ResponseEntity<>(map, statusResponse);
    }

    private boolean doadorInvalido(Doador doador) {
        return doador.getNome() == "" | doador.getSobrenome() == "" | doador.getCpf() == ""
                | doador.getNome() == null | doador.getSobrenome() == null | doador.getCpf() == null;
    }
}
