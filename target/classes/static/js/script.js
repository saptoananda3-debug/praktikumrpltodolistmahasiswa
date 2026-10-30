/**
 * To-Do List Mahasiswa - Script Frontend
 * Penanganan interaktivitas UI, mobile sidebar toggle, filter dummy data, dan notifikasi simulasi.
 */

document.addEventListener('DOMContentLoaded', function () {
    // 1. Mobile Sidebar Toggle
    const sidebar = document.getElementById('sidebar');
    const sidebarToggleBtn = document.getElementById('sidebarToggleBtn');
    const sidebarOverlay = document.getElementById('sidebarOverlay');

    if (sidebarToggleBtn && sidebar && sidebarOverlay) {
        sidebarToggleBtn.addEventListener('click', function () {
            sidebar.classList.toggle('show');
            sidebarOverlay.classList.toggle('show');
        });

        sidebarOverlay.addEventListener('click', function () {
            sidebar.classList.remove('show');
            sidebarOverlay.classList.remove('show');
        });
    }

    // Tanggal Hari Ini (Format Indonesia)
    const currentDateDisplay = document.getElementById('currentDateDisplay');
    if (currentDateDisplay) {
        const now = new Date();
        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        try {
            currentDateDisplay.textContent = now.toLocaleDateString('id-ID', options);
        } catch (e) {
            currentDateDisplay.textContent = now.toDateString();
        }
    }

    // 2. Client-side Filter & Pencarian Interaktif pada Halaman Daftar Tugas
    const searchInput = document.getElementById('searchTugas');
    const filterMatkul = document.getElementById('filterMatkul');
    const filterStatus = document.getElementById('filterStatus');
    const sortDeadline = document.getElementById('sortDeadline');
    const tugasTableBody = document.getElementById('tugasTableBody');

    if (tugasTableBody) {
        const applyFilters = () => {
            const query = (searchInput ? searchInput.value : '').toLowerCase().trim();
            const selectedMatkul = filterMatkul ? filterMatkul.value.toLowerCase().trim() : '';
            const selectedStatus = filterStatus ? filterStatus.value.toLowerCase().trim() : '';

            const rows = Array.from(tugasTableBody.querySelectorAll('tr.tugas-row'));
            let visibleCount = 0;

            rows.forEach(row => {
                const nama = (row.getAttribute('data-nama') || '').toLowerCase();
                const matkul = (row.getAttribute('data-matkul') || '').toLowerCase();
                const status = (row.getAttribute('data-status') || '').toLowerCase();

                const matchesSearch = !query || nama.includes(query) || matkul.includes(query);
                const matchesMatkul = !selectedMatkul || matkul === selectedMatkul;
                const matchesStatus = !selectedStatus || status === selectedStatus;

                if (matchesSearch && matchesMatkul && matchesStatus) {
                    row.style.display = '';
                    visibleCount++;
                } else {
                    row.style.display = 'none';
                }
            });

            // Tampilkan baris "Data tidak ditemukan" jika kosong
            let noDataRow = document.getElementById('noDataRow');
            if (visibleCount === 0) {
                if (!noDataRow) {
                    noDataRow = document.createElement('tr');
                    noDataRow.id = 'noDataRow';
                    noDataRow.innerHTML = '<td colspan="8" class="text-center py-4 text-muted"><i class="bi bi-inbox fs-2 d-block mb-2"></i>Tidak ada tugas yang sesuai dengan kriteria pencarian/filter.</td>';
                    tugasTableBody.appendChild(noDataRow);
                }
                noDataRow.style.display = '';
            } else if (noDataRow) {
                noDataRow.style.display = 'none';
            }
        };

        if (searchInput) searchInput.addEventListener('input', applyFilters);
        if (filterMatkul) filterMatkul.addEventListener('change', applyFilters);
        if (filterStatus) filterStatus.addEventListener('change', applyFilters);

        // Pengurutan berdasarkan Deadline
        if (sortDeadline) {
            sortDeadline.addEventListener('change', function () {
                const rows = Array.from(tugasTableBody.querySelectorAll('tr.tugas-row'));
                const direction = this.value; // 'asc' atau 'desc'

                rows.sort((a, b) => {
                    const dateA = new Date(a.getAttribute('data-deadline') || '1970-01-01');
                    const dateB = new Date(b.getAttribute('data-deadline') || '1970-01-01');
                    return direction === 'asc' ? dateA - dateB : dateB - dateA;
                });

                rows.forEach(row => tugasTableBody.appendChild(row));
            });
        }
    }

    // 3. Penanganan Tombol UI Dummy (Edit, Hapus) menggunakan data attributes
    document.addEventListener('click', function (e) {
        const btn = e.target.closest('button[data-action]');
        if (!btn) return;

        const action = btn.getAttribute('data-action');
        const tugasNama = btn.getAttribute('data-tugas-nama') || 'Tugas';

        if (action === 'edit') {
            alert(`[SIMULASI UI] Anda memilih untuk mengedit tugas:\n"${tugasNama}"\n\nFitur pengeditan data akan dihubungkan ke database pada praktikum berikutnya.`);
        } else if (action === 'hapus') {
            const confirmed = confirm(`[SIMULASI UI] Apakah Anda yakin ingin menghapus tugas:\n"${tugasNama}"?\n\n(Tindakan ini hanya simulasi UI pada tahap ini).`);
            if (confirmed) {
                // Sembunyikan baris tugas jika dikonfirmasi
                const tr = btn.closest('tr');
                if (tr) {
                    tr.style.opacity = '0.4';
                    tr.style.pointerEvents = 'none';
                }
                alert(`[SIMULASI UI] Data "${tugasNama}" berhasil dihapus dari tampilan.`);
            }
        }
    });

    // 4. Penanganan Form Tambah Tugas (Mock UI Feedback)
    const formTambahTugas = document.getElementById('formTambahTugas');
    if (formTambahTugas) {
        formTambahTugas.addEventListener('submit', function (e) {
            e.preventDefault();
            const nama = document.getElementById('namaTugas')?.value || 'Tugas Baru';
            alert(`[SIMULASI UI] Formulir Tugas "${nama}" berhasil disubmit!\n\nPenyimpanan data ke database MySQL akan diimplementasikan pada praktikum berikutnya.\nAnda akan dialihkan kembali ke Daftar Tugas.`);
            window.location.href = '/tugas';
        });
    }
});
