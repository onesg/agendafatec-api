package br.com.projetoagendafatec.agendafatecapi.model.entity;

import javax.persistence.*;


/* a classe se tornará uma entidade */
@Entity

public class Contato {

    /* campo ID */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /* campo nome */
    @Column(lenght = 350, nullable = false)
    private String nome;

    /* campo e-mail */
    @Column(lenght = 300, nullable = false)
    private String email;

    /* campo favorito */
    @Column
    private Boolean favorito;


}
