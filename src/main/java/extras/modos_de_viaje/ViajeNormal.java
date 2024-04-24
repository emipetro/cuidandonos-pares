package extras.modos_de_viaje;

import trayecto.Trayecto;

public class ViajeNormal implements ModoDeViaje{
    @Override
    public double demoraSegunModo(Trayecto trayecto) {
        return trayecto.getTramos().get(0).calcularDemoraTramo();
    }

    @Override
    public void esperarTiempoDeDemora(Trayecto trayecto) {
        //Mientras no haya pasado el tiempo de la demora se bloquean las notificaciones
        for(int i=0;i<this.demoraSegunModo(trayecto);i++){
            //Bloquear notificaciones
            //Mientras se ejecuta esto, el usuario puede presionar el botón de "Llegué bien!"
        }
    }


}
