package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.domain.Chamado;
import br.com.mayrcon.helpdesk.repositories.ChamadoRepository;
import br.com.mayrcon.helpdesk.services.exceptions.ObjectnotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    final private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new ObjectnotFoundException("Chamado não encontrado!"));
    }
}
