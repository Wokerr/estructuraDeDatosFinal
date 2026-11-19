package controller;

import model.CentroSalud;
import model.CitaNueva;
import model.CitaProcesada;
import model.Estructuras.Validador;

public class CitasController {
    private final CentroSalud centro;

    public CitasController(CentroSalud c) {
        this.centro = c;
    }

    public CitaNueva registrarNuevaCita() {
        int id = 0;
        do {
            id = Validador.leerEnteroMayor0("Introduzca el ID del usuario");

            if (!centro.verificarExiste(id)) {
                System.out.println(
                        "Este usuario no se encuentra registrado, para poder agendar una cita debes estar registrado");
            }
            
            
        } while (!centro.verificarExiste(id));

        String especialidad = Validador.leerOpcionMinusculaSinEspacios("Introduzca el nombre de la especialidad a ver");

        String problema = Validador.leerOpcionMinusculaSinEspacios("Introduzca el motivo de su cita");
        
        centro.crearCita(id, especialidad, problema);

        return new CitaNueva(id, especialidad, problema);
    }

    public CitaProcesada atenderSiguienteCita() throws Exception {
        CitaNueva c = centro.procesarCita();

        String diagnostico = Validador.leerOpcionMinusculaSinEspacios("Introduzca el diagnostico del usuario");

        CitaProcesada citaProcesada = new CitaProcesada(c.getIdPaciente(), c.getEspecialidad(), diagnostico);

        centro.agregarHistorial(citaProcesada);

        return citaProcesada;

    }
    
    public void mostrarCitasPendientes() {
        centro.mostrarCitasPendientes();
    }

    public void mostrarHistorial() {
        centro.mostrarHistorial();
    }

    public void mostrarUltimaAtencion() {
        centro.mostrarUltimaAtencion();
    }



}
