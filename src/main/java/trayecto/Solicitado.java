package trayecto;

import extras.Ubicacion;
import usuarios.Transeunte;

public class Solicitado extends Trayecto {
    public Solicitado(Transeunte transeunte, Ubicacion inicio, Ubicacion destino) {
        super(transeunte, null, inicio, destino);
    }
}
