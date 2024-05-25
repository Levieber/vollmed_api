package med.voll.api.application.services;

import med.voll.api.application.dtos.doctor.CreateDoctorDto;
import med.voll.api.application.dtos.doctor.ListDoctorsDto;
import med.voll.api.application.dtos.doctor.ShowDoctorDto;
import med.voll.api.application.dtos.doctor.UpdateDoctorDto;
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

    public ShowDoctorDto create(CreateDoctorDto dto) {
        return new ShowDoctorDto(doctorRepository.save(new Doctor(dto)));
    }

    public Page<ListDoctorsDto> getAll(Pageable pageable) {
        return doctorRepository.findAllByActiveTrue(pageable).map(ListDoctorsDto::new);
    }

    public ShowDoctorDto update(Long id, UpdateDoctorDto dto) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.update(dto);
        return new ShowDoctorDto(doctor);
    }

    public void delete(Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }

    public ShowDoctorDto getById(Long id) {
        return new ShowDoctorDto(doctorRepository.getReferenceById(id));
    }
}
