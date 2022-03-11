package br.com.mayrcon.helpdesk.repositories;

import br.com.mayrcon.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
