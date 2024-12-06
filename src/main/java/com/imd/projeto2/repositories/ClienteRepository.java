package com.imd.projeto2.repositories;

import com.imd.projeto2.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    List<ClienteModel> findAllByAtivoTrue();
}
