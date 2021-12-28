import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Program {

    // SQL
    static Connection connSQL;
    static Statement statementSQL;
    static ResultSet resultSQL;
    
    static String url = "jdbc:mysql://localhost:3306/penjualan";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String pass = ""; 
    
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);

    public static void main(String[] args) throws Exception {
        
        // inputan
        Scanner inputUser = new Scanner(System.in);

      
        boolean program = true;
        String pilihan;
        String tr;

        //nama field database
        

        //riwayat penjualan
        ArrayList<String> riwayatPenjualan = new ArrayList<String>();

        // Main
        try{


            Class.forName(driver);
            connSQL = DriverManager.getConnection(url, user, pass);
            System.out.println("Class Driver ditemukan");

            while(program){
                System.out.println("\n-------------------------------------------------------");
                System.out.println("=======Transaksi Penjualan Minuman di Toko Anugrah=======");
                System.out.println("---------------------------------------------------------");
                System.out.println("1. Transaksi penjualan");
                System.out.println("2. Riwayat penjualan");
                System.out.println("3. Hapus riwayat penjualan");
                System.out.println("4. Database penjualan");
                System.out.println("\nMasukkan Pilihan Anda (1/2/3/4/5): ");
                pilihan = inputUser.next();

            switch(pilihan){
                case "1":
                       try{
                           String text2 = "\n===Transaksi penjualan===";
                           System.out.println(text2.toUpperCase());

                           inputUser = new Scanner(System.in);

                           connSQL = DriverManager.getConnection(url, user, pass);
                           statementSQL = connSQL.createStatement();

                           System.out.println("Nomor Struk\t: ");
                           String no_struk = inputUser.nextLine();
                           System.out.print("Nama Barang\t: ");
                           String nama_barang = inputUser.nextLine();
                           System.out.print("Harga Barang\t: ");
                           int harga = inputUser.nextInt();
                           System.out.print("Jumlah Barang\t: ");
                           int jumlah_barang = inputUser.nextInt();
                           String sql = "INSERT INTO transaksi (nomor_struk, nama_barang, harga, jumlah_barang) VALUES('"+no_struk+"','"+nama_barang+"','"+harga+"','+jumlah_barang')";
                           sql = String.format(sql, no_struk, nama_barang, harga, jumlah_barang);
                           statementSQL.execute(sql);

                           Transaksi barang = new Barang(no_struk, nama_barang, harga, jumlah_barang);
                           barang.tampil();

                           System.out.println("Berhasil input data");
                           System.out.println("\nApakah anda ingin melanjutkan [y/n]? ");
                           inputUser.nextLine();
                           tr = inputUser.nextLine();
                           barang.pilihan(tr);
                           
                       }catch(SQLException e){
                        System.err.println("Terjadi kesalahan dalam input data");
                    }catch(InputMismatchException e){
                        System.err.println("Input dengan angka saja");
                    }
                    break;

                    case "2":
                        //riwayat penjualan
                        System.out.println("\n===Riwayat Penjualan===");
                        if (riwayatPenjualan.isEmpty()) {
                            System.out.println("Belum ada transaksi penjualan");
                        } 
                        else {
                            System.out.println("Riwayat penjualan yaitu : " + riwayatPenjualan);
                        }
                    break;

                    case "3":
                        //hapus riwayat penjualan
                        System.out.println("\n===Hapus Riwayat Penjualan===");
                        if (riwayatPenjualan.isEmpty()) {
                            System.out.println("Belum ada riwayat penjualan!");
                        } 
                        else {
                            riwayatPenjualan.clear();
                            System.out.println("Berhasil menghapus riwayat penjualan" + riwayatPenjualan);
                        }
                        break;

                    case "4":
                        //database penjualan
                        System.out.println("\n===Database Penjualan===");
                        System.out.println("1. Lihat data penjualan");
                        System.out.println("2. Tambah data penjualan");
                        System.out.println("3. Ubah data penjualan");
                        System.out.println("4. Hapus data penjualan");
                        System.out.println("5. Cari data penjualan");

                        // input pilihan 
                        System.out.println("\nMasukkan Pilihan Anda (1/2/3/4/5): ");
                        pilihan = inputUser.next();

                        // database
                        
                        switch(pilihan){
                            case "1":
                                LihatData();
                            break;

                            case "2":
                                TambahData();
                            break;

                            case "3":
                                UbahData();
                            break;

                            case "4":
                                HapusData();
                            break;

                            case "5":
                                CariData();
                            break;
                        }
                        break;

                    default:
                        System.out.println("Input data tidak ditemukan!\nInput data dari 1-5");
                    }
            
                System.out.println("\nApakah anda ingin melanjutkan [y/n]? ");
                pilihan = inputUser.next();
                program = pilihan.equalsIgnoreCase("Y");
        }
                System.out.println("\nTransaksi penjualan selesai");
                System.out.println("\n=====Terimakasih sudah membeli=====");

        }
            catch(ClassNotFoundException ex){
                System.err.println("Driver eror");
                System.exit(0);
            }
        
        }


    // Database
    
    // View(lihat) data 
    private static void LihatData() throws SQLException{
        try{
            String text1 = "\n===Daftar Data Penjualan===";
            System.out.println(text1.toUpperCase());
    
            // tampil semua
            String sql = "SELECT * FROM penjualan";
            connSQL = DriverManager.getConnection(url, user, pass);
            Statement statementSQL = connSQL.createStatement();
            resultSQL = statementSQL.executeQuery(sql);
    
            while(resultSQL.next()){
                System.out.println("\nNomor Struk\t: ");
                System.out.println(resultSQL.getString("no_struk"));
                System.out.println("\nNama Barang\t: ");
                System.out.println(resultSQL.getString("nama_barang"));
                System.out.println("\nHarga Barang\t: ");
                System.out.println(resultSQL.getInt("harga"));
                System.out.println("\nJumlah Barang\t: ");
                System.out.println(resultSQL.getInt("jumlah_barang"));
                System.out.println("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     
    }


    // Tambah data
    private static void TambahData() throws SQLException{
        
        String text2 = "\n===Tambah Data Penjualan===";
        System.out.println(text2.toUpperCase());

        Scanner inputUser = new Scanner(System.in);

        try{
            
            System.out.println("Nomor Struk\t: ");
            String no_struk = inputUser.nextLine();
            System.out.println("Nama Barang\t: ");
            String nama_barang = inputUser.nextLine();
            System.out.println("Harga Barang\t: ");
            int harga = inputUser.nextInt();
            System.out.println("Jumlah Barang\t: ");
            int jumlah = inputUser.nextInt();

            // tambah data
            String sql = "INSERT INTO penjualan (no_struk, nama_barang, harga, jumlah_barang) VALUES ('"+no_struk+"','"+nama_barang+"', '"+harga+"', '"+jumlah+"')";
            connSQL = DriverManager.getConnection(url, user, pass);
            statementSQL = connSQL.createStatement();
            resultSQL = statementSQL.executeQuery(sql);
            statementSQL.execute(sql);
            System.out.println("Berhasil input data");
        }
        catch(SQLException e){
            System.err.println("Terjadi kesalahan input data");
        }
    }


    // Update(ubah) data
    private static void UbahData() throws SQLException{
        String text3 = "\n===Ubah Data Penjualan===";
        System.out.println(text3.toUpperCase());

        Scanner inputUser = new Scanner(System.in);

        try{
            LihatData();
            System.out.println("Masukkan nomor struk yang akan diubah atau diupdate : ");
            String no_struk = inputUser.nextLine();

            // ubah data
            String sql = "SELECT * FROM penjualan WHERE no_struk = '" +no_struk+ "'";
            connSQL = DriverManager.getConnection(url, user, pass);
            statementSQL = connSQL.createStatement();
            resultSQL = statementSQL.executeQuery(sql);

        
        if(resultSQL.next()){

            // update atau ubah
            System.out.println("nama_barang ["+resultSQL.getString("nama_barang")+"]\t: ");
            String nama_barang = inputUser.nextLine();
            System.out.println("harga ["+resultSQL.getString("harga")+"]\t: ");
            int harga = inputUser.nextInt();
            System.out.println("jumlah ["+resultSQL.getString("jumlah_barang")+"]\t: ");
            int jumlah = inputUser.nextInt();

            // update
            sql = "UPDATE toko SET nama_barang='"+nama_barang+"',harga_barang= '"+harga+"', jumlah= '"+jumlah+"' WHERE no_struk='"+no_struk+"'";

            if(statementSQL.executeUpdate(sql) > 0){
                System.out.println("Data berhasil di-update (no_struk "+no_struk+")");
            }

        }
        statementSQL.close();
        }
        catch(SQLException e){
            System.err.println("Terjadi kesalahan dalam update data");
            System.err.println(e.getMessage());
        }
    }


    // Delete(hapus) data
    private static void HapusData() throws SQLException{
        String text4 = "\n===Hapus Data Penjualan===";
        System.out.println(text4.toUpperCase());

        Scanner inputUser = new Scanner(System.in);

        try{
            LihatData();
            System.out.println("Ketik nomor struk yang akan dihapus : ");
            String no_struk = inputUser.nextLine();

            // hapus data 
            String sql = "DELETE FROM penjualan WHERE no_struk = " +no_struk+"'";
            connSQL = DriverManager.getConnection(url, user, pass);
            statementSQL = connSQL.createStatement();
            // resultSQL = statementSQL.executeQuery(sql);


        if(statementSQL.executeUpdate(sql) > 0){
            System.out.println("Berhasil hapus data penjualan (Nomor struk "+no_struk+")");
        }
    }
        catch(SQLException e){
            System.err.println("Terjadi kesalahan dalam hapus data"); 
        }
    }


    //Search(cari) data
    private static void CariData() throws SQLException{
        String text5 = "\n===Cari Data Penjualan===";
        System.out.println(text5.toUpperCase());

        Scanner inputUser = new Scanner(System.in);

        System.out.println("Masukkan nama barang: ");

        String keyword = inputUser.nextLine();

        // cari data
        String sql = "SELECT * FROM penjualan WHERE nama LIKE '%"+keyword+"%'";
        connSQL = DriverManager.getConnection(url, user, pass);
        statementSQL = connSQL.createStatement();
        resultSQL = statementSQL.executeQuery(sql);


        while(resultSQL.next()){
            System.out.println("\nNomor Struk\t: ");
            System.out.println(resultSQL.getString("no_struk"));
            System.out.println("\nNama Barang\t: ");
            System.out.println(resultSQL.getString("nama_barang"));
            System.out.println("\nHarga Barang\t: ");
            System.out.println(resultSQL.getInt("harga"));
            System.out.println("\nJumlah Barang\t: ");
            System.out.println(resultSQL.getInt("nama_barang"));
            System.out.println("\nTotal Bayar Rp \t: ");
            System.out.println(resultSQL.getInt("nama_barang"));
            System.out.println("\n");
            
        }
    }
}
