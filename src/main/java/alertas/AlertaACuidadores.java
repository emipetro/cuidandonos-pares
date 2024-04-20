package alertas;

import usuarios.Transeunte;

public class AlertaACuidadores implements Alerta{
    @Override
    public void alertar(Transeunte transeunte){
        //Llamar/notificar a cada cuidador
        //Los mismos pueden sacarse de transeunte.getTrayectoAsociado().getCuidadores()
    }
}
