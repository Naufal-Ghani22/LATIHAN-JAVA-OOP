import java.util.*;

// KARYA SENI
class KaryaSeni {
    String judul;
    Artis artis;
    double harga;

    public KaryaSeni(String judul, Artis artis, double harga) {
        this.judul = judul;
        this.artis = artis;
        this.harga = harga;
    }

    public double getHarga() {
        return harga;
    }

    public String getJudul() {
        return judul;
    }

    public Artis getArtis() {
        return artis;
    }

    public String toString() {
        return judul + " oleh " + artis.getNama() + " - Rp" + harga;
    }
}

// LUKISAN EXTEND
class Lukisan extends KaryaSeni {
    private String teknik;

    public Lukisan(String judul, Artis artis, double harga, String teknik) {
        super(judul, artis, harga);
        this.teknik = teknik;
    }

    @Override
    public String toString() {
        return "[Lukisan] " + super.toString() + " | Teknik: " + teknik;
    }
}

// PATUNG EXTEND
class Patung extends KaryaSeni {
    private String bahan;

    public Patung(String judul, Artis artis, double harga, String bahan) {
        super(judul, artis, harga);
        this.bahan = bahan;
    }

    @Override
    public String toString() {
        return "[Patung] " + super.toString() + " | Bahan: " + bahan;
    }
}

// ARTIS
class Artis {
    private String nama;

    public Artis(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String toString() {
        return "Artis: " + nama;
    }
}

// CLIENT/PEMBELI
class Pembeli {
    private String nama;
    private LinkedList<KaryaSeni> koleksi;

    public Pembeli(String nama) {
        this.nama = nama;
        this.koleksi = new LinkedList<>();
    }

    public void beliKarya(KaryaSeni karya) {
        koleksi.add(karya);
    }

    public double hitungTotal() {
        double total = 0;
        for (KaryaSeni k : koleksi) {
            total += k.getHarga();
        }
        return total;
    }

    public void tampilkanKoleksi() {
        System.out.println("Koleksi karya seni " + nama + ":");
        int index = 1;
        for (KaryaSeni k : koleksi) {
            System.out.println(index++ + ". " + k.toString());
        }
    }

    public String getNama() {
        return nama;
    }
}

// TRANSKASI
class TransaksiSeni {
    // PERSEN DISKON
    public static double applyDiskon(double total, double persen) {
        if (persen < 0 || persen > 100) return total;
        return total - (total * persen / 100);
    }
    
    // NOTA
    public static void generateReceipt(Pembeli pembeli, double diskon) {
        System.out.println("\n--- Receipt Transaksi ---");
        pembeli.tampilkanKoleksi();
        double total = pembeli.hitungTotal();
        System.out.println("Total sebelum diskon: Rp" + total);
        double afterDiskon = applyDiskon(total, diskon);
        System.out.println("Total setelah diskon " + diskon + "%: Rp" + afterDiskon);
        System.out.println("-------------------------\n");
    }
}

// MAIN
public class WadahSeniLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<KaryaSeni> daftarKarya = new LinkedList<>();

        // Data awal
        Artis a1 = new Artis("Dewi Lestari");
        Artis a2 = new Artis("Budi Santosa");
        daftarKarya.add(new Lukisan("Senja Merah", a1, 1500000, "Akrilik"));
        daftarKarya.add(new Patung("Rasa Sunyi", a2, 2000000, "Kayu Jati"));
        daftarKarya.add(new Lukisan("Pagi di Desa", a2, 1000000, "Cat Minyak"));

        boolean running = true;
        while (running) {
            // menu utama
            System.out.println("\nSelamat datang di Aplikasi WANI (Wadah Seni) Galeri Seni Digital!");
            System.out.println("Silahkan pilih peran:");
            System.out.println("1. Creator (Artis)");
            System.out.println("2. Client (Pembeli)");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan : ");
            int role = sc.nextInt();
            sc.nextLine(); // konsumsi newline

            // creator menu
            if (role == 1) {
                System.out.print("Masukkan nama Anda (Creator): ");
                String namaArtis = sc.nextLine();
                Artis creator = new Artis(namaArtis);

                int menuCreator;
                do {
                    System.out.println("\n----------- Menu Creator ------------");
                    System.out.println("-------------------------------------");
                    System.out.println("HALO " + namaArtis);
                    System.out.println("Selamat datang di menu Creator - WANI (Wadah Seni)");
                    System.out.println("Pilih opsi fiturmu");
                    System.out.println("-------------------------------------");
                    System.out.println("1. Tambah karya seni");
                    System.out.println("2. Lihat semua karya Anda");
                    System.out.println("3. Hapus karya terakhir");
                    System.out.println("9. Ganti Peran");
                    System.out.print("Pilih menu: ");
                    menuCreator = sc.nextInt();
                    sc.nextLine();
 
                    switch (menuCreator) {
                        case 1:
                            System.out.println("Jenis karya seni:");
                            System.out.println("1. Lukisan");
                            System.out.println("2. Patung");
                            System.out.print("Pilih: ");
                            int jenis = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Judul: ");
                            String judul = sc.nextLine();

                            double harga = -1;
                            while (harga <= 0) {
                                System.out.print("Harga (lebih dari 0): ");
                                if (sc.hasNextDouble()) {
                                    harga = sc.nextDouble();
                                    sc.nextLine();
                                    if (harga <= 0) System.out.println("Harga tidak valid.");
                                } else {
                                    System.out.println("Input harus angka.");
                                    sc.next();
                                }
                            }

                            if (jenis == 1) {
                                System.out.print("Teknik lukisan: ");
                                String teknik = sc.nextLine();
                                daftarKarya.addLast(new Lukisan(judul, creator, harga, teknik));
                                System.out.println("Lukisan ditambahkan.");
                            } else if (jenis == 2) {
                                System.out.print("Bahan patung: ");
                                String bahan = sc.nextLine();
                                daftarKarya.addLast(new Patung(judul, creator, harga, bahan));
                                System.out.println("Patung ditambahkan.");
                            } else {
                                System.out.println("Jenis tidak valid.");
                            }
                            break;

                        case 2:
                            System.out.println("\nKarya milik Anda:");
                            int count = 0;
                            for (KaryaSeni k : daftarKarya) {
                                if (k.getArtis().getNama().equalsIgnoreCase(creator.getNama())) {
                                    System.out.println(++count + ". " + k);
                                }
                            }
                            if (count == 0) {
                                System.out.println("Belum ada karya.");
                            }
                            break;
                            
                        case 3:
                            if (!daftarKarya.isEmpty()) {
                                KaryaSeni removed = daftarKarya.removeLast();
                                System.out.println("Karya terakhir dihapus: " + removed.getJudul());
                            } else {
                                System.out.println("Daftar karya kosong.");
                            }
                            break;

                        case 9:
                            System.out.println("Kembali ke pemilihan peran...");
                            break;

                        default:
                            System.out.println("Menu tidak valid.");
                    }
                } while (menuCreator != 9);

            // client menu 
            } else if (role == 2) {
                System.out.print("Masukkan nama Anda (Client): ");
                String namaPembeli = sc.nextLine();
                Pembeli pembeli = new Pembeli(namaPembeli);

                int pilihan;
                do {
                    System.out.println("\n------------ Menu Client ------------");
                    System.out.println("-------------------------------------");
                    System.out.println("HALO " + namaPembeli);
                    System.out.println("Selamat datang di menu Client - WANI (Wadah Seni)");
                    System.out.println("Pilih opsi fiturmu");
                    System.out.println("--------------------------------------");
                    System.out.println("1. Lihat semua karya");
                    System.out.println("2. Beli karya seni");
                    System.out.println("3. Lihat koleksi Anda");
                    System.out.println("4. Generate Receipt");
                    System.out.println("5. Cari karya berdasarkan judul");
                    System.out.println("6. Urutkan karya berdasarkan harga (ascending)");
                    System.out.println("9. Ganti Peran");
                    System.out.print("Pilih menu: ");
                    pilihan = sc.nextInt();
                    sc.nextLine();

                    switch (pilihan) {
                        case 1:
                            System.out.println("\nDaftar Karya Seni:");
                            if (daftarKarya.isEmpty()) {
                                System.out.println("Belum ada karya seni tersedia.");
                            } else {
                                int index = 1;
                                for (KaryaSeni karya : daftarKarya) {
                                    System.out.println(index++ + ". " + karya);
                                }
                            }
                            break;
                            
                        case 2:
                            if (daftarKarya.isEmpty()) {
                                System.out.println("Tidak ada karya seni yang tersedia.");
                                break;
                            }
                            System.out.print("Masukkan nomor karya yang ingin dibeli: ");
                            int no = sc.nextInt();
                            if (no > 0 && no <= daftarKarya.size()) {
                                pembeli.beliKarya(daftarKarya.get(no - 1));
                                System.out.println("Karya berhasil dibeli.");
                            } else {
                                System.out.println("Nomor tidak valid.");
                            }
                            break;
                            
                        case 3:
                            pembeli.tampilkanKoleksi();
                            break;
                            
                        case 4:
                            System.out.print("Masukkan persen diskon (0-100): ");
                            double diskon = sc.nextDouble();
                            TransaksiSeni.generateReceipt(pembeli, diskon);
                            break;
                            
                        case 5:
                            System.out.print("Masukkan kata kunci judul: ");
                            String keyword = sc.nextLine().toLowerCase();
                            System.out.println("Hasil pencarian:");
                            boolean found = false;
                            for (KaryaSeni k : daftarKarya) {
                                if (k.getJudul().toLowerCase().contains(keyword)) {
                                    System.out.println("- " + k);
                                    found = true;
                                }
                            }
                            if (!found) {
                                System.out.println("Tidak ditemukan karya dengan judul tersebut.");
                            }
                            break;
                            
                        case 6:
                            // Menggunakan LinkedList untuk sorting
                            daftarKarya.sort(Comparator.comparingDouble(KaryaSeni::getHarga));
                            System.out.println("Karya seni telah diurutkan berdasarkan harga (ascending).");
                            break;
                            
                        case 9:
                            System.out.println("Kembali ke pemilihan peran...");
                            break;
                            
                        default:
                            System.out.println("Menu tidak valid.");
                    }
                } while (pilihan != 9);

            } else if (role == 0) {
                System.out.println("Terima kasih telah menggunakan aplikasi WANI - (Wadah Seni) Galeri Seni Digital.");
                running = false;
            } else {
                System.out.println("Peran tidak valid.");
            }
        }
        sc.close();
    }
}