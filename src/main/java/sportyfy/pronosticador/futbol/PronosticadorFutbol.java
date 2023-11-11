package sportyfy.pronosticador.futbol;

import lombok.Data;
import sportyfy.core.Pronosticador;
import sportyfy.core.entidades.equipo.Equipo;
import sportyfy.core.entidades.partido.Partido;
import sportyfy.core.entidades.resultado.Resultado;
import sportyfy.core.servicios.factorys.ResultadoPartidoFactory;
import sportyfy.core.servicios.parser.EquiposParser;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase `PronosticadorFutbol` que implementa la interfaz `Pronosticador`.
 * Esta clase se utiliza para pronosticar los resultados de partidos de fútbol
 * en base a datos históricos de partidos anteriores.
 */
@Data
public class PronosticadorFutbol implements Pronosticador {

    private Map<Partido, Resultado> partidos;
    private final Logger logger = Logger.getLogger(PronosticadorFutbol.class.getName());

    /**
     * Constructor de la clase `PronosticadorFutbol`.
     * Inicializa la lista de partidos a partir de los archivos de datos
     * de partidos.
     */
    @Override
    public void iniciar() {
        try {
            Set<Equipo> equipos = new EquiposParser().crearEquiposDesdeArchivos("src/main/resources/datos/partidos");
            partidos = ResultadoPartidoFactory.crearPartidosResultado("src/main/resources/datos/partidos",
                    new ObjectMapper(), equipos);
        } catch (IOException e) {
            logger.severe("Error al leer los archivos de partidos");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resultado pronosticar(Partido partido) {
        return new Resultado(new LinkedHashMap<>(Map.of(
                partido.getLocal(),
                (int) Math.round(CalculadoraPromedioGoles.calcularPromedioGoles(partido.getLocal(), partidos)),
                partido.getVisitante(),
                (int) Math.round(CalculadoraPromedioGoles.calcularPromedioGoles(partido.getVisitante(), partidos)))));
    }

    @Override
    public String getDeporte() {
        return "Fútbol";
    }

    @Override
    public Set<Equipo> getEquipos() {
        Set<Equipo> equipos = new HashSet<>();
        for (Partido partido : partidos.keySet()) {
            equipos.add(partido.getLocal());
            equipos.add(partido.getVisitante());
        }
        return equipos;
    }
}
