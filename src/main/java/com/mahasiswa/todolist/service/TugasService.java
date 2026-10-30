package com.mahasiswa.todolist.service;

import com.mahasiswa.todolist.model.Tugas;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer untuk mengelola logika bisnis dan data Tugas.
 * Saat ini menggunakan data dummy in-memory untuk kebutuhan tampilan frontend.
 * Pada praktikum berikutnya, service ini akan memanggil TugasRepository (Spring Data JPA).
 */
@Service
public class TugasService {

    private final List<Tugas> dummyTugasList = new ArrayList<>();

    public TugasService() {
        initDummyData();
    }

    /**
     * Menginisialisasi data dummy tugas mahasiswa yang realistis
     */
    private void initDummyData() {
        dummyTugasList.add(new Tugas(
                1L,
                "Makalah Penerapan Neural Network pada Citra Medis",
                "Kecerdasan Buatan",
                "Membuat makalah ilmiah minimal 10 halaman beserta referensi IEEE terbaru.",
                LocalDate.now().plusDays(3),
                "Tinggi",
                "Sedang Dikerjakan"
        ));

        dummyTugasList.add(new Tugas(
                2L,
                "Praktikum Routing OSPF & VLAN Modul 4",
                "Jaringan Komputer",
                "Konfigurasi topologi jaringan pada Cisco Packet Tracer dan kumpulkan file .pkt beserta laporan.",
                LocalDate.now().plusDays(7),
                "Sedang",
                "Belum Dikerjakan"
        ));

        dummyTugasList.add(new Tugas(
                3L,
                "Normalisasi Database Toko Online ke 3NF & BCNF",
                "Basis Data Lanjut",
                "Menganalisis skema tabel relasional unnormalized dan membuat diagram ERD relasi baru.",
                LocalDate.now().minusDays(2), // Tanggal kemarin -> Terlambat!
                "Tinggi",
                "Belum Dikerjakan"
        ));

        dummyTugasList.add(new Tugas(
                4L,
                "Implementasi RESTful API Produk dengan Spring Boot",
                "Pemrograman Web",
                "Membuat endpoint CRUD produk dengan validasi input dan exception handler.",
                LocalDate.now().plusDays(10),
                "Tinggi",
                "Sedang Dikerjakan"
        ));

        dummyTugasList.add(new Tugas(
                5L,
                "Analisis Kasus Pelanggaran Hak Cipta Perangkat Lunak",
                "Etika Profesi TI",
                "Review studi kasus pelanggaran lisensi open source vs proprietary.",
                LocalDate.now().minusDays(5),
                "Rendah",
                "Selesai"
        ));

        dummyTugasList.add(new Tugas(
                6L,
                "Desain UI/UX Wireframe Dashboard Mobile",
                "Interaksi Manusia dan Komputer",
                "Membuat prototype high-fidelity di Figma untuk aplikasi pemantau aktivitas harian.",
                LocalDate.now().minusDays(1),
                "Sedang",
                "Selesai"
        ));
    }

    /**
     * Mendapatkan semua daftar tugas
     */
    public List<Tugas> getAllTugas() {
        return new ArrayList<>(dummyTugasList);
    }

    /**
     * Statistik: Total seluruh tugas
     */
    public int getTotalTugas() {
        return dummyTugasList.size();
    }

    /**
     * Statistik: Total tugas dengan status 'Belum Dikerjakan'
     */
    public long getTotalBelumDikerjakan() {
        return dummyTugasList.stream()
                .filter(t -> "Belum Dikerjakan".equalsIgnoreCase(t.getStatus()))
                .count();
    }

    /**
     * Statistik: Total tugas dengan status 'Sedang Dikerjakan'
     */
    public long getTotalSedangDikerjakan() {
        return dummyTugasList.stream()
                .filter(t -> "Sedang Dikerjakan".equalsIgnoreCase(t.getStatus()))
                .count();
    }

    /**
     * Statistik: Total tugas dengan status 'Selesai'
     */
    public long getTotalSelesai() {
        return dummyTugasList.stream()
                .filter(t -> "Selesai".equalsIgnoreCase(t.getStatus()))
                .count();
    }

    /**
     * Mendapatkan daftar nama mata kuliah unik untuk filter dropdown
     */
    public List<String> getMataKuliahList() {
        return dummyTugasList.stream()
                .map(Tugas::getMataKuliah)
                .distinct()
                .collect(Collectors.toList());
    }
}
