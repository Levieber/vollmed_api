package med.voll.api.application.services;

import med.voll.api.application.dtos.CreateDoctorDto;
import med.voll.api.application.dtos.ReadDoctorDto;
import med.voll.api.application.dtos.UpdateDoctorDto;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.infra.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public void create(CreateDoctorDto dto) {
        doctorRepository.save(new Doctor(dto));
    }

    public Page<ReadDoctorDto> getAll(Pageable pageable) {
        return doctorRepository.findAllByActiveTrue(pageable).map(ReadDoctorDto::new);
    }

    public void update(Long id, UpdateDoctorDto dto) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.update(dto);
    }

    public void delete(Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}
