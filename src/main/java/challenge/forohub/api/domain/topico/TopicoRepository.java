package challenge.forohub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Page<Topico> findAllByOrderByFechaDesc(Pageable paginacion);

    Page<Topico> findByCurso(NombreCurso nombre, Pageable paginacion);

    @Query(value = "SELECT * FROM topicos WHERE YEAR(fecha) = :year", nativeQuery = true)
    Page<Topico> findByFecha(@Param("year") int year, Pageable paginacion);

    @Query(value = "SELECT * FROM topicos WHERE YEAR(fecha) = :year AND curso = :nombre", nativeQuery = true)
    Page<Topico> findByCursoAndFecha(@Param("nombre") NombreCurso nombre, @Param("year") int year, Pageable paginacion);

    @Query("SELECT t FROM Topico t WHERE t.id =:id")
    Optional<Topico> findById(Long id);

}