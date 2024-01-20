package med.voli.medapi.repository.medico;

import med.voli.medapi.domain.medico.Especialidade;
import med.voli.medapi.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
                select m from Medico m
                where
                m.ativo = true
                and
                m.especialidade = :especialidade
                and
                m.id not in (
                    select c.medico.id from Consulta c
                    where
                    c.data = :data
                )
                order by rand()
                limit 1
                """)
    Medico buscarMedicoDisponivelNaData(Especialidade especialidade, LocalDateTime data);
}
