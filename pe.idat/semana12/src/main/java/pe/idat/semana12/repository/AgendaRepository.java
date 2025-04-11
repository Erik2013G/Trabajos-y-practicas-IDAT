package pe.idat.semana12.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.idat.semana12.model.Contacto;

public interface AgendaRepository extends JpaRepository <Contacto,Long>{

}
