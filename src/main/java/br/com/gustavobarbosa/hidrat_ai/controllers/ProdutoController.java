package br.com.gustavobarbosa.hidrat_ai.controllers;

import br.com.gustavobarbosa.hidrat_ai.dto.request.ProdutoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.ProdutoResponse;
import br.com.gustavobarbosa.hidrat_ai.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoService.buscarPorId(id));
    }

    @GetMapping("ativos")
    public ResponseEntity<List<ProdutoResponse>> buscarAtivos() {
        return ResponseEntity.ok().body(produtoService.buscarAtivos());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@RequestBody ProdutoRequest request) {
        return ResponseEntity.ok().body(produtoService.cadastrar(request));
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<ProdutoResponse> desativar(@PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoService.desativar(id));
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<ProdutoResponse> editar(
            @RequestBody ProdutoRequest request,
            @PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoService.editar(request, id));
    }
}
