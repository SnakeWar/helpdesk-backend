package br.com.mayrcon.helpdesk.resources;

import br.com.mayrcon.helpdesk.domain.Chamado;
import br.com.mayrcon.helpdesk.domain.dtos.ChamadoDTO;
import br.com.mayrcon.helpdesk.services.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("chamados")
public class ChamadoResource {

    final private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        final var chamadoObj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamadoObj));
    }

    @GetMapping
    ResponseEntity<List<ChamadoDTO>> findAll() {
        final var chamados = chamadoService.findAll();
        return ResponseEntity.ok().body(chamados);
    }

    @PostMapping
    ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(chamado.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.update(id, chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
