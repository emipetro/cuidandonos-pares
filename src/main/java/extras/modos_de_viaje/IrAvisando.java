package extras.modos_de_viaje;

import trayecto.Tramo;
import trayecto.Trayecto;

public class IrAvisando implements ModoDeViaje{
    @Override
    public float demoraSegunModo(Trayecto trayecto) {
        float demoraTotalTrayectos = 0;
        for(Tramo unTramo : trayecto.getTramos()){
            demoraTotalTrayectos+= unTramo.calcularDemora();
        }

        return demoraTotalTrayectos;
    }
}
