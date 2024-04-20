package alertas;

import lombok.Getter;
import lombok.Setter;
import usuarios.Transeunte;

public class Esperar implements Alerta{
    @Setter @Getter private Integer minutosDeEspera;

    public Esperar(Integer minutosDeEspera) {
        this.minutosDeEspera = minutosDeEspera;
    }
    @Override
    public void alertar(Transeunte transeunte){
        //Se setean unos minutos de espera.
        //Pasados dichos minutos, se ejecuta una nueva alarma
    }
}
