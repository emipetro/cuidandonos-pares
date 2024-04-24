package trayecto;

import extras.calculadora.CalculadoraDistancia;
import extras.calculadora.DistanciaMatrixAPI;
import extras.Ubicacion;
import extras.modos_de_viaje.ModoDeViaje;
import lombok.Getter;
import lombok.Setter;
import usuarios.Cuidador;
import usuarios.Transeunte;
import java.util.List;

public abstract class Trayecto {
    @Setter @Getter protected Transeunte transeunte;
    @Setter @Getter protected List<Cuidador> cuidadores = null;
    @Setter @Getter protected ModoDeViaje modoDeViaje;
    @Getter @Setter private List<Tramo> tramos;

    public Trayecto(Transeunte transeunte, ModoDeViaje modoDeViaje, List<Tramo> tramos) {
        this.transeunte = transeunte;
        this.modoDeViaje = modoDeViaje;
        this.tramos = tramos;
    }

    public double calcularDemora(){
        return this.modoDeViaje.demoraSegunModo(this);
    }
    public abstract void cambiarEstado(Transeunte transeunte);
}
