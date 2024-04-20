package usuarios;

import lombok.Getter;
import lombok.Setter;
import trayecto.Trayecto;

public class Cuidador {
    @Getter @Setter private Trayecto trayectoAsociado = null;

    public void recibirNotificacion(String notificacion){
        //RECIBIR NOTIFICACION
    }

    public boolean quiereHacerTrayecto(){
        // Tomar decision
        return true;
    }

}
