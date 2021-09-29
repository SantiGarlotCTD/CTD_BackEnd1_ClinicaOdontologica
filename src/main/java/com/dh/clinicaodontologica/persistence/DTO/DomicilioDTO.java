package com.dh.clinicaodontologica.persistence.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class DomicilioDTO {
    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    @Override
    public String toString() {
        return "DomicilioDTO{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
