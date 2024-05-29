package med.voll.api.application.services;

import med.voll.api.application.dtos.appointment.CancelAppointmentDto;
import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.application.dtos.appointment.ShowAppointmentDto;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.validation.interfaces.CancelAppointmentValidator;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import med.voll.api.infra.repositories.AppointmentRepository;
import med.voll.api.infra.repositories.DoctorRepository;
import med.voll.api.infra.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private List<CreateAppointmentValidator> createAppointmentValidators;
    @Autowired
    private List<CancelAppointmentValidator> cancelAppointmentValidators;

    public ShowAppointmentDto create(CreateAppointmentDto dto) {
        if (!patientRepository.existsById(dto.patientId())) {
            throw new DomainValidationException("Patient not found with id " + dto.patientId());
        }

        if (dto.doctorId() != null && !doctorRepository.existsById(dto.doctorId())) {
            throw new DomainValidationException("Doctor not found with id " + dto.doctorId());
        }

        createAppointmentValidators.forEach(v -> v.validate(dto));

        var doctor = chooseDoctor(dto);

        if (doctor == null) {
            throw new DomainValidationException("No doctor with this specialty available at the time found");
        }

        var patient = patientRepository.getReferenceById(dto.patientId());
        var appointment = new Appointment(null, doctor, patient, dto.date(), null);

        appointmentRepository.save(appointment);

        return new ShowAppointmentDto(appointment);
    }

    public void cancel(Long id, CancelAppointmentDto dto) {
        if (!appointmentRepository.existsById(id)) {
            throw new DomainValidationException("Appointment not found with id " + id);
        }

        cancelAppointmentValidators.forEach(v -> v.validate(id, dto));

        var appointment = appointmentRepository.getReferenceById(id);


        appointment.cancel(dto.reason());
    }

    private Doctor chooseDoctor(CreateAppointmentDto dto) {
        if (dto.doctorId() != null) {
            return doctorRepository.getReferenceById(dto.doctorId());
        }

        if (dto.speciality() == null) {
            throw new DomainValidationException("Speciality is required when doctor is not specified");
        }

        return doctorRepository.findRandomBySpecialityAndFreeOnDate(dto.speciality(), dto.date());
    }
}
