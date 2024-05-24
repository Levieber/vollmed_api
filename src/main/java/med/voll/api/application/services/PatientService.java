package med.voll.api.application.services;

import med.voll.api.application.dtos.patient.CreatePatientDto;
import med.voll.api.application.dtos.patient.ListPatientsDto;
import med.voll.api.application.dtos.patient.ShowPatientDto;
import med.voll.api.application.dtos.patient.UpdatePatientDto;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void create(CreatePatientDto dto) {
        patientRepository.save(new Patient(dto));
    }

    public Page<ListPatientsDto> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable).map(ListPatientsDto::new);
    }

    public ShowPatientDto update(Long id, UpdatePatientDto dto) {
        var patient = patientRepository.getReferenceById(id);
        patient.update(dto);
        return new ShowPatientDto(patient);
    }

    public void delete(Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
    }
}