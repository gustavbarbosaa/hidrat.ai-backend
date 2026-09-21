package br.com.gustavobarbosa.hidrat_ai.controllers;

import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EstabelecimentoResponse;
import br.com.gustavobarbosa.hidrat_ai.services.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {
    private final EstabelecimentoService estabelecimentoService;

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoResponse> buscaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(estabelecimentoService.buscarPorId(id));
    }

    @GetMapping("ativos")
    public ResponseEntity<List<EstabelecimentoResponse>> buscarAtivos() {
        return ResponseEntity.ok().body(estabelecimentoService.buscarAtivos());
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoResponse> cadastrar(@RequestBody EstabelecimentoRequest request) {
        return ResponseEntity.ok().body(estabelecimentoService.cadastrar(request));
    }
}
