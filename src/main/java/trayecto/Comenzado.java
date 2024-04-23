package trayecto;

import extras.Ubicacion;
import extras.modos_de_viaje.ModoDeViaje;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class Comenzado extends Trayecto{
    public Comenzado(Transeunte transeunte, List<Cuidador> cuidadores, ModoDeViaje modoDeViaje, List<Tramo> tramos) {
        super(transeunte, modoDeViaje, tramos);
        this.cuidadores = cuidadores;
    }

    @Override
    public void cambiarEstado(Transeunte transeunte){
        List<Cuidador> cuidadores = transeunte.getTrayectoAsociado().getCuidadores();
        ModoDeViaje modoDeViaje1 = transeunte.getTrayectoAsociado().modoDeViaje;
        List<Tramo> tramos = transeunte.getTrayectoAsociado().getTramos();

        transeunte.setTrayectoAsociado(new FinalizadoCorrectamente(transeunte,cuidadores,modoDeViaje1,tramos));
    }
}
