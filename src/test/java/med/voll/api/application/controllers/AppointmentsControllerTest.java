package med.voll.api.application.controllers;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.application.dtos.appointment.ShowAppointmentDto;
import med.voll.api.application.services.AppointmentService;
import med.voll.api.domain.enums.DoctorSpeciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<CreateAppointmentDto> createAppointmentDtoJson;
    @Autowired
    private JacksonTester<ShowAppointmentDto> showAppointmentDtoJson;
    @MockBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Should return status code 400 when a invalid body is send")
    @WithMockUser
    void createCase1() throws Exception {
        var response = mvc.perform(post("/appointments")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return status code 200 when a appointment is created successfully")
    @WithMockUser
    void createCase2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var showAppointmentDto = new ShowAppointmentDto(null, 2L, 5L, date);

        when(appointmentService.create(any())).thenReturn(showAppointmentDto);

        var response = mvc
                .perform(
                        post("/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createAppointmentDtoJson.write(
                                        new CreateAppointmentDto(2L, 5L, date, DoctorSpeciality.CARDIOLOGY)
                                ).getJson())
                )
                .andReturn()
                .getResponse();

        var expectedJson = showAppointmentDtoJson
                .write(showAppointmentDto)
                .getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}