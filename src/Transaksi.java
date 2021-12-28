import java.sql.Timestamp;
import java.util.Scanner;
import java.util.*;
import java.text.*;

public class Transaksi extends Program implements Penjualan{
    public String noFaktur , nama, toko;
    String namaBarang;
    int harga; 
    int jumlahBarang; 
    int diskon = 0;
    int discount = 0;
    int subTotal = harga * jumlahBarang;;
    int totalHarga;
    Scanner transaksi = new Scanner(System.in);

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    Transaksi(String a, String b, int c, int d){
        this.namaBarang = a;
        this.noFaktur = b;
    }

     // date
     Date Now = new Date( ); 
     SimpleDateFormat tgl =  new SimpleDateFormat ("E, dd-MM-yyy");
     SimpleDateFormat wk = new SimpleDateFormat ("hh:mm:ss a zzz"); 
     

    // no faktur
       public void NoFaktur() {
        System.out.println("\n");
        System.out.println("Nomor faktur\t: " + timestamp.getTime());
        noFaktur = transaksi.nextLine();
		noFaktur = noFaktur.toLowerCase();
        System.out.println("Tanggal pembelian barang\t: " + tgl.format(Now));
        System.out.println("Waktu pembelian barang\t\t: " + wk.format(Now));
    }


    // nama barang
    public void NamaBarang() {
        System.out.println("Nama Barang\t\t: " + this.namaBarang);
    }

    // harga barang
    public void HargaBarang() {
        System.out.println("Harga Barang\t\t: " + this.harga);
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

    // total belanja
    public void TotalHarga() {
        totalHarga = subTotal - diskon;
			System.out.println("Total harga barang Rp: " + totalHarga);
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


        public void pilihan(String tr) {
            if ((tr.equals("tidak")) || (tr.equals("Tidak"))) {
                System.exit(0);
            } 
            else {}
        } 

}
