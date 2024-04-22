package usuarios;
import alertas.Alerta;
import trayecto.Comenzado;
import trayecto.FinalizadoCorrectamente;
import trayecto.Solicitado;
import trayecto.Trayecto;
import extras.Sexo;
import extras.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
        float demoraAproximada = this.trayectoAsociado.calcularDemora();
        List<Cuidador> cuidadoresConfirmados = this.trayectoAsociado.getCuidadores();

        for(Cuidador unCuidador : cuidadoresConfirmados){
            unCuidador.recibirNotificacion("La demora aproximada es de "+demoraAproximada+" minutos");
        }

        //Cambio el estado de Solicitado a Comenzado
        this.trayectoAsociado.cambiarEstado(this);
        this.comenzarViaje();
    }

    public void apretarBotonLlegueBien(){
        //Cambio el estado de Comenzado a FinalizadoCorrectamente
        this.trayectoAsociado.cambiarEstado(this);

        //////////////////////////////////////////////
        // Habilitar notificaciones para el transeunte
        //////////////////////////////////////////////

        for(Cuidador unCuidador : this.trayectoAsociado.getCuidadores()){
            unCuidador.recibirNotificacion("El viaje ha finalizado correctamente");
            unCuidador.getTrayectosRealizados().add(trayectoAsociado);
        }

        //Ahora que el viaje terminó correctamente, libero a los cuidadores y el transeunte del trayecto y lo guardo en un historial
        this.trayectosRealizados.add(trayectoAsociado);
        for(Cuidador unCuidador : this.trayectoAsociado.getCuidadores()){
            unCuidador.setTrayectoAsociado(null);
        }
        this.setTrayectoAsociado(null);
    }

    ////////////////////////////
    public void quieroViajar(Ubicacion inicio, Ubicacion destino, List<Cuidador> cuidadoresDeseados){
        this.setTrayectoAsociado(new Solicitado(this,inicio,destino));

        for(Cuidador unCuidador : cuidadoresDeseados){
            unCuidador.recibirNotificacion("Te han seleccionado para un trayecto");
        }

        List<Cuidador> cuidadoresConfirmados = null;
        for(Cuidador unCuidador : cuidadoresDeseados){
            if(unCuidador.quiereHacerTrayecto(this.trayectoAsociado)){
                unCuidador.setTrayectoAsociado(this.trayectoAsociado);
                cuidadoresConfirmados.add(unCuidador);
            }
        }

        if(cuidadoresConfirmados!=null){
            //Asigno los cuidadores al trayecto
            this.trayectoAsociado.setCuidadores(cuidadoresConfirmados);

            //MOSTRAR BOTÓN COMENZAR VIAJE

            //Si apreta se ejecuta:
            this.apretarBotonComenzarViaje();
        }
        else{
            // Error por falta de cuidadores
        }
    }

    //Cuando el transeunte apriete el botón, comienza el viaje
    public void comenzarViaje(){
        float demoraAproximada = this.trayectoAsociado.calcularDemora();

        //Mientras no haya pasado el tiempo de la demora se bloquean las notificaciones
        for(int i=0;i<demoraAproximada;i++){
            //Bloquear notificaciones
            //Mientras se ejecuta esto, el usuario puede presionar el botón de "Llegué bien!"
        }

        //Si el trayecto no pasó a ser FinalizadoCorrectamente (debido al botón), se ejecuta la alarma
        if(!(this.trayectoAsociado instanceof FinalizadoCorrectamente)){
            this.ejecutarAlerta();
        }
    }

    public void ejecutarAlerta() {
        this.alertaConfigurada.alertar(this);
    }
}
