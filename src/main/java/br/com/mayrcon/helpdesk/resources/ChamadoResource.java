package br.com.mayrcon.helpdesk.resources;

import br.com.mayrcon.helpdesk.domain.dtos.ChamadoDTO;
import br.com.mayrcon.helpdesk.services.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
