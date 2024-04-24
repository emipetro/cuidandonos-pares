package trayecto;

import extras.Ubicacion;
import extras.calculadora.CalculadoraDistancia;
import extras.calculadora.DistanciaMatrixAPI;
import lombok.Getter;
import lombok.Setter;

public class Tramo {
    @Setter @Getter private Ubicacion inicio;
    @Setter @Getter private Ubicacion destino;
    @Getter @Setter private CalculadoraDistancia calculadoraDistancia = new DistanciaMatrixAPI();
    @Getter @Setter private boolean tramoCompleto = false;

    public Tramo(Ubicacion inicio, Ubicacion destino) {
        this.inicio = inicio;
        this.destino = destino;
    }
    public double calcularDemoraTramo(){
        //Expresada en metros
        float distanciaTrayecto = this.calculadoraDistancia.calcularDistancia(inicio,destino);

        //Si una persona promedio demora 1 minuto en recorrer 100 metros:
        //Expresada en minutos
        return distanciaTrayecto/100;
    }
}
