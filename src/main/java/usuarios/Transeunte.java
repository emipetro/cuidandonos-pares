package usuarios;
import alertas.Alerta;
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
    @Setter @Getter private Ubicacion ubicacionActual;
    @Setter @Getter private Trayecto trayectoAsociado = null;
    @Setter @Getter private String numeroDeTelefono;

    @Setter @Getter private Alerta alertaConfigurada;

    public Transeunte(String nombreCompleto, String direccion, Integer edad, Sexo sexo, Ubicacion ubicacionActual, String numeroDeTelefono, Alerta alertaConfigurada) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.sexo = sexo;
        this.edad = edad;
        this.numeroDeTelefono = numeroDeTelefono;
        this.alertaConfigurada = alertaConfigurada;

        //TODO
        this.ubicacionActual = ubicacionActual; // TRABAJAR SOBRE CÓMO INTERACTUAR CON LAS UBICACIONES (LONGITUD Y LATITUD)
    }

    ////////// BOTONES /////////
    // Pueden ser clases?

    public void apretarBotonEmpezarViaje(){
        this.trayectoAsociado.setEstaComenzado(true);
    }

    public void apretarBotonLlegueBien(){
        this.trayectoAsociado.setLlegoBien(true);
    }

    ////////////////////////////
    public void quieroViajar(Ubicacion inicio, Ubicacion destino, List<Cuidador> cuidadores){
        Transeunte transeunte = new Transeunte(this.nombreCompleto,this.direccion,this.edad,this.sexo,inicio,this.numeroDeTelefono,this.alertaConfigurada);
        Trayecto nuevoTrayecto = new Trayecto(transeunte,cuidadores,inicio,destino);
        this.trayectoAsociado = nuevoTrayecto;

        for(Cuidador unCuidador : cuidadores){
            unCuidador.recibirNotificacion("Te han seleccionado para un trayecto");
            if(unCuidador.quiereHacerTrayecto()){
               //TODO
               //MOSTRAR BOTÓN PARA EMPEZAR VIAJE

                //Dicho botón COMENZAR cambia el estado del trayecto a iniciado
                if(nuevoTrayecto.isEstaComenzado()){
                    this.comenzarViaje(cuidadores);
                }
            }
        }
    }

    //Cuando el transeunte apriete el botón, comienza el viaje
    public void comenzarViaje(List<Cuidador> cuidadores){
        Float demoraAproximada = this.trayectoAsociado.calcularDemora();
        for(Cuidador unCuidador : cuidadores){
            unCuidador.recibirNotificacion("La demora aproximada es de "+demoraAproximada+" minutos");
        }

        //Mientras no haya pasado el tiempo de la demora se bloquean las notificaciones
        //Puede pensarse de otra manera que no sea un for
        for(int i=0;i<demoraAproximada;i++){
            //TODO
            //Bloquear notificaciones
            //Mientras se ejecuta esto, el usuario puede presionar el botón de "Llegué bien!"
        }

        if(trayectoAsociado.isLlegoBien()){
            //TODO
            //Habilitar notificaciones

            for(Cuidador unCuidador : cuidadores){
                unCuidador.recibirNotificacion("El viaje ha finalizado");
            }
        }
        else{
            this.ejecutarAlerta();
        }
    }

    public void ejecutarAlerta() {
        this.alertaConfigurada.alertar();
    }

}
