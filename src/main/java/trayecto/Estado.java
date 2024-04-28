package trayecto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Estado {
    @Setter @Getter private PosibleEstado estado;
    @Setter @Getter private LocalDateTime tiempo;

    public Estado(PosibleEstado estado, LocalDateTime tiempo) {
        this.estado = estado;
        this.tiempo = tiempo;
    }
}
