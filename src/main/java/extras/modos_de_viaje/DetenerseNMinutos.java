package extras.modos_de_viaje;

import lombok.Getter;
import lombok.Setter;
import trayecto.Tramo;
import trayecto.Trayecto;

import java.util.List;

public class DetenerseNMinutos implements ModoDeViaje{
    @Setter @Getter private Integer minutosDetenido;

    public DetenerseNMinutos(Integer minutosDetenido) {
        this.minutosDetenido = minutosDetenido;
    }

    @Override
    public double demoraSegunModo(Trayecto trayecto) {
        //Demora sólo por las detenciones, se calcula con trayectos.size()-1 * minutosDetenido por parada
        //trayectos.size()-1 representa la cantidad de paradas totales
        int demoraPorDetenciones = (trayecto.getTramos().size()-1)*minutosDetenido;

        //Demora sólo de los trayectos, sin contar detenciones
        double demoraTotalTrayecto = 0;
        for(Tramo unTramo : trayecto.getTramos()){
            demoraTotalTrayecto+= unTramo.calcularDemoraTramo();
        }

        return demoraPorDetenciones+demoraTotalTrayecto;
    }

    @Override
    public void esperarTiempoDeDemora(Trayecto trayecto) {
        List<Tramo> tramos = trayecto.getTramos();
        int cantidadTramos = tramos.size();

        //Mientras no haya pasado el tiempo de la demora total del trayecto se bloquean las notificaciones
        for(int i=0; i<cantidadTramos ;i++){
            //Evaluo tramo por tramo la demora
            Tramo unTramo = tramos.get(i);

            //La demora por tramo será la demora normal + minutosDetenido
            for(int j=0; j<(unTramo.calcularDemoraTramo()+minutosDetenido) ;j++){
                //Mientras se ejecuta esto, el usuario puede presionar el botón de "Completé tramo"
            }

            //Si se excede la demora del tramo y el transeunte no confirmó que completó el tramo, se ejecuta la alarma
            if(!(unTramo.isTramoCompleto())){
                trayecto.getTranseunte().ejecutarAlerta();
            }
        }
    }
}
