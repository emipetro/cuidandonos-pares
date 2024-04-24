package extras.modos_de_viaje;

import trayecto.Tramo;
import trayecto.Trayecto;

import java.util.List;

public interface ModoDeViaje {
    public abstract double demoraSegunModo(Trayecto trayecto);
    public void esperarTiempoDeDemora(Trayecto trayecto);
}
