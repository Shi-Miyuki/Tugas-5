/**
 * Merepresentasikan sebuah tugas dengan judul dan deskripsi.
 */
class Task {
    private String title;
    private String description;

    /**
     * Konstruktor untuk kelas Task.
     *
     * @param title Judul tugas.
     * @param description Deskripsi tugas.
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Mengambil judul tugas.
     *
     * @return Judul tugas.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mengambil deskripsi tugas.
     *
     * @return Deskripsi tugas.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mengembalikan representasi string dari objek Task.
     *
     * @return String yang berisi judul dan deskripsi tugas.
     */
    @Override
    public String toString() {
        return "Judul: " + title + " | Deskripsi: " + description;
    }
}