public class Barang extends Transaksi{

	public String no_struk;
    public String nama_barang;
    public int jumlah_barang;
    public int harga;
    public int total_bayar;

	Barang(String a, String b, int c, int d) {
		super(a, b, c, d);
	} 

   // jumlah barang
   public void Jumlah() {
	System.out.println("Jumlah Barang\t\t: " + this.jumlahBarang);
}

// sub total belanja
public void SubTotal() {
	this.totalHarga = this.totalHarga + (this.jumlahBarang * this.harga);
	System.out.println("Sub Total\t\t: " + this.totalHarga);
}

// diskon belanja
public void Discount() {
	if(subTotal > 10000 && subTotal <= 50000) {
		discount = subTotal * 1/100;
		System.out.println("Diskon yang didapatkan sebesar Rp " + discount);
		
	}
	else if (subTotal > 50000 && subTotal <= 75000) {
		discount = subTotal * 3/100;
		System.out.println("Diskon yang didapatkan sebesar Rp " + discount);
	}
	else if (subTotal > 75000 && subTotal <= 90000) {
		discount = subTotal * 5/100;
		System.out.println("Diskon yang didapatkan sebesar Rp " + discount);
	}
	else if (subTotal > 90000 && subTotal <= 150000) {
		discount = subTotal * 7/100;
		System.out.println("Diskon yang didapatkan sebesar Rp " + discount);
		
	}
	else if (subTotal > 150000) {
		discount = subTotal * 10/100;
		System.out.println("Diskon yang didapatkan sebesar Rp " + discount);
	}
	else {
		System.out.println("Maaf, total belanja anda tidak memenuhi persyaratan mendapatkan diskon");
		System.out.println("Silakan belanja lagi");
	}
	diskon = discount;
	}

	public void tampil() {
		NoFaktur();
		NamaBarang();
		HargaBarang();
		Jumlah();
		SubTotal();
		Discount();
		TotalHarga();
		System.out.println("\nTerimakasih sudah membeli\n");
	}


	// total belanja
	public void TotalHarga() {
		totalHarga = subTotal - diskon;
		System.out.println("Total harga barang Rp: " + totalHarga);
	}

}
