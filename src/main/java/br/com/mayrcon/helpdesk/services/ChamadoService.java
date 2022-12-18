package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.domain.Chamado;
import br.com.mayrcon.helpdesk.domain.dtos.ChamadoDTO;
import br.com.mayrcon.helpdesk.repositories.ChamadoRepository;
import br.com.mayrcon.helpdesk.services.exceptions.ObjectnotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    final private ChamadoRepository chamadoRepository;
    public List<ChamadoDTO> findAll() {
        return chamadoRepository
                .findAll()
                .stream()
                .map(ChamadoDTO::new)
                .collect(Collectors.toList());
    };

    public Chamado findById(Integer id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new ObjectnotFoundException("Chamado não encontrado!"));
    }
}
