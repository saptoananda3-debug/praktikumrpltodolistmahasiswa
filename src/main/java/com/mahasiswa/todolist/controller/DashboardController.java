package com.mahasiswa.todolist.controller;

import com.mahasiswa.todolist.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller untuk Halaman Dashboard
 */
@Controller
public class DashboardController {

    private final TugasService tugasService;

    @Autowired
    public DashboardController(TugasService tugasService) {
        this.tugasService = tugasService;
    }

    /**
     * Menampilkan dashboard utama saat mengakses '/' atau '/dashboard'
     */
    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("activePage", "dashboard");
        model.addAttribute("pageTitle", "Dashboard Mahasiswa");

        // Data Statistik
        model.addAttribute("totalTugas", tugasService.getTotalTugas());
        model.addAttribute("belumDikerjakan", tugasService.getTotalBelumDikerjakan());
        model.addAttribute("sedangDikerjakan", tugasService.getTotalSedangDikerjakan());
        model.addAttribute("selesai", tugasService.getTotalSelesai());

        // Daftar Tugas untuk tabel di Dashboard
        model.addAttribute("daftarTugas", tugasService.getAllTugas());

        return "dashboard";
    }
}
