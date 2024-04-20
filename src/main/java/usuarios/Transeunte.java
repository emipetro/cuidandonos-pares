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
    }

    public void apretarBotonLlegueBien(){
        List<Cuidador> cuidadores = trayectoAsociado.getCuidadores();
        Ubicacion inicio = trayectoAsociado.getInicio();
        Ubicacion destino = trayectoAsociado.getDestino();

        //Seteo como nuevo trayecto asociado un trayecto FinalizadoCorrectamente pero con los campos del trayecto que en su momento fue Comenzado
        setTrayectoAsociado(new FinalizadoCorrectamente(this,cuidadores,inicio,destino));

        //////////////////////////////////////////////
        // Habilitar notificaciones para el transeunte
        //////////////////////////////////////////////

        for(Cuidador unCuidador : cuidadores){
            unCuidador.recibirNotificacion("El viaje ha finalizado correctamente");
        }
    }

    ////////////////////////////
    public void quieroViajar(Ubicacion inicio, Ubicacion destino, List<Cuidador> cuidadoresDeseados){
        this.setTrayectoAsociado(new Solicitado(this,inicio,destino));

        for(Cuidador unCuidador : cuidadoresDeseados){
            unCuidador.recibirNotificacion("Te han seleccionado para un trayecto");
        }

        List<Cuidador> cuidadoresConfirmados = null;
        for(Cuidador unCuidador : cuidadoresDeseados){
            if(unCuidador.quiereHacerTrayecto()){
                cuidadoresConfirmados.add(unCuidador);
            }
        }

        if(cuidadoresConfirmados!=null){
            //MOSTRAR BOTÓN COMENZAR VIAJE
        }

        this.setTrayectoAsociado(new Comenzado(this,cuidadoresConfirmados,inicio,destino));
        this.comenzarViaje();
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
