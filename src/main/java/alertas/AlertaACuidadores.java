package alertas;

import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class AlertaACuidadores implements Alerta{
    @Override
    public void alertar(Transeunte transeunte){
        List<Cuidador> cuidadores = transeunte.getTrayectoAsociado().getCuidadores();

        for(Cuidador unCuidador : cuidadores){
            unCuidador.recibirNotificacion("ALERTA");
        }
    }
}
