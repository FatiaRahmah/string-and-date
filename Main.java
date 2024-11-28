import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+-----------------------------------------------------+");
        System.out.println("                      Log In");
        System.out.println("+-----------------------------------------------------+");

        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            System.out.print("Username : ");
            String username = scanner.nextLine().trim();
            System.out.print("Password : ");
            String password = scanner.nextLine().trim();
            String captcha = generateCaptcha();

            System.out.println("Captcha    : " + captcha);
            System.out.print("Masukkan Captcha: ");
            String inputCaptcha = scanner.nextLine().trim();

            if (username.equalsIgnoreCase("fatia") && password.equals("123") && inputCaptcha.equals(captcha)) {
                isLoggedIn = true;
                System.out.println("Login berhasil. Selamat datang!");
            } else {
                System.out.println("Login gagal, silakan coba lagi.");
            }
        }

        // After successful login
        System.out.println("+-----------------------------------------------------+");
        System.out.println("           Selamat Datang di Supermarket Icih");
        String currentDateTime = getCurrentDateTime();
        System.out.println("Tanggal dan Waktu : " + currentDateTime);
        System.out.println("+-----------------------------------------------------+");

        while (true) {
            try {
                System.out.println("\n=== Input Data Transaksi ===\n");
                System.out.print("Masukkan No Faktur: ");
                String noFaktur = scanner.nextLine().trim();
                System.out.print("Masukkan Kode Barang: ");
                String kodeBarang = scanner.nextLine().trim();
                System.out.print("Masukkan Nama Barang: ");
                String namaBarang = scanner.nextLine().trim();

                double hargaBarang = 0;
                while (true) {
                    try {
                        System.out.print("Masukkan Harga Barang: ");
                        hargaBarang = Double.parseDouble(scanner.nextLine().trim());
                        if (hargaBarang <= 0)
                            throw new IllegalArgumentException("Harga barang harus lebih besar dari 0.");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Terjadi kesalahan di bagian harga barang: Input tidak valid.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Terjadi kesalahan di bagian harga barang: " + e.getMessage());
                    }
                }

                int jumlahBeli = 0;
                while (true) {
                    try {
                        System.out.print("Masukkan Jumlah Beli: ");
                        jumlahBeli = Integer.parseInt(scanner.nextLine().trim());
                        if (jumlahBeli <= 0)
                            throw new IllegalArgumentException("Jumlah beli harus lebih besar dari 0.");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Terjadi kesalahan di bagian jumlah beli: Input tidak valid.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Terjadi kesalahan di bagian jumlah beli: " + e.getMessage());
                    }
                }

                // Membuat objek Transaksi dan menghitung total
                Transaksi transaksi = new Transaksi(kodeBarang, namaBarang, hargaBarang, noFaktur, jumlahBeli);
                transaksi.hitungTotal();

                // Menampilkan invoice
                System.out.println("+-----------------------------------------------------+");
                System.out.println("                    Faktur Pembelian");
                System.out.println("+-----------------------------------------------------+");
                System.out.println(transaksi.displayInvoice());
                System.out.println("+-----------------------------------------------------+");
                System.out.print("Masukkan Nama Kasir: ");
                String namaKasir = scanner.nextLine().trim();
                System.out.println("Kasir           : " + namaKasir);
                System.out.println("+-----------------------------------------------------+");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }

            // Tanya ulang setelah input selesai
            System.out.print("\nApakah kamu ingin memasukkan data transaksi lain? (y/n): ");
            if (!konfirmasiUlang(scanner)) {
                System.out.println("Program berhasil diselesaikan. Terima kasih.");
                break;
            }
        }
        scanner.close();
    }

    private static String getCurrentDateTime() { //Metode Date kelas SimpleDateFormat untuk memformat tanggal dan waktu
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
// string untuk membentuk captcha dengan karakter acak
    private static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * characters.length());
            captcha.append(characters.charAt(index));
        }
        return captcha.toString();
    }

    private static boolean konfirmasiUlang(Scanner scanner) {
        while (true) {
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            } else {
                System.out.print("Input tidak valid. Masukkan 'y' atau 'n': ");
            }
        }
    }
}
