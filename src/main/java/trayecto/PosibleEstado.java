package trayecto;

import lombok.Getter;
import lombok.Setter;

public class PosibleEstado {
    @Setter @Getter private String nombre;

    public PosibleEstado(String nombre) {
        this.nombre = nombre;
    }
}
