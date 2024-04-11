package br.com.projeto.api.service;

import br.com.projeto.api.controller.dto.UnitDTO;
import br.com.projeto.api.model.Unit;
import br.com.projeto.api.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    ModelMapper modelMapper;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Optional<Unit> findById(Long id) {
        return unitRepository.findById(id);
    }

    @Override
    public Unit register(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public Unit edit(Long id, UnitDTO unitDTO) {
        Optional<Unit> existUnitOptional = unitRepository.findById(id);
        if (existUnitOptional.isPresent()) {
            Unit existUnit = existUnitOptional.get();
            BeanUtils.copyProperties(unitDTO, existUnit);
            return unitRepository.save(existUnit);
        } else {
            throw new EntityNotFoundException("Unidade não encontrada!");
        }
    }

    @Override
    public Unit delete(Long id) {
        Optional<Unit> existUnitOptional = unitRepository.findById(id);
        if (existUnitOptional.isPresent()) {
            Unit existUnit = existUnitOptional.get();
            unitRepository.delete(existUnit);
            return existUnit;
        } else {
            throw new EntityNotFoundException("Unidade não encontrada!");
        }
    }
}