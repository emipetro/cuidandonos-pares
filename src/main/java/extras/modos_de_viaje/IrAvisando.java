package extras.modos_de_viaje;

import trayecto.Tramo;
import trayecto.Trayecto;

import java.util.List;

public class IrAvisando implements ModoDeViaje{
    @Override
    public double demoraSegunModo(Trayecto trayecto) {
        float demoraTotalTrayecto = 0;
        for(Tramo unTramo : trayecto.getTramos()){
            demoraTotalTrayecto+= unTramo.calcularDemoraTramo();
        }

        return demoraTotalTrayecto;
    }

    @Override
    public void esperarTiempoDeDemora(Trayecto trayecto){
        List<Tramo> tramos = trayecto.getTramos();
        int cantidadTramos = tramos.size();

        //Mientras no haya pasado el tiempo de la demora total del trayecto se bloquean las notificaciones
        for(int i=0; i<cantidadTramos ;i++){
            //Evaluo tramo por tramo la demora
            Tramo unTramo = tramos.get(i);
            for(int j=0; j<unTramo.calcularDemoraTramo() ;j++){
                //Mientras se ejecuta esto, el usuario puede presionar el botón de "Completé tramo"
                //Si se presiona dicho botón, se ejecuta:
                //trayecto.getTranseunte().apretarBotonCompleteTramo(i);
            }

            //Si se excede la demora del tramo y el transeunte no confirmó que completó el tramo, se ejecuta la alarma
            if(!(unTramo.isTramoCompleto())){
                trayecto.getTranseunte().ejecutarAlerta();
            }
        }
    }
}
