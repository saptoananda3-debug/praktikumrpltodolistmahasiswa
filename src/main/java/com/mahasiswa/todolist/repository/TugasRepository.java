package com.mahasiswa.todolist.repository;

import com.mahasiswa.todolist.model.Tugas;
import java.util.List;
import java.util.Optional;

/**
 * Interface Repository untuk entitas Tugas.
 * 
 * CATATAN PRAKTIKUM BERIKUTNYA:
 * Pada praktikum integrasi database MySQL, interface ini akan meng-extend:
 * {@code org.springframework.data.jpa.repository.JpaRepository<Tugas, Long>}
 * 
 * Saat ini disiapkan sebagai kontrak dasar agar arsitektur proyek tetap rapi dan terstruktur.
 */
public interface TugasRepository {

    List<Tugas> findAll();

    Optional<Tugas> findById(Long id);

    Tugas save(Tugas tugas);

    void deleteById(Long id);
}
