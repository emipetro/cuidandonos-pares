package usuarios;

import extras.Sexo;
import lombok.Getter;
import lombok.Setter;
import trayecto.Trayecto;

import java.util.List;

public class Cuidador {
    @Setter @Getter private String nombreCompleto;
    @Setter @Getter private String direccion;
    @Setter @Getter private Integer edad;
    @Setter @Getter private Sexo sexo;
    @Setter @Getter private String numeroDeTelefono;
    @Getter private List<Trayecto> trayectosRealizados = null;
    @Getter @Setter private Trayecto trayectoAsociado = null;

    public void recibirNotificacion(String notificacion){
        //RECIBIR NOTIFICACION
    }

    public boolean quiereHacerTrayecto(Trayecto trayecto){
        // Tomar decision
        return true;
    }

}
