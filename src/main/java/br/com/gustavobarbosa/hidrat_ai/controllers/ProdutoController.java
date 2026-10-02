package br.com.gustavobarbosa.hidrat_ai.controllers;

import br.com.gustavobarbosa.hidrat_ai.dto.ClienteDTO;
import br.com.gustavobarbosa.hidrat_ai.dto.ProdutoDTO;
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
    public ResponseEntity<ProdutoDTO> buscaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoService.buscarPorId(id));
    }

    @GetMapping("ativos")
    public ResponseEntity<List<ProdutoDTO>> buscarAtivos() {
        return ResponseEntity.ok().body(produtoService.buscarAtivos());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoDTO request) {
        return ResponseEntity.ok().body(produtoService.cadastrar(request));
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<ProdutoDTO> desativar(@PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoService.desativar(id));
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<ProdutoDTO> editar(
            @RequestBody ProdutoDTO request,
            @PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoService.editar(request, id));
    }
}
