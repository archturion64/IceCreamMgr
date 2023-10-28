package com.github.archturion64.CodingChallengeIceCream.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.archturion64.CodingChallengeIceCream.control.Category;
import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavor;
import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureMockMvc()
class IceCreamFlavorControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    private final String endpoint = "/api/v1/ice-cream/flavors";

    @MockBean
    private IceCreamFlavorService iceCreamFlavorServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should list all stored flavors on GET-Request to " + endpoint)
    void getFlavors_success() throws Exception {
        // arrange
        Mockito.when(iceCreamFlavorServiceMock.getAll())
                .thenReturn(Arrays.asList(
                        new IceCreamFlavor("Bloody Mary",
                                Category.WATER_BASED,
                                new HashSet<>(List.of("Tomatensaft", "Tabasco")),
                                "Glutenintoleranz",
                                219,
                                3.35),
                        new IceCreamFlavor("White Russian",
                                Category.CREAM_BASED,
                                new HashSet<>(List.of("Kaffee Liquor", "Sahne")),
                                "Laktoseintoleranz",
                                219,
                                4.02)
                ));
        // act, assert
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Bloody Mary")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("White Russian")));
    }

    @Test
    @DisplayName("should return a 500 if unhandled Exception thrown on GET-Request to " + endpoint)
    void getFlavors_Failure5xx() throws Exception {
        // arrange
        Mockito.when(iceCreamFlavorServiceMock.getAll())
                .thenThrow(new RuntimeException());
        // act, assert
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    @DisplayName("should return a 204 on successful POST-Request to " + endpoint)
    void postFlavors_Success() throws Exception {
        // arrange
        ArgumentCaptor<IceCreamFlavor> captor = ArgumentCaptor.forClass(IceCreamFlavor.class);
        final IceCreamFlavor expected = new IceCreamFlavor("Bloody Mary",
                Category.WATER_BASED,
                new HashSet<>(List.of("Tomatensaft", "Tabasco")),
                "Glutenintoleranz",
                219,
                3.35);
        final IceCreamFlavorDTO input = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                219,
                "3.35");
        final String requestJson = objectToJsonString(input);
        // act, assert
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().is(204));
        Mockito.verify(iceCreamFlavorServiceMock, times(1))
                .add(captor.capture());
        assertThat(captor.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("should return a 500 if unhandled Exception thrown on POST-Request to " + endpoint)
    void postFlavors_Failure5xx() throws Exception {
        // arrange
        final IceCreamFlavorDTO input = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                219,
                "3.35");
        final String requestJson = objectToJsonString(input);
        doThrow(new RuntimeException("unexpected"))
                .when(iceCreamFlavorServiceMock).add(Mockito.any());
        // act, assert
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }

    private <T> String objectToJsonString(final T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}