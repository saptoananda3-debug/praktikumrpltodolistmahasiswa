package com.mahasiswa.todolist.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Model data Tugas Mahasiswa
 * Catatan: Anotasi @Entity dan @Table akan ditambahkan pada praktikum integrasi database.
 */
public class Tugas {

    private Long id;
    private String namaTugas;
    private String mataKuliah;
    private String deskripsi;
    private LocalDate deadline;
    private String prioritas; // "Tinggi", "Sedang", "Rendah"
    private String status;    // "Belum Dikerjakan", "Sedang Dikerjakan", "Selesai"
    private boolean terlambat;

    public Tugas() {
    }

    public Tugas(Long id, String namaTugas, String mataKuliah, String deskripsi, LocalDate deadline, String prioritas, String status) {
        this.id = id;
        this.namaTugas = namaTugas;
        this.mataKuliah = mataKuliah;
        this.deskripsi = deskripsi;
        this.deadline = deadline;
        this.prioritas = prioritas;
        this.status = status;
        // Hitung apakah tugas terlambat jika deadline telah lewat dan status belum selesai
        this.terlambat = deadline != null && deadline.isBefore(LocalDate.now()) && !"Selesai".equalsIgnoreCase(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaTugas() {
        return namaTugas;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        if (deadline != null && deadline.isBefore(LocalDate.now()) && !"Selesai".equalsIgnoreCase(this.status)) {
            this.terlambat = true;
        }
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if ("Selesai".equalsIgnoreCase(status)) {
            this.terlambat = false;
        }
    }

    public boolean isTerlambat() {
        return terlambat;
    }

    public void setTerlambat(boolean terlambat) {
        this.terlambat = terlambat;
    }

    // Helper method untuk format tanggal tampilan
    public String getFormattedDeadline() {
        if (deadline == null) return "-";
        return deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
