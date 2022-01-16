package ni.com.nicasource.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DisponibilidadMedicosDTO {
    public String nombre;
    public ArrayList<DisponibilidadDTO> disponibilidad;

}


