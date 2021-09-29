package com.dh.clinicaodontologica.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Turnos")
@Getter @Setter
public class Turno implements Serializable {
    @Id
    @SequenceGenerator(name = "turno sequence", sequenceName = "turno_sequence")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_paciente",referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_odontologo",referencedColumnName = "id")
    private Odontologo odontologo;
    private Date fecha;

    public Turno(){}

    public Turno(Paciente paciente, Odontologo odontologo, Date fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

}