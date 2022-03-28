package ar.edu.um.progranacion2.demo2.service.impl;

import ar.edu.um.progranacion2.demo2.domain.Persona;
import ar.edu.um.progranacion2.demo2.repository.PersonaRepository;
import ar.edu.um.progranacion2.demo2.service.PersonaService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Persona}.
 */
@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

    private final Logger log = LoggerFactory.getLogger(PersonaServiceImpl.class);

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        log.debug("Request to save Persona : {}", persona);
        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> partialUpdate(Persona persona) {
        log.debug("Request to partially update Persona : {}", persona);

        return personaRepository
            .findById(persona.getId())
            .map(existingPersona -> {
                if (persona.getNombre() != null) {
                    existingPersona.setNombre(persona.getNombre());
                }
                if (persona.getApellido() != null) {
                    existingPersona.setApellido(persona.getApellido());
                }

                return existingPersona;
            })
            .map(personaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> findAll(Pageable pageable) {
        log.debug("Request to get all Personas");
        return personaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findOne(Long id) {
        log.debug("Request to get Persona : {}", id);
        return personaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Persona : {}", id);
        personaRepository.deleteById(id);
    }
}
