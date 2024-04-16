package alertas;

import lombok.Getter;
import lombok.Setter;

public class Esperar implements Alerta{
    @Setter @Getter private Integer minutosDeEspera;

    public Esperar(Integer minutosDeEspera) {
        this.minutosDeEspera = minutosDeEspera;
    }
    @Override
    public void alertar(){

    }
}
