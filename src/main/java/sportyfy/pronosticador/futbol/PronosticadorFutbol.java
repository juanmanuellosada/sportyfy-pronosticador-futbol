package sportyfy.pronosticador.futbol;

import lombok.Data;
import sportyfy.core.entidades.Pronosticador;
import sportyfy.core.entidades.partido.Partido;

import java.util.Set;


/**
 * Clase `PronosticadorFutbol` que implementa la interfaz `Pronosticador`.
 * Esta clase se utiliza para pronosticar los resultados de partidos de fútbol
 * en base a datos históricos de partidos anteriores.
 */
@Data
public class PronosticadorFutbol implements Pronosticador {
    private Set<Partido> partidos;

    @Override
    public void setPartidos(Set<Partido> partidos) {
        this.partidos = partidos;
    }

    @Override
    public void pronosticar(Partido partido) {
        ValidadorDatos.validar(partido.getLocal(), partido.getVisitante(), partidos);

        double promedioGolesLocal = CalculadoraPromedioGoles.calcular(partido.getLocal(), partidos);
        double promedioGolesVisitante = CalculadoraPromedioGoles.calcular(partido.getVisitante(), partidos);

        int golesLocal = (int) (promedioGolesLocal + Math.random() * 2);
        int golesVisitante = (int) (promedioGolesVisitante + Math.random() * 2);

        partido.setMarcadorLocal(golesLocal);
        partido.setMarcadorVisitante(golesVisitante);
    }

    @Override
    public String getDeporte() {
        return "Futbol";
    }

}
