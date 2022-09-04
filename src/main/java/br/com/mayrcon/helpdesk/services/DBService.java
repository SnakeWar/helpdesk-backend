package br.com.mayrcon.helpdesk.services;

import br.com.mayrcon.helpdesk.domain.Chamado;
import br.com.mayrcon.helpdesk.domain.Cliente;
import br.com.mayrcon.helpdesk.domain.Tecnico;
import br.com.mayrcon.helpdesk.domain.enums.Perfil;
import br.com.mayrcon.helpdesk.domain.enums.Prioridade;
import br.com.mayrcon.helpdesk.domain.enums.Status;
import br.com.mayrcon.helpdesk.repositories.ChamadoRepository;
import br.com.mayrcon.helpdesk.repositories.ClienteRepository;
import br.com.mayrcon.helpdesk.repositories.TecnicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class DBService {

    final private TecnicoRepository tecnicoRepository;

    final private ClienteRepository clienteRepository;

    final private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
//        Tecnico tec1 = new Tecnico(null, "Micael Gustavo", "514.824.060-12", "micael@email.com", "123");
//        tec1.addPerfil(Perfil.ADMIN);
//
//        Cliente cli1 = new Cliente(null, "Dayane Priscila", "854.868.850-59", "dayane@email.com", "123");
//
//        Chamado c1 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 02", "Segundo Chamado", tec1, cli1);
//
//        tecnicoRepository.saveAll(Arrays.asList(tec1));
//        //tecnicoRepository.save(tec1);
//        clienteRepository.saveAll(Arrays.asList(cli1));
//        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
