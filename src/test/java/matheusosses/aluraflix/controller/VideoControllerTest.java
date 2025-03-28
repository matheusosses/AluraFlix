package matheusosses.aluraflix.controller;

import matheusosses.aluraflix.dto.video.VideoDto;
import matheusosses.aluraflix.exception.ValidacaoException;
import matheusosses.aluraflix.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VideoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    private VideoService service;

    private final String json = """
                {
                "titulo": "teste",
                "descricao": "teste",
                "url": "https://example.net/"
                }
                """;

    private final VideoDto dto = new VideoDto("teste", "teste", "https://example.net/", 1L);

    @Test
    @DisplayName("Deveria retornar codigo 400 quando cadastrar videos com informações invalidas")
    void cenario1() throws Exception {
        String json = "";

        mvc.perform(
                post("/videos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar codigo 200 quando cadastrar videos com informações validas")
    void cenario2() throws Exception {
        when(service.cadastrarVideo(any())).thenReturn(dto);

        mvc.perform(
                post("/videos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("teste"))
                .andExpect(jsonPath("$.descricao").value("teste"))
                .andExpect(jsonPath("$.url").value("https://example.net/"))
                .andExpect(jsonPath("$.categoriaId").value(1L));


    }

    @Test
    @DisplayName("Deveria lançar codigo 404 quando listar e nao houver videos cadastrados")
    void cenario3() throws Exception {
        when(service.listarVideos()).thenThrow(new ValidacaoException(""));

        mvc.perform(get("/videos")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deveria lançar codigo 200 e devolver videos ao listar")
    void cenario4() throws Exception {
        List<VideoDto> dtoList = new ArrayList<>();
        dtoList.add(dto);
        dtoList.add(new VideoDto("teste2", "teste2", "https://example.net/2", 1L));

        when(service.listarVideos()).thenReturn(dtoList);

        mvc.perform(get("/videos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("teste"))
                .andExpect(jsonPath("$[0].descricao").value("teste"))
                .andExpect(jsonPath("$[0].url").value("https://example.net/"))
                .andExpect(jsonPath("$[0].categoriaId").value(1L))
                .andExpect(jsonPath("$[1].titulo").value("teste2"))
                .andExpect(jsonPath("$[1].descricao").value("teste2"))
                .andExpect(jsonPath("$[1].url").value("https://example.net/2"))
                .andExpect(jsonPath("$[1].categoriaId").value(1L));


    }

    @Test
    @DisplayName("Deveria lançar codigo 404 quando buscar por id que nao existe")
    void cenario5() throws Exception {
        when(service.buscarPorId(any())).thenThrow(new ValidacaoException(""));

        mvc.perform(get("/videos/1")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deveria lançar codigo 200 quando buscar por id")
    void cenario6() throws Exception {
        when(service.buscarPorId(any())).thenReturn(dto);

        mvc.perform(get("/videos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("teste"))
                .andExpect(jsonPath("$.descricao").value("teste"))
                .andExpect(jsonPath("$.url").value("https://example.net/"))
                .andExpect(jsonPath("$.categoriaId").value(1L));
    }

    @Test
    @DisplayName("Deveria lançar codigo 400 quando atualizar com informações invalidas")
    void cenario7() throws Exception {
        String json = "";

        mvc.perform(put("/videos/1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria lançar codigo 200 e video quando atualizar")
    void cenario8() throws Exception {
        BDDMockito.when(service.atualizarVideo(any(), any())).thenReturn(dto);

        mvc.perform(put("/videos/1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("teste"))
                .andExpect(jsonPath("$.descricao").value("teste"))
                .andExpect(jsonPath("$.url").value("https://example.net/"))
                .andExpect(jsonPath("$.categoriaId").value(1L));
    }
}