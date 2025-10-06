import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aplikasi Personal Organizer sederhana untuk mengelola daftar tugas.
 * Kelas ini berisi metode main dan logika menu utama.
 */
public class PersonalOrganizer {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        // Menggunakan System.in untuk input
        Scanner scanner = new Scanner(System.in); 
        int choice;

        do {
            // Tampilkan menu utama
            displayMenu();
            
            // Tangani input untuk pilihan menu
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer setelah nextInt()
            } else {
                System.out.println("Input tidak valid. Masukkan angka.");
                scanner.nextLine(); // Membersihkan input yang salah
                choice = 0; // Atur ke pilihan default yang tidak valid
                continue; // Lanjutkan ke iterasi berikutnya
            }

            // Proses pilihan menu
            switch (choice) {
                case 1:
                    addTask(tasks, scanner);
                    break;
                case 2:
                    viewTasks(tasks);
                    break;
                case 3:
                    deleteTask(tasks, scanner);
                    break;
                case 4:
                    System.out.println("Terima kasih! Keluar dari aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (choice != 4);

        scanner.close();
    }

    /**
     * Menampilkan menu opsi kepada pengguna.
     */
    private static void displayMenu() {
        System.out.println("\n=== Personal Organizer ===");
        System.out.println("1. Tambah Tugas");
        System.out.println("2. Lihat Semua Tugas");
        System.out.println("3. Hapus Tugas");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu: ");
    }

    /**
     * Menambahkan tugas baru ke daftar tugas.
     *
     * @param tasks Daftar tugas.
     * @param scanner Objek Scanner untuk input pengguna.
     */
    private static void addTask(ArrayList<Task> tasks, Scanner scanner) {
        System.out.print("Masukkan judul tugas: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan deskripsi tugas: ");
        String description = scanner.nextLine();

        // Validasi input sederhana agar judul tugas tidak kosong
        if (title.trim().isEmpty()) {
             System.out.println("Judul tugas tidak boleh kosong!");
             return;
        }

        Task newTask = new Task(title, description);
        tasks.add(newTask);
        System.out.println("Tugas berhasil ditambahkan!");
    }

    /**
     * Menampilkan semua tugas dalam daftar.
     *
     * @param tasks Daftar tugas.
     */
    private static void viewTasks(ArrayList<Task> tasks) {
        System.out.println("\n=== Daftar Tugas ===");
        if (tasks.isEmpty()) {
            System.out.println("Tidak ada tugas.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Menghapus tugas dari daftar berdasarkan nomor.
     *
     * @param tasks Daftar tugas.
     * @param scanner Objek Scanner untuk input pengguna.
     */
    private static void deleteTask(ArrayList<Task> tasks, Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("Tidak ada tugas untuk dihapus.");
            return;
        }

        viewTasks(tasks); // Tampilkan daftar agar pengguna tahu nomornya
        System.out.print("Masukkan nomor tugas yang akan dihapus: ");

        int index = -1;
        if (scanner.hasNextInt()) {
            index = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
        } else {
            System.out.println("Input tidak valid. Masukkan angka.");
            scanner.nextLine(); // Membersihkan input yang salah
            return;
        }

        // Periksa apakah nomor tugas valid (antara 1 hingga ukuran daftar)
        if (index > 0 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1); // index - 1 karena ArrayList berbasis 0
            System.out.println("Tugas '" + removedTask.getTitle() + "' berhasil dihapus!");
        } else {
            System.out.println("Nomor tugas tidak valid.");
        }
    }
}