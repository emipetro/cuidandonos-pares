package extras.modos_de_viaje;

import trayecto.Trayecto;

import java.util.List;

public class IrAvisando implements ModoDeViaje{
    @Override
    public float demoraSegunModo(List<Trayecto> trayectos) {
        float demoraTotalTrayectos = 0;
        for(Trayecto unTrayecto : trayectos){
            demoraTotalTrayectos+= unTrayecto.calcularDemora();
        }

        return demoraTotalTrayectos;
    }
}
