package br.com.gustavobarbosa.hidrat_ai.controllers;

import br.com.gustavobarbosa.hidrat_ai.dto.request.EntregaRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EntregaResponse;
import br.com.gustavobarbosa.hidrat_ai.services.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entregas")
@RequiredArgsConstructor
public class EntregaController {
    private final EntregaService entregaService;

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponse> buscaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(entregaService.buscarPorId(id));
    }

    @GetMapping("ativos")
    public ResponseEntity<List<EntregaResponse>> buscarAtivos() {
        return ResponseEntity.ok().body(entregaService.buscarAtivos());
    }

    @PostMapping
    public ResponseEntity<EntregaResponse> cadastrar(@RequestBody EntregaRequest request) {
        return ResponseEntity.ok().body(entregaService.cadastrar(request));
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<EntregaResponse> desativar(@PathVariable UUID id) {
        return ResponseEntity.ok().body(entregaService.desativar(id));
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<EntregaResponse> editar(
            @RequestBody EntregaRequest request,
            @PathVariable UUID id) {
        return ResponseEntity.ok().body(entregaService.editar(request, id));
    }
}
