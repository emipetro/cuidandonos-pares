package trayecto;

import extras.CalculadoraDistancia;
import extras.DistanciaMatrixAPI;
import extras.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import usuarios.Cuidador;
import usuarios.Transeunte;
import java.util.List;

public abstract class Trayecto {
    @Setter @Getter protected Transeunte transeunte;
    @Setter @Getter protected List<Cuidador> cuidadores;

    @Setter @Getter protected Ubicacion inicio;
    @Setter @Getter protected Ubicacion destino;

    @Getter @Setter protected CalculadoraDistancia calculadoraDistancia = new DistanciaMatrixAPI();

    public Trayecto(Transeunte transeunte, List<Cuidador> cuidadores, Ubicacion inicio, Ubicacion destino) {
        this.transeunte = transeunte;
        this.cuidadores = cuidadores;
        this.inicio = inicio;
        this.destino = destino;

    }
    public float calcularDemora(){
        float distanciaTrayecto = this.calculadoraDistancia.calcularDistancia(inicio,destino);

        //Calcular tiempo de demora aproximado entre inicio y destino usando distanciaTrayecto

        return (float) 0;
    }
    public abstract void cambiarEstado(Transeunte transeunte);
}
