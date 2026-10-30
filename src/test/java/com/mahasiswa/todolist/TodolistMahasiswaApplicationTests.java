package com.mahasiswa.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodolistMahasiswaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testDashboardRoute() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attributeExists("totalTugas", "belumDikerjakan", "sedangDikerjakan", "selesai", "daftarTugas"));

        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    void testDaftarTugasRoute() throws Exception {
        mockMvc.perform(get("/tugas"))
                .andExpect(status().isOk())
                .andExpect(view().name("tugas/index"))
                .andExpect(model().attributeExists("daftarTugas", "mataKuliahList"));
    }

    @Test
    void testTambahTugasRoute() throws Exception {
        mockMvc.perform(get("/tugas/tambah"))
                .andExpect(status().isOk())
                .andExpect(view().name("tugas/tambah"))
                .andExpect(model().attributeExists("tugas"));
    }
}
