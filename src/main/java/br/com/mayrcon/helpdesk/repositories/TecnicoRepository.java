package br.com.mayrcon.helpdesk.repositories;

import br.com.mayrcon.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    
}
