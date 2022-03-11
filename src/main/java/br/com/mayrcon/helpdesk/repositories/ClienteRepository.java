package br.com.mayrcon.helpdesk.repositories;

import br.com.mayrcon.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
