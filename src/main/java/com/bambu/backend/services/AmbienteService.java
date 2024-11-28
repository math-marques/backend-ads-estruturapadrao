package com.bambu.backend.services;

import com.bambu.backend.dtos.AmbienteDto;
import com.bambu.backend.models.AmbienteModel;
import com.bambu.backend.models.ProjetoModel;
import com.bambu.backend.repositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository ambienteRepository;

    public void salvarAmbientes(List<AmbienteDto> ambientes, ProjetoModel projeto) {

        for (AmbienteDto ambienteDto : ambientes) {
            AmbienteModel ambienteModel = new AmbienteModel();

            ambienteModel.setNome(ambienteDto.getNome());

            String tamanhoString = ambienteDto.getTamanho().replace("m²", "").trim();
            ambienteModel.setTamanho(Float.parseFloat(tamanhoString));

            ambienteModel.setProjeto(projeto);

            ambienteRepository.save(ambienteModel);
        }
    }

    public List<AmbienteDto> findAmbientesByProjetoId(UUID projetoId) {
        return ambienteRepository.findByProjetoId(projetoId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AmbienteDto convertToDto(AmbienteModel ambiente) {
        AmbienteDto dto = new AmbienteDto();
        dto.setNome(ambiente.getNome());
        dto.setTamanho(String.valueOf(ambiente.getTamanho()));
        return dto;
    }
}
