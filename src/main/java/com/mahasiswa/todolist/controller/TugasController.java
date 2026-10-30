package com.mahasiswa.todolist.controller;

import com.mahasiswa.todolist.model.Tugas;
import com.mahasiswa.todolist.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller untuk Halaman Daftar Tugas dan Tambah Tugas
 */
@Controller
@RequestMapping("/tugas")
public class TugasController {

    private final TugasService tugasService;

    @Autowired
    public TugasController(TugasService tugasService) {
        this.tugasService = tugasService;
    }

    /**
     * Halaman Daftar Seluruh Tugas
     */
    @GetMapping
    public String daftarTugas(Model model) {
        model.addAttribute("activePage", "tugas");
        model.addAttribute("pageTitle", "Daftar Tugas Perkuliahan");
        model.addAttribute("daftarTugas", tugasService.getAllTugas());
        model.addAttribute("mataKuliahList", tugasService.getMataKuliahList());
        return "tugas/index";
    }

    /**
     * Halaman Form Tambah Tugas Baru (UI only)
     */
    @GetMapping("/tambah")
    public String formTambahTugas(Model model) {
        model.addAttribute("activePage", "tambah");
        model.addAttribute("pageTitle", "Tambah Tugas Baru");
        model.addAttribute("tugas", new Tugas());
        return "tugas/tambah";
    }
}
