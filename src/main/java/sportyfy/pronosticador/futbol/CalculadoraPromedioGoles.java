package sportyfy.pronosticador.futbol;

import sportyfy.core.entidades.equipo.Equipo;
import sportyfy.core.entidades.partido.Partido;
import sportyfy.core.entidades.resultado.Resultado;

import java.util.Map;

public class CalculadoraPromedioGoles {
    public static double calcularPromedioGoles(Equipo equipo, Map<Partido, Resultado> partidos) {
        return partidos.entrySet().stream()
                .filter(entrada -> entrada.getKey().getLocal().equals(equipo)
                        || entrada.getKey().getVisitante().equals(equipo))
                .mapToInt(entrada -> entrada.getValue().getMarcador(equipo).orElse(0))
                .average()
                .orElse(0);
    }
}
