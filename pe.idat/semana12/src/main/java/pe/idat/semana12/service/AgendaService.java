package pe.idat.semana12.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.idat.semana12.model.Contacto;
import pe.idat.semana12.repository.AgendaRepository;

@Service
public class AgendaService {
    private AgendaRepository contactoRepository;
    public List<Contacto>recuperarContactos(){
        return contactoRepository.findAll();
    }
    public Contacto buscarcontacto(Long id){
        Optional<Contacto>contacto=contactoRepository.findById(id);
        return contacto.orElse(null);
    }
    public boolean agregarcontacto(Contacto contacto){
        return contactoRepository.save(contacto) !=null;
    }
    public void actualizarcontacto (Contacto contacto){
        if(contactoRepository.existsById(contacto.getIdContacto())){
            contactoRepository.save(contacto);
        }
    }
    public void eliminarcontacto(Long id){
        contactoRepository.deleteById(id);
    }
}
