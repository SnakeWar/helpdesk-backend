package br.com.mayrcon.helpdesk.repositories;

import br.com.mayrcon.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    
}
