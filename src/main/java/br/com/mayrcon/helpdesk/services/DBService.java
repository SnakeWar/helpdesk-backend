package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.repositories.ChamadoRepository;
import br.com.mayrcon.helpdesk.repositories.ClienteRepository;
import br.com.mayrcon.helpdesk.repositories.TecnicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DBService {

    final private TecnicoRepository tecnicoRepository;

    final private ClienteRepository clienteRepository;

    final private ChamadoRepository chamadoRepository;

    final private BCryptPasswordEncoder encoder;

    public void instanciaDB() {

//        Tecnico tec1 = new Tecnico(null, "Micael Gustavo", "514.824.060-12", "micael@email.com", encoder.encode("123"));
//        tec1.addPerfil(Perfil.ADMIN);
//
//        Cliente cli1 = new Cliente(null, "Dayane Priscila", "854.868.850-59", "dayane@email.com", encoder.encode("123"));
//
//        Chamado c1 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 02", "Segundo Chamado", tec1, cli1);
//
//        tecnicoRepository.saveAll(Arrays.asList(tec1));
//        //tecnicoRepository.save(tec1);
//        clienteRepository.saveAll(Arrays.asList(cli1));
//        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
