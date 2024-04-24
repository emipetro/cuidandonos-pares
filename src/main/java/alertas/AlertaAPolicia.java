package alertas;

import lombok.Getter;
import lombok.Setter;
import usuarios.Transeunte;

public class AlertaAPolicia implements Alerta{
    @Getter @Setter private String numeroPolicia = "911";

    @Override
    public void alertar(Transeunte transeunte){
        //Llamar al 911
    }
}
