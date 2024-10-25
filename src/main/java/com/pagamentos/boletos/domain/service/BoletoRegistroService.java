package com.pagamentos.boletos.domain.service;

import static com.pagamentos.boletos.domain.constants.BoletoConstants.DIAS_PARA_ADICIONAR_AO_VENCIMENTO;
import static com.pagamentos.boletos.domain.utils.BoletoUtils.gerarCodigoBarras;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pagamentos.boletos.domain.exception.BoletoNotFoundException;
import com.pagamentos.boletos.domain.model.Boleto;
import com.pagamentos.boletos.domain.model.StatusBoleto;
import com.pagamentos.boletos.domain.repository.BoletoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoletoRegistroService {

    private final BoletoRepository repository;

    public List<Boleto> listar() {
        return repository.findAll();
    }

    public Boleto consultar(Long id) {
        return repository.findById(id).orElseThrow(() -> new BoletoNotFoundException("Não foi possível localizar um boleto com este id."));
    }

    public Boleto registrar(Boleto boleto) {
        prepararBoleto(boleto);
        return repository.save(boleto);
    }

    private void prepararBoleto(Boleto boleto) {
        boleto.setCodigoBarras(gerarCodigoBarras());
        boleto.setDataEmissao(LocalDate.now());
        boleto.setStatus(StatusBoleto.PENDENTE);
        boleto.setValorRestante(boleto.getValor());
        boleto.setDataVencimento(LocalDate.now().plusDays(DIAS_PARA_ADICIONAR_AO_VENCIMENTO));
    }

    @Scheduled(fixedRate = 60000) 
    public void atualizarStatusBoletos() {
        var boletos = repository.findAll();

        boletos.stream()
            .filter(boleto -> boleto.getStatus().equals(StatusBoleto.PENDENTE) && boleto.getDataVencimento().isBefore(LocalDate.now()))
            .forEach(this::atualizaStatusBoletosPendentes);

        boletos.stream()
            .filter(boleto -> boleto.getStatus().equals(StatusBoleto.ATRASADO) && boleto.getDataVencimento().isBefore(LocalDate.now().minusDays(10)))
            .forEach(this::atualizaStatusBoletosAtrasados);
    }

    private void atualizaStatusBoletosPendentes(Boleto boleto) {
        boleto.setStatus(StatusBoleto.ATRASADO);
        repository.save(boleto);
    }

    private void atualizaStatusBoletosAtrasados(Boleto boleto) {
        boleto.setStatus(StatusBoleto.CANCELADO);
        repository.save(boleto);
    }

}
