package trayecto;

import extras.Ubicacion;
import extras.modos_de_viaje.ModoDeViaje;
import lombok.Getter;
import lombok.Setter;
import usuarios.Cuidador;
import usuarios.Transeunte;

import java.util.List;

public class TrayectoExtendido{
    @Getter @Setter private List<Trayecto> trayectos;
    @Setter @Getter private ModoDeViaje modoDeViaje;
    @Setter @Getter private Transeunte transeunte;
    @Setter @Getter private List<Cuidador> cuidadores;

    public TrayectoExtendido(List<Trayecto> trayectos, ModoDeViaje modoDeViaje) {
        this.trayectos = trayectos;
        this.modoDeViaje = modoDeViaje;

        this.transeunte = this.getTrayectos().get(0).getTranseunte();
        this.cuidadores = this.getTrayectos().get(0).getCuidadores();
    }

    public float calcularDemoraExtendido(){
        return this.modoDeViaje.demoraSegunModo(this);
    }
}
