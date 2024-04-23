package trayecto;

import extras.Ubicacion;
import extras.calculadora.CalculadoraDistancia;
import extras.calculadora.DistanciaMatrixAPI;
import lombok.Getter;
import lombok.Setter;

public class Tramo {
    @Setter @Getter private Ubicacion inicio;
    @Setter @Getter private Ubicacion destino;
    @Getter @Setter protected CalculadoraDistancia calculadoraDistancia = new DistanciaMatrixAPI();

    public Tramo(Ubicacion inicio, Ubicacion destino) {
        this.inicio = inicio;
        this.destino = destino;
    }

    public float calcularDemora(){
        float distanciaTrayecto = this.calculadoraDistancia.calcularDistancia(inicio,destino);

        //Calcular tiempo de demora aproximado entre inicio y destino usando distanciaTrayecto

        return (float) 0;
    }
}
