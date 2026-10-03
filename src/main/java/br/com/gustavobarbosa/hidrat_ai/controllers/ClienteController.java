package br.com.gustavobarbosa.hidrat_ai.controllers;

import br.com.gustavobarbosa.hidrat_ai.dto.request.ClienteRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.ClienteResponse;
import br.com.gustavobarbosa.hidrat_ai.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(clienteService.buscarPorId(id));
    }

    @GetMapping("ativos")
    public ResponseEntity<List<ClienteResponse>> buscarAtivos() {
        return ResponseEntity.ok().body(clienteService.buscarAtivos());
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteRequest request) {
        return ResponseEntity.ok().body(clienteService.cadastrar(request));
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<ClienteResponse> desativar(@PathVariable UUID id) {
        return ResponseEntity.ok().body(clienteService.desativar(id));
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<ClienteResponse> editar(
            @RequestBody ClienteRequest request,
            @PathVariable UUID id) {
        return ResponseEntity.ok().body(clienteService.editar(request, id));
    }
}
