package med.voll.api.repository;

import med.voll.api.entidade.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository <Consulta, Long> {
}
