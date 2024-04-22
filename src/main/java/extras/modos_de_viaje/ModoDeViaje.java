package extras.modos_de_viaje;

import trayecto.Trayecto;

import java.util.List;

public interface ModoDeViaje {
    public float demoraSegunModo(List<Trayecto> trayectos);
}
