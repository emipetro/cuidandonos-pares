package extras.modos_de_viaje;

import lombok.Getter;
import lombok.Setter;
import trayecto.Trayecto;

import java.util.List;

public class DetenerseNMinutos implements ModoDeViaje{
    @Setter @Getter private Integer minutosDetenido;

    public DetenerseNMinutos(Integer minutosDetenido) {
        this.minutosDetenido = minutosDetenido;
    }

    @Override
    public float demoraSegunModo(List<Trayecto> trayectos) {
        //Demora sólo por las detenciones, se calcula con trayectos.size()-1 * minutosDetenido por parada
        //trayectos.size()-1 representa la cantidad de paradas totales
        int demoraPorDetenciones = (trayectos.size()-1)*minutosDetenido;

        //Demora sólo de los trayectos, sin contar detenciones
        float demoraTotalTrayectos = 0;
        for(Trayecto unTrayecto : trayectos){
            demoraTotalTrayectos+= unTrayecto.calcularDemora();
        }

        return demoraPorDetenciones+demoraTotalTrayectos;
    }
}
