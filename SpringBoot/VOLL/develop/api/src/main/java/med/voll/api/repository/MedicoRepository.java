package med.voll.api.repository;

import med.voll.api.medico.entidade.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
