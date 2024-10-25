package com.pagamentos.boletos.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pagamentos.boletos.api.model.input.BoletoModelInput;
import com.pagamentos.boletos.api.model.input.BoletoModelPagarInput;
import com.pagamentos.boletos.api.model.output.BoletoModelOutput;
import com.pagamentos.boletos.api.model.output.BoletoModelPagarOutput;
import com.pagamentos.boletos.domain.model.Boleto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BoletoMapper {

    private ModelMapper modelMapper;

    public BoletoModelOutput toModel(Boleto boleto) {
        return modelMapper.map(boleto, BoletoModelOutput.class);
    }

    public BoletoModelPagarOutput toModelPagar(Boleto boleto) {
        return modelMapper.map(boleto, BoletoModelPagarOutput.class);
    }

    public List<BoletoModelOutput> toCollection(List<Boleto> boletoModel) {
        return boletoModel.stream()
            .map(this::toModel)
            .toList();
    }

    public Boleto toEntity(BoletoModelInput boleto) {
        return modelMapper.map(boleto, Boleto.class);
    }

    public Boleto toEntity(BoletoModelPagarInput boleto) {
        return modelMapper.map(boleto, Boleto.class);
    }

}
