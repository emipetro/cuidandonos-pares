package extras.modos_de_viaje;

import trayecto.Trayecto;
import trayecto.TrayectoExtendido;

import java.util.List;

public class IrAvisando implements ModoDeViaje{
    @Override
    public float demoraSegunModo(TrayectoExtendido trayectoExtendido) {
        float demoraTotalTrayectos = 0;
        for(Trayecto unTrayecto : trayectoExtendido.getTrayectos()){
            demoraTotalTrayectos+= unTrayecto.calcularDemora();
        }

        return demoraTotalTrayectos;
    }
}
