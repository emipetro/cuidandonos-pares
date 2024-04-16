package trayecto;

import extras.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import usuarios.Cuidador;
import usuarios.Transeunte;
import java.util.List;

public class Trayecto {
    @Setter @Getter private Transeunte transeunte;
    @Setter @Getter private List<Cuidador> cuidadores;

    @Setter @Getter private Ubicacion inicio;
    @Setter @Getter private Ubicacion destino;

    @Setter @Getter private boolean estaComenzado = false;
    @Setter @Getter private boolean llegoBien = false;

    public Trayecto(Transeunte transeunte, List<Cuidador> cuidadores, Ubicacion inicio, Ubicacion destino) {
        this.transeunte = transeunte;
        this.cuidadores = cuidadores;
        this.inicio = inicio;
        this.destino = destino;
    }

    public Float calcularDemora(){
        //TODO
        //Calcular distancia en metros entre inicio y destino

        //TODO
        //Calcular tiempo de demora aproximado entre inicio y destino
        return (float) 0;
    }

    public boolean pasoMasTiempoDelEsperado(){
        //TODO
        //Calcular si desde que inicio el trayecto hasta ahora pasó más tiempo de la demora esperada
        return false;
    }

}
