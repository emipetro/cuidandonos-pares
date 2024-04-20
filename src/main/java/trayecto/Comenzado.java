package trayecto;

import extras.Ubicacion;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class Comenzado extends Trayecto{
    public Comenzado(Transeunte transeunte, List<Cuidador> cuidadores, Ubicacion inicio, Ubicacion destino) {
        super(transeunte, cuidadores, inicio, destino);
    }
}
