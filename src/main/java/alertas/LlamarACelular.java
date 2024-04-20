package alertas;

import usuarios.Transeunte;

public class LlamarACelular implements Alerta{
    @Override
    public void alertar(Transeunte transeunte){
        //El transeunte posee un campo "teléfono celular"
        //Se procede a llamar al celular del transeunte
    }
}
