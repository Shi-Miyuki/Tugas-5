import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Aplikasi sederhana untuk mengelola katalog buku perpustakaan.
 */
public class LibraryCatalog {

    public static void main(String[] args) {
        ArrayList<Book> catalog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka untuk memilih menu.");
                scanner.nextLine(); // Membersihkan input yang salah
                choice = 0; // Pilihan yang tidak valid
                continue;
            }

            switch (choice) {
                case 1:
                    addBook(catalog, scanner);
                    break;
                case 2:
                    viewAllBooks(catalog);
                    break;
                case 3:
                    searchBook(catalog, scanner);
                    break;
                case 4:
                    deleteBook(catalog, scanner);
                    break;
                case 5:
                    System.out.println("Keluar dari katalog. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // --- Metode Bantuan ---

    /**
     * Menampilkan menu opsi kepada pengguna.
     */
    private static void displayMenu() {
        System.out.println("\n=== Library Catalog ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Lihat Semua Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Hapus Buku");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu: ");
    }

    /**
     * Menambahkan buku baru ke katalog.
     *
     * @param catalog Daftar buku.
     * @param scanner Objek Scanner untuk input pengguna.
     */
    private static void addBook(ArrayList<Book> catalog, Scanner scanner) {
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan nama penulis: ");
        String author = scanner.nextLine();
        
        int year = 0;
        System.out.print("Masukkan tahun terbit: ");
        // Validasi input untuk tahun
        try {
            year = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
        } catch (InputMismatchException e) {
            System.out.println("Tahun terbit tidak valid. Pembatalan penambahan buku.");
            scanner.nextLine(); // Membersihkan input yang salah
            return;
        }

        if (title.trim().isEmpty() || author.trim().isEmpty() || year < 0) {
             System.out.println("Input tidak boleh kosong atau tahun tidak valid. Pembatalan penambahan buku.");
             return;
        }
        
        catalog.add(new Book(title, author, year));
        System.out.println("Buku berhasil ditambahkan!");
    }

    /**
     * Menampilkan semua buku dalam katalog.
     *
     * @param catalog Daftar buku.
     */
    private static void viewAllBooks(ArrayList<Book> catalog) {
        System.out.println("\n=== Daftar Buku ===");
        if (catalog.isEmpty()) {
            System.out.println("Tidak ada buku dalam katalog.");
        } else {
            for (int i = 0; i < catalog.size(); i++) {
                System.out.println((i + 1) + ". " + catalog.get(i));
            }
        }
    }

    /**
     * Mencari buku berdasarkan judul (pencarian sebagian, tidak case-sensitive).
     *
     * @param catalog Daftar buku.
     * @param scanner Objek Scanner untuk input pengguna.
     */
    private static void searchBook(ArrayList<Book> catalog, Scanner scanner) {
        System.out.print("Masukkan judul buku yang dicari: ");
        String searchTitle = scanner.nextLine().toLowerCase();

        System.out.println("\n=== Hasil Pencarian ===");
        boolean found = false;
        
        if (catalog.isEmpty()) {
            System.out.println("Katalog kosong.");
            return;
        }

        for (Book book : catalog) {
            if (book.getTitle().toLowerCase().contains(searchTitle)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    /**
     * Menghapus buku dari katalog berdasarkan nomor urut yang ditampilkan.
     *
     * @param catalog Daftar buku.
     * @param scanner Objek Scanner untuk input pengguna.
     */
    private static void deleteBook(ArrayList<Book> catalog, Scanner scanner) {
        if (catalog.isEmpty()) {
            System.out.println("Tidak ada buku untuk dihapus.");
            return;
        }
        
        viewAllBooks(catalog); // Tampilkan daftar agar pengguna tahu nomornya
        System.out.print("Masukkan nomor buku yang akan dihapus: ");

        int index = -1;
        // Validasi input untuk nomor buku
        try {
            index = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka.");
            scanner.nextLine(); // Membersihkan input yang salah
            return;
        }

        // Periksa apakah nomor buku valid (antara 1 hingga ukuran daftar)
        if (index > 0 && index <= catalog.size()) {
            Book removedBook = catalog.remove(index - 1); // index - 1 karena ArrayList berbasis 0
            System.out.println("Buku '" + removedBook.getTitle() + "' berhasil dihapus!");
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }
}