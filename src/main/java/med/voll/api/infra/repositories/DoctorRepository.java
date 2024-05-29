package med.voll.api.infra.repositories;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.enums.DoctorSpeciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);
    @Query("""
            SELECT d.active
            FROM Doctor d
            WHERE d.id = :id""")
    boolean findActiveById(Long id);
    @Query("""
            SELECT d
            FROM Doctor d
            WHERE d.active = true
            AND d.speciality = :speciality
            AND d.id not in(
                SELECT a.doctor.id
                FROM Appointment a
                WHERE a.date = :date
                AND a.cancelReason is null
            )
            ORDER BY RAND()
            LIMIT 1""")
    Doctor findRandomBySpecialityAndFreeOnDate(DoctorSpeciality speciality, LocalDateTime date);
}
