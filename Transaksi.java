public class Transaksi extends Produk {
    private String noFaktur;
    private int jumlahBeli;
    private double hitungTotal;

    public Transaksi(String kodeBarang, String namaBarang, double hargaBarang, String noFaktur, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        if (noFaktur == null || noFaktur.isEmpty()) {
            throw new IllegalArgumentException("No Faktur tidak boleh kosong");
        }
        if (jumlahBeli <= 0) {
            throw new IllegalArgumentException("Jumlah beli harus lebih besar dari 0");
        }
        this.noFaktur = noFaktur;
        this.jumlahBeli = jumlahBeli;
        this.hitungTotal = 0;
    }

    public void hitungTotal() {
        this.hitungTotal = jumlahBeli * getHargaBarang();
    }

    public double getTotal() {
        return hitungTotal;
    }

    public String displayInvoice() {
        return "No Faktur: " + noFaktur + "\n"
                + super.toString() + "\n"
                + "Jumlah Beli: " + jumlahBeli + " buah\n"
                + "Total: Rp." + hitungTotal;
    }
}
