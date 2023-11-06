package sportyfy.pronosticador.futbol;

import sportyfy.core.entidades.equipo.Equipo;
import sportyfy.core.entidades.partido.Partido;


import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Calcula el promedio de goles de un equipo en los partidos dados.
 */
public class ValidadorDatos {
    private static final Logger logger = Logger.getLogger(ValidadorDatos.class.getName());

    /**
     * Función que valida los datos de los partidos.
     * @param equipoLocal equipo local del partido.
     * @param equipoVisitante equipo visitante del partido.
     * @param partidos partidos que se van a validar.
     *
     * @throws IllegalArgumentException si los datos no son válidos.
     */
    public static void validar(Equipo equipoLocal, Equipo equipoVisitante, Set<Partido> partidos) {
        if (equipoLocal == null || equipoVisitante == null || partidos == null || equipoLocal.equals(equipoVisitante) || Objects.requireNonNull(partidos).isEmpty()) {
            String message = "Los datos no son válidos: ";
            if (equipoLocal == null) message += "equipo local nulo; ";
            if (equipoVisitante == null) message += "equipo visitante nulo; ";
            if (partidos == null) message += "partidos nulos; ";
            if (Objects.equals(equipoLocal, equipoVisitante)) message += "los equipos son iguales; ";
            if (Objects.requireNonNull(partidos).isEmpty()) message += "no hay partidos; ";

            logger.severe(message);
            throw new IllegalArgumentException(message);
        }
    }
}