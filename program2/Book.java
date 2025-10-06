/**
 * Merepresentasikan sebuah objek buku dengan judul, penulis, dan tahun terbit.
 */
class Book {
    private String title;
    private String author;
    private int year;

    /**
     * Konstruktor untuk kelas Book.
     *
     * @param title Judul buku.
     * @param author Penulis buku.
     * @param year Tahun terbit buku.
     */
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    /**
     * Mengambil judul buku.
     *
     * @return Judul buku.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mengembalikan representasi string dari objek Book.
     *
     * @return String yang berisi judul, penulis, dan tahun terbit.
     */
    @Override
    public String toString() {
        return "Judul: " + title + " | Penulis: " + author + " | Tahun: " + year;
    }
}