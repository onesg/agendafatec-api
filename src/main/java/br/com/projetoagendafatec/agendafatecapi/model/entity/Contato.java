package br.com.projetoagendafatec.agendafatecapi.model.entity;

import javax.persistence.*;

import lombok.*;

import static javax.persistence.GenerationType.IDENTITY;

/* a classe se tornará uma entidade */
@Entity

/* encapsulamento */
@Getter @Setter

/* construtor */
@NoArgsConstructor

public class Contato {

    /* campo ID */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /* campo nome */
    @Column(length = 350, nullable = false)
    private String nome;

    /* campo e-mail */
    @Column(length = 300, nullable = false)
    private String email;

    /* campo telefone */
    @Column(length = 15, nullable = false)
    private String telefone;

    /* campo favorito */
    @Column
    private Boolean favorito;

    /* (campo) foto */
    @Column
    @Lob
    private byte[] foto;


}
