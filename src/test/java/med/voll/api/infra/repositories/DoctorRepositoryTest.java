package med.voll.api.infra.repositories;

import med.voll.api.application.dtos.address.CreateAddressDto;
import med.voll.api.application.dtos.doctor.CreateDoctorDto;
import med.voll.api.application.dtos.patient.CreatePatientDto;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.domain.enums.DoctorSpeciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should return null when a doctor is not free on date")
    void findRandomBySpecialityAndFreeOnDateCase1() {
        var date = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = createDoctor("Doctor", "doctor@voll.med", "123456", DoctorSpeciality.CARDIOLOGY);
        var patient = createPatient("Patient", "patient@email.com", "00000000000");
        createAppointment(doctor, patient, date);

        var doctorFree = doctorRepository.findRandomBySpecialityAndFreeOnDate(DoctorSpeciality.CARDIOLOGY, date);
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Should return a Doctor when a doctor is free on date")
    void findRandomBySpecialityAndFreeOnDateCase2() {
        var date = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = createDoctor("Doctor", "doctor@voll.med", "123456", DoctorSpeciality.CARDIOLOGY);
        var doctorFree = doctorRepository.findRandomBySpecialityAndFreeOnDate(DoctorSpeciality.CARDIOLOGY, date);

        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor createDoctor(String name, String email, String crm, DoctorSpeciality speciality) {
        var doctor = new Doctor(doctorDto(name, email, crm, speciality));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf) {
        var patient = new Patient(patientDto(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private CreateDoctorDto doctorDto(String name, String email, String crm, DoctorSpeciality speciality) {
        return new CreateDoctorDto(
                name,
                email,
                "61999999999",
                crm,
                speciality,
                addressDto()
        );
    }

    private CreatePatientDto patientDto(String name, String email, String cpf) {
        return new CreatePatientDto(
                name,
                email,
                "61999999999",
                cpf,
                addressDto()
        );
    }

    private CreateAddressDto addressDto() {
        return new CreateAddressDto(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}