package com.pagamentos.boletos.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pagamentos.boletos.api.mapper.BoletoMapper;
import com.pagamentos.boletos.api.model.input.BoletoModelInput;
import com.pagamentos.boletos.api.model.input.BoletoModelPagarInput;
import com.pagamentos.boletos.api.model.output.BoletoModelOutput;
import com.pagamentos.boletos.api.model.output.BoletoModelPagarOutput;
import com.pagamentos.boletos.domain.service.BoletoPagamentoService;
import com.pagamentos.boletos.domain.service.BoletoRegistroService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/boletos")
public class BoletoController {

    private final BoletoRegistroService registroService;
    private final BoletoPagamentoService pagamentoService;
    private final BoletoMapper boletoMapper;

    @GetMapping("/{id}")
    public BoletoModelOutput consultar(@PathVariable Long id) {
        return boletoMapper.toModel(registroService.consultar(id));
    }

    @GetMapping
    public List<BoletoModelOutput> listar() {
        return boletoMapper.toCollection(registroService.listar());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoletoModelOutput registrar(@Valid @RequestBody BoletoModelInput boleto) {
        return boletoMapper.toModel(registroService.registrar(boletoMapper.toEntity(boleto)));
    }

    @PostMapping("/pagar")
    @ResponseStatus(HttpStatus.OK)
    public BoletoModelPagarOutput pagar(@Valid @RequestBody BoletoModelPagarInput boleto) {
        return boletoMapper.toModelPagar(pagamentoService.pagar(boletoMapper.toEntity(boleto)));
    }

}
