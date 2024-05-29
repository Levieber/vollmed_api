package med.voll.api.application.controllers;

import med.voll.api.application.dtos.address.CreateAddressDto;
import med.voll.api.application.dtos.address.ReadAddressDto;
import med.voll.api.application.dtos.doctor.CreateDoctorDto;
import med.voll.api.application.dtos.doctor.ShowDoctorDto;
import med.voll.api.application.services.DoctorService;
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


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<CreateDoctorDto> createDoctorDtoJson;
    @Autowired
    private JacksonTester<ShowDoctorDto> showDoctorDtoJson;
    @MockBean
    private DoctorService doctorService;

    @Test
    @DisplayName("Should return status code 400 when a invalid body is send")
    @WithMockUser
    void createCase1() throws Exception {
        var response = mvc.perform(post("/doctors")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return status code 200 when a doctor is created successfully")
    @WithMockUser
    void createCase2() throws Exception {
        var showDoctorDto = new ShowDoctorDto(
                null,
                "Doctor",
                "doctor@voll.med",
                "61888888888",
                "123456",
                DoctorSpeciality.CARDIOLOGY,
                readAddressDto());

        when(doctorService.create(any())).thenReturn(showDoctorDto);

        var response = mvc
                .perform(
                        post("/doctors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createDoctorDtoJson.write(
                                        new CreateDoctorDto(
                                                "Doctor",
                                                "doctor@voll.med",
                                                "61888888888",
                                                "123456",
                                                DoctorSpeciality.CARDIOLOGY,
                                                createAddressDto()
                                        )
                                ).getJson())
                )
                .andReturn()
                .getResponse();

        var expectedJson = showDoctorDtoJson
                .write(showDoctorDto)
                .getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    private CreateAddressDto createAddressDto() {
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

    private ReadAddressDto readAddressDto() {
        return new ReadAddressDto(
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