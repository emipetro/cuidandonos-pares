package extras.modos_de_viaje;

import trayecto.Trayecto;

public class ViajeNormal implements ModoDeViaje{
    @Override
    public float demoraSegunModo(Trayecto trayecto) {
        return trayecto.getTramos().get(0).calcularDemora();
    }
}
