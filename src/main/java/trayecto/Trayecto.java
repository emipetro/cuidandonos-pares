package trayecto;

import extras.calculadora.CalculadoraDistancia;
import extras.calculadora.DistanciaMatrixAPI;
import extras.Ubicacion;
import extras.modos_de_viaje.ModoDeViaje;
import lombok.Getter;
import lombok.Setter;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trayecto {
    @Setter @Getter protected Transeunte transeunte;
    @Setter @Getter protected List<Cuidador> cuidadores = null;
    @Setter @Getter protected ModoDeViaje modoDeViaje;
    @Getter @Setter private List<Tramo> tramos;

    @Getter @Setter private List<Estado> historialEstados = new ArrayList<>();

    public Trayecto(Transeunte transeunte, ModoDeViaje modoDeViaje, List<Tramo> tramos) {
        this.transeunte = transeunte;
        this.modoDeViaje = modoDeViaje;
        this.tramos = tramos;
        this.historialEstados.add(new Estado(new PosibleEstado("Solicitado"), LocalDateTime.now()));
    }

    public double calcularDemora(){
        return this.modoDeViaje.demoraSegunModo(this);
    }

    public Estado estadoActual(){
        return this.historialEstados.get(this.historialEstados.size()-1);
    }
}
