package com.api.agenda.repository;

import com.api.agenda.model.Agenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    Page<Agenda> findAllByPaciente_Id(Long idPaciente, Pageable pageable);

}
