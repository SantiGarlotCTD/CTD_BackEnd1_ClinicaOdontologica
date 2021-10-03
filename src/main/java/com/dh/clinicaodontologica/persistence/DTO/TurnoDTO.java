package com.dh.clinicaodontologica.persistence.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class TurnoDTO {
    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private Date fecha;

    public TurnoDTO(PacienteDTO paciente, OdontologoDTO odontologo, Date fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "TrunoDTO{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
