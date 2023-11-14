package med.voli.medapi.consulta.repository;

import med.voli.medapi.consulta.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

}
