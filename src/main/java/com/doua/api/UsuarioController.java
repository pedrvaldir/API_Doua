package com.doua.api;

import com.doua.domain.usuario.Usuario;
import com.doua.domain.usuario.UsuarioService;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

//	@Autowired
//	private PedidoService pedidoservice;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Usuario>> get() {
        return new ResponseEntity<>(service.getUsuarios(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.getUsuarioPorId(id);
        ResponseEntity statusResponse;

        if (usuario.isPresent()) {
            statusResponse = ResponseEntity.ok(usuario.get());
        } else {
            statusResponse = ResponseEntity.notFound().build();
        }

        return statusResponse;
    }

    @CrossOrigin
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Usuario>> get(@PathVariable("email") String email) {
        List<Usuario> usuario = service.getUsuarioPorEmail(email);
        ResponseEntity statusResponse;

        if (usuario.isEmpty()) {
            statusResponse = ResponseEntity.noContent().build();
        } else {
            statusResponse = ResponseEntity.ok(usuario);
        }
        return statusResponse;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Usuario usuario) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;

        if (usuarioInvalido(usuario)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            Usuario postUsuario = service.save(usuario);
            if (postUsuario == null) {
                map.put(Strings.ERRO, Strings.ERRO_CPF_EXISTENTE);
                statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            } else {
                map.put("email", postUsuario.getEmail().toString());
                map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_CLIENTE);
                statusResponse = new ResponseEntity<>(map, HttpStatus.OK);
            }
        }
        return statusResponse;
    }

//	@CrossOrigin
//	@PutMapping("/email}")
//	public ResponseEntity<HashMap<String, String>> put(@PathVariable("email") String email, @RequestBody Usuario usuario) {
//        HashMap<String, String> map = new HashMap<>();
//        ResponseEntity<HashMap<String, String>> statusResponse;
//
//        if (usuarioInvalido(usuario))
//        {
//			map.put(Strings.ERRO,Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
//			statusResponse =  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
//        }else {
//        	Doador cli = service.update(usuario, email);
//
//			map.put("idCliente",cli.getId().toString());
//			map.put(Strings.STATUS,Strings.SUCESSO_ATUALIZAR_CLIENTE);
//			statusResponse =  new ResponseEntity<>(map,HttpStatus.OK);
//        }
//        return statusResponse;
//	}

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {
        HashMap<String, String> map = new HashMap<>();
        HttpStatus statusResponse;

        service.delete(id);

        map.put(Strings.STATUS, Strings.SUCESSO_EXCLUSAO_CLIENTE);
        statusResponse = HttpStatus.OK;

        return new ResponseEntity<>(map, statusResponse);
    }

    private boolean usuarioInvalido(Usuario usuario) {
        return usuario.getNome() == "" | usuario.getEmail() == "" | usuario.getToken() == ""
                | usuario.getNome() == null | usuario.getEmail() == null | usuario.getToken() == null;
    }
}
