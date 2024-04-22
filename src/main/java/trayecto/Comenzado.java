package trayecto;

import extras.Ubicacion;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class Comenzado extends Trayecto{
    public Comenzado(Transeunte transeunte, List<Cuidador> cuidadores, Ubicacion inicio, Ubicacion destino) {
        super(transeunte, cuidadores, inicio, destino);
    }

    @Override
    public void cambiarEstado(Transeunte transeunte){
        List<Cuidador> cuidadores = transeunte.getTrayectoAsociado().getCuidadores();
        Ubicacion inicio = transeunte.getTrayectoAsociado().getInicio();
        Ubicacion destino = transeunte.getTrayectoAsociado().getDestino();

        transeunte.setTrayectoAsociado(new FinalizadoCorrectamente(transeunte,cuidadores,inicio,destino));
    }
}
