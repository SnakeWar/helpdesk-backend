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
import java.time.LocalDate;
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

    public Chamado update(Integer id, @Valid ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado chamado = findById(id);
        chamado = newChamado(chamadoDTO);
        return chamadoRepository.save(chamado);
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }

        if (chamadoDTO.getStatus().equals(2) && chamadoDTO.getDataFechamento() == null) {
            chamado.setDataFechamento(LocalDate.now());
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
