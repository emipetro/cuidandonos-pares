package alertas;

import lombok.Getter;
import lombok.Setter;
import usuarios.Transeunte;

public class Esperar implements Alerta{
    @Setter @Getter private Integer minutosDeEspera;
    @Setter @Getter private Alerta alertaSecundaria;

    public Esperar(Integer minutosDeEspera, Alerta alertaSecundaria) {
        this.minutosDeEspera = minutosDeEspera;
        this.alertaSecundaria = alertaSecundaria;
    }
    @Override
    public void alertar(Transeunte transeunte){
        //Se setean unos minutos de espera.
        //Pasados dichos minutos, se ejecuta una nueva alarma
        alertaSecundaria.alertar(transeunte);
    }
}
