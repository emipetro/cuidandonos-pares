package trayecto;

import extras.Ubicacion;
import extras.modos_de_viaje.ModoDeViaje;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class Solicitado extends Trayecto {
    public Solicitado(Transeunte transeunte, ModoDeViaje modoDeViaje, List<Tramo> tramos) {
        super(transeunte, modoDeViaje, tramos);
    }

    @Override
    public void cambiarEstado(Transeunte transeunte){
        List<Cuidador> cuidadores = transeunte.getTrayectoAsociado().getCuidadores();
        ModoDeViaje modoDeViaje1 = transeunte.getTrayectoAsociado().modoDeViaje;
        List<Tramo> tramos = transeunte.getTrayectoAsociado().getTramos();

        transeunte.setTrayectoAsociado(new Comenzado(transeunte,cuidadores,modoDeViaje1,tramos));
    }
}
