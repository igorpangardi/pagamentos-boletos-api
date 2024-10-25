package com.pagamentos.boletos.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagamentos.boletos.domain.model.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

    Optional<Boleto> findByCodigoBarras(String codigoBarras);
    
}
