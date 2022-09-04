package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.domain.Pessoa;
import br.com.mayrcon.helpdesk.domain.Tecnico;
import br.com.mayrcon.helpdesk.domain.dtos.TecnicoDTO;
import br.com.mayrcon.helpdesk.repositories.PessoaRepository;
import br.com.mayrcon.helpdesk.repositories.TecnicoRepository;
import br.com.mayrcon.helpdesk.services.exceptions.DataIntegrationViolationException;
import br.com.mayrcon.helpdesk.services.exceptions.ObjectnotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    private final PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Técnico não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrationViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrationViolationException("Email já cadastrado no sistema!");
        }
    }
}
