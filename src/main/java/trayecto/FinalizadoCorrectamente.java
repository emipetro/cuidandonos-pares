package trayecto;

import extras.Ubicacion;
import extras.modos_de_viaje.ModoDeViaje;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class FinalizadoCorrectamente extends Trayecto{
    public FinalizadoCorrectamente(Transeunte transeunte, List<Cuidador> cuidadores, ModoDeViaje modoDeViaje, List<Tramo> tramos) {
        super(transeunte, modoDeViaje, tramos);
        this.cuidadores = cuidadores;
    }

    @Override
    public void cambiarEstado(Transeunte transeunte) {
        //En principio, no puede cambiarse este estado
    }

}
