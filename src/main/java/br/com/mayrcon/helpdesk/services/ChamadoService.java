package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.domain.Chamado;
import br.com.mayrcon.helpdesk.domain.Cliente;
import br.com.mayrcon.helpdesk.domain.Tecnico;
import br.com.mayrcon.helpdesk.domain.dtos.ChamadoDTO;
import br.com.mayrcon.helpdesk.domain.enums.Prioridade;
import br.com.mayrcon.helpdesk.domain.enums.Status;
import br.com.mayrcon.helpdesk.repositories.ChamadoRepository;
import br.com.mayrcon.helpdesk.services.exceptions.ObjectnotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    final private ChamadoRepository chamadoRepository;

    final private TecnicoService tecnicoService;

    final private ClienteService clienteService;

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

    public Chamado create(@Valid ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }
        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        return chamado;
    }
}
