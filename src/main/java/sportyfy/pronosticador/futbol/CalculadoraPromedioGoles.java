package sportyfy.pronosticador.futbol;

import sportyfy.core.entidades.equipo.Equipo;
import sportyfy.core.entidades.partido.Partido;

import java.util.Set;

/**
 * Calcula el promedio de goles de un equipo en los partidos dados.
 */
public class CalculadoraPromedioGoles {
    /**
     * Calcula el promedio de goles de un equipo en los partidos dados.
     * @param equipo equipo del que se calculará el promedio de goles.
     * @param partidos partidos en los que se calculará el promedio de goles.
     * @return promedio de goles de un equipo en los partidos dados.
     */
    public static double calcular(Equipo equipo, Set<Partido> partidos) {
        double goles = 0;
        int partidosJugados = 0;
        for (Partido partido : partidos) {
            if (partido.getLocal().equals(equipo) || partido.getVisitante().equals(equipo)) {
                goles += (partido.getLocal().equals(equipo)) ? partido.getMarcadorLocal() : partido.getMarcadorVisitante();
                partidosJugados++;
            }
        }
        return goles / partidosJugados;
    }
}