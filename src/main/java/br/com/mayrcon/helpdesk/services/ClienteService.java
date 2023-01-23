package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.domain.Cliente;
import br.com.mayrcon.helpdesk.domain.Pessoa;
import br.com.mayrcon.helpdesk.domain.dtos.ClienteDTO;
import br.com.mayrcon.helpdesk.repositories.ClienteRepository;
import br.com.mayrcon.helpdesk.repositories.PessoaRepository;
import br.com.mayrcon.helpdesk.services.exceptions.DataIntegrationViolationException;
import br.com.mayrcon.helpdesk.services.exceptions.ObjectnotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PessoaRepository pessoaRepository;

    private final BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ObjectnotFoundException("Cliente não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        validaPorCpfEEmail(clienteDTO);
        Cliente newObj = new Cliente(clienteDTO);
        return clienteRepository.save(newObj);
    }

    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente cliente = findById(id);
        validaPorCpfEEmail(clienteDTO);
        cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);

        if(cliente.getChamados().size() > 0) {
            throw new DataIntegrationViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
