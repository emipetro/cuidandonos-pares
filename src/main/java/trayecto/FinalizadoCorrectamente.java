package trayecto;

import extras.Ubicacion;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class FinalizadoCorrectamente extends Trayecto{
    public FinalizadoCorrectamente(Transeunte transeunte, List<Cuidador> cuidadores, Ubicacion inicio, Ubicacion destino) {
        super(transeunte, cuidadores, inicio, destino);
    }
}
