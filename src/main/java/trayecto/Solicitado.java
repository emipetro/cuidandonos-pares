package trayecto;

import extras.Ubicacion;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class Solicitado extends Trayecto {
    public Solicitado(Transeunte transeunte, Ubicacion inicio, Ubicacion destino) {
        super(transeunte, null, inicio, destino);
    }

    @Override
    public void cambiarEstado(Transeunte transeunte){
        List<Cuidador> cuidadores = transeunte.getTrayectoAsociado().getCuidadores();
        Ubicacion inicio = transeunte.getTrayectoAsociado().getInicio();
        Ubicacion destino = transeunte.getTrayectoAsociado().getDestino();

        transeunte.setTrayectoAsociado(new Comenzado(transeunte,cuidadores,inicio,destino));
    }
}
