package usuarios;
import alertas.Alerta;
import extras.modos_de_viaje.DetenerseNMinutos;
import extras.modos_de_viaje.IrAvisando;
import extras.modos_de_viaje.ModoDeViaje;
import extras.modos_de_viaje.ViajeNormal;
import trayecto.*;
import extras.Sexo;
import extras.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transeunte {
    @Setter @Getter private String nombreCompleto;
    @Setter @Getter private String direccion;
    @Setter @Getter private Integer edad;
    @Setter @Getter private Sexo sexo;
    @Setter @Getter private Trayecto trayectoAsociado = null;
    @Setter @Getter private String numeroDeTelefono;
    @Setter @Getter private Alerta alertaConfigurada;
    @Getter private List<Trayecto> trayectosRealizados = null;

    public Transeunte(String nombreCompleto, String direccion, Integer edad, Sexo sexo, String numeroDeTelefono, Alerta alertaConfigurada) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.sexo = sexo;
        this.edad = edad;
        this.numeroDeTelefono = numeroDeTelefono;
        this.alertaConfigurada = alertaConfigurada;
    }

    ////////// BOTONES /////////
    public void apretarBotonComenzarViaje() {
        double demoraAproximada = this.trayectoAsociado.calcularDemora();
        List<Cuidador> cuidadoresConfirmados = this.trayectoAsociado.getCuidadores();

        for(Cuidador unCuidador : cuidadoresConfirmados){
            unCuidador.recibirNotificacion("La demora aproximada es de "+demoraAproximada+" minutos");
        }

        //Cambio el estado de Solicitado a Comenzado
        this.trayectoAsociado.getHistorialEstados().add(new Estado(new PosibleEstado("Comenzado"), LocalDateTime.now()));
        this.comenzarViaje();
    }

    public void apretarBotonCompleteTramo(int numeroDeTramo){
        List<Tramo> tramos = this.trayectoAsociado.getTramos();
        Tramo tramoCompletado = tramos.get(numeroDeTramo);

        //Confirmo que el tramo fue completado
        tramoCompletado.setTramoCompleto(true);
    }

    public void apretarBotonLlegueBien(){
        //Cambio el estado de Comenzado a FinalizadoCorrectamente
        this.trayectoAsociado.getHistorialEstados().add(new Estado(new PosibleEstado("Finalizado"), LocalDateTime.now()));

        // Habilitar notificaciones para el transeunte

        //Notificar a los cuidadores
        for(Cuidador unCuidador : this.trayectoAsociado.getCuidadores()){
            unCuidador.recibirNotificacion("El viaje ha finalizado correctamente");
            unCuidador.getTrayectosRealizados().add(trayectoAsociado);
        }

        //Ahora que el viaje terminó correctamente, libero a los cuidadores y el transeunte del trayecto y lo guardo en un historial
        this.trayectosRealizados.add(trayectoAsociado);
        for(Cuidador unCuidador : this.trayectoAsociado.getCuidadores()){
            unCuidador.getTrayectosRealizados().add(this.trayectoAsociado);
            unCuidador.setTrayectoAsociado(null);
        }
        this.setTrayectoAsociado(null);
    }

    public ModoDeViaje apretarBotonElegirModoDeViaje(){
        // El usuario verá la opción de elegir el modo de viaje si el trayecto tiene más de una parada

        //Ejemplo de selección:

        //ModoDeViaje modoDeViaje = new IrAvisando();
        ModoDeViaje modoDeViaje = new DetenerseNMinutos(10);

        return modoDeViaje;
    }
    ////////////////////////////

    public void ejecutarAlerta() {
        this.alertaConfigurada.alertar(this);
    }

    /////////////IMPLEMENTACIÓN EXTENDIDA/////////////

    public void quieroViajar(Ubicacion inicio, List<Ubicacion> destinos, List<Cuidador> cuidadoresDeseados){
        //Armar los tramos
        Tramo tramoBase = new Tramo(inicio, destinos.get(0));
        List<Tramo> tramos = new ArrayList<>();
        tramos.add(tramoBase);

        for(int i=1; i<=destinos.size() ;i++){
            Tramo tramo = new Tramo(destinos.get(i-1), destinos.get(i));
            tramos.add(tramo);
        }

        //Seteo el trayecto asociado
        if(tramos.size()==1){
            this.setTrayectoAsociado(new Trayecto(this,(new ViajeNormal()),tramos));
        }
        else {
            //Mostrar botón elegir modo de viaje
            this.setTrayectoAsociado(new Trayecto(this,this.apretarBotonElegirModoDeViaje(),tramos));
        }

        //Notifico a los cuidadores
        for(Cuidador unCuidador : cuidadoresDeseados){
            unCuidador.recibirNotificacion("Te han seleccionado para un trayecto");
        }

        //Confirmo los cuidadores
        List<Cuidador> cuidadoresConfirmados = null;
        for(Cuidador unCuidador : cuidadoresDeseados){
            if(unCuidador.quiereHacerTrayecto(this.trayectoAsociado)){
                unCuidador.setTrayectoAsociado(this.trayectoAsociado);
                cuidadoresConfirmados.add(unCuidador);
            }
        }

        //Si hay al menos un cuidador confirmado:
        if(cuidadoresConfirmados!=null){
            //Asigno los cuidadores al trayecto
            this.trayectoAsociado.setCuidadores(cuidadoresConfirmados);

            //MOSTRAR BOTÓN COMENZAR VIAJE

            //Si aprieta se ejecuta:
            this.apretarBotonComenzarViaje();
        }
        else{
            // Error por falta de cuidadores
        }
    }

    //Cuando el transeunte apriete el botón, comienza el viaje
    public void comenzarViaje(){
        //Se ejecuta dentro de esta función un for para esperar la demora que corresponda
        //A su vez, se bloquean las notificaciones. Mientras pasa el tiempo estimado de demora, el usuario puede presionar el botón llegué bien
        this.trayectoAsociado.getModoDeViaje().esperarTiempoDeDemora(this.trayectoAsociado);

        //Si el trayecto no pasó a ser FinalizadoCorrectamente (debido al botón), se ejecuta la alarma
        if(!Objects.equals(this.trayectoAsociado.estadoActual().getEstado().getNombre(), "Finalizado")){
            this.ejecutarAlerta();
        }
    }
}
