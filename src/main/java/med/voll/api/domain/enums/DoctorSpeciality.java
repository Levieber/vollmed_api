package med.voll.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DoctorSpeciality {
    ORTHOPEDICS,
    CARDIOLOGY,
    GYNECOLOGY,
    DERMATOLOGY;

    @JsonCreator
    public static DoctorSpeciality fromString(String value) {
        for (DoctorSpeciality speciality : DoctorSpeciality.values()) {
            if (speciality.name().equalsIgnoreCase(value)) {
                return speciality;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Especialidade: " + value);
    }
}
