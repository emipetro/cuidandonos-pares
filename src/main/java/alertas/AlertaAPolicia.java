package alertas;

import usuarios.Transeunte;

public class AlertaAPolicia implements Alerta{
    @Override
    public void alertar(Transeunte transeunte){
        //Llamar al 911
    }
}
