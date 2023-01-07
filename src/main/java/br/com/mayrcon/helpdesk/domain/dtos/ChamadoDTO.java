package br.com.mayrcon.helpdesk.domain.dtos;

import br.com.mayrcon.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @NotNull(message = "O campo PRIORIDADE é obrigatório")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS é obrigatório")
    private Integer status;
    @NotNull(message = "O campo TÍTULO é obrigatório")
    private String titulo;
    @NotNull(message = "O campo OBSERVAÇÕES é obrigatório")
    private String observacoes;

    @NotNull(message = "O campo TÉCNICO é obrigatório")
    private Integer tecnico;
    private String nomeTecnico;

    @NotNull(message = "O campo CLIENTE é obrigatório")
    private Integer cliente;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado chamado) {
        super();
        this.id = chamado.getId();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.cliente = chamado.getCliente().getId();
        this.nomeCliente = chamado.getCliente().getNome();
    }
}
