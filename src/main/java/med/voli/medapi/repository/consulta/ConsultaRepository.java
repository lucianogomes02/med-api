package med.voli.medapi.repository.consulta;

import med.voli.medapi.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

}
