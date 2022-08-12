/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kasirtoko;

/**
 *
 * @author naelafm
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class KasirToko {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws ParseException{
        BufferedReader br;
        String line;
        List<TokoBuah> listBarang=new ArrayList<TokoBuah>();
        Scanner bacaNamaF = new Scanner(System.in);
        System.out.print("Masukkan nama file toko (csv) = ");
        String namaF = bacaNamaF.next();
        try {
            br=new BufferedReader(new FileReader(namaF));
            while((line=br.readLine())!=null){
                
                String[] buahArray=line.split(";");
                
                TokoBuah buah=new TokoBuah();
              
                
                buah.setNamaBuah(buahArray[0]);
                buah.setHargaBuah(Integer.parseInt(buahArray[1]));
                
                listBarang.add(buah);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Scanner bacaInput = new Scanner(System.in);
        Scanner bacaLagi = new Scanner(System.in);
        Scanner baca = new Scanner(System.in);
        List<Belanja> listBeli=new ArrayList<Belanja>();
        
        System.out.println();
        System.out.println("=============== TOKO BUAH MBAK ELA ===============");
        
        boolean lagi = true;
        while (lagi){  

            System.out.println();
            System.out.print("Masukkan nama buah yang dibeli : ");
            String fruit = bacaInput.next();
            
            System.out.print("Jumlah buah yang dibeli : ");
            int jml = bacaInput.nextInt();
            System.out.println("..................................................");
        
        // pencarian sekuensial
            boolean ketemu = false;
            int i = 0;
            int banyak = listBarang.size();
            int posisi = 0;

            while (!(ketemu) && (i<banyak)) {
               if (listBarang.get(i).getNamaBuah().toLowerCase().contains(fruit)){
                   ketemu = true;
                   posisi = i;
               } else {
                   i = i + 1;
               }
            }
            
            int harga = listBarang.get(posisi).getHargaBuah();
            int totalHarga = harga * jml;
        
            // buat tampilkan data kalau buah ditemukan
            if (ketemu) {
                System.out.println("Buah yang dibeli : " + listBarang.get(posisi).getNamaBuah());
                System.out.println("Harga " +listBarang.get(posisi).getNamaBuah() + " satuan : Rp"+ 
                        harga + "               "+ "x" +jml);
                System.out.println("Total harga buah " +listBarang.get(posisi).getNamaBuah()+ 
                        ": Rp" + totalHarga);
                
                //array untuk menyimpan barang belanjaan dan harganya
                String[] tempArray = null;

                Belanja beliBuah = new Belanja();
        
                beliBuah.setNamaBuah(listBarang.get(posisi).getNamaBuah());
                beliBuah.setHargaBuah(listBarang.get(posisi).getHargaBuah());
                beliBuah.setJmlBuah(jml);

                listBeli.add(beliBuah);
            }
                else {
                    // buat tampilkan pesan kalau tidak ditemukan
                    System.out.println("Maaf, data buah " + fruit + " tidak ditemukan");
                    System.out.println();
            }
                    System.out.println();
                    System.out.print("Tambah buah lagi? (y/t)? ");
                    String pilihanLagi2 = bacaLagi.next();
         
                    System.out.println("--------------------------------------------------");

                    if (pilihanLagi2.compareTo("t")==0){
                        lagi=false;
                    }
            }
            if(lagi == false){
                  System.out.println("Daftar buah yang dibeli: ");
                  int total = 0;
                  
                  
                  for(Belanja daftarBarang:listBeli){
                  System.out.println(
                        daftarBarang.getNamaBuah()+
                        "             x"+daftarBarang.getJmlBuah()+"          "+
                        "Rp" + daftarBarang.getHargaBuah()*daftarBarang.getJmlBuah()
                                );
                  total += daftarBarang.getHargaBuah()*daftarBarang.getJmlBuah();
                  }
                  
                  //untuk menghitung diskon pembelian di atas Rp200.000
                  int totalDis = 0;
                  int diskon = total * 5/100;
                  if (total > 200000){  
                      totalDis = total - diskon;
                      System.out.println();
                      System.out.println("Pembelian di atas Rp200000, mendapat diskon 5%");
                      System.out.println("==================================================");

                      System.out.println("Total pembayaran    = Rp" +total+ " - Diskon 5%");
                      System.out.println("                    = Rp" +total+ " - Rp" + diskon);
                      System.out.println("                    = Rp" + totalDis);
                      System.out.print("Uang yang diberikan : Rp");
                      int bayar = baca.nextInt();
                      System.out.println("==================================================");
                      
                      if (bayar != total) {

                              if (bayar > total ){
                                  int kembali = bayar - total;
                                  System.out.println("Uang kembalian : Rp" + kembali);
                              }

                              else if (bayar < total){
                                  int utang = bayar - total;
                                  System.out.println("Maaf, uangnya kurang :Rp" + utang*-1);           
                                  }
                              }
                              else if (bayar == total){
                                  System.out.println("Pembayaran berhasil");    
                              }
                  } else{
                      diskon = 0;
                      totalDis = total - diskon;
                      System.out.println();
                      System.out.println("Pembelian di bawah Rp200000, tidak mendapat diskon");
                      System.out.println("==================================================");
             
                      System.out.println("Total pembayaran    = Rp" +total+ " - Diskon 0%");
                      System.out.println("                    = Rp" + totalDis);
                      System.out.print("Uang yang diberikan : Rp");
                      int bayar = baca.nextInt();
                      System.out.println("==================================================");
                      
                      if (bayar != total) {

                              if (bayar > total ){
                                  int kembali = bayar - total;
                                  System.out.println("Uang kembalian : Rp" + kembali);
                              }

                              else if (bayar < total){
                                  int utang = bayar - total;
                                  System.out.println("Maaf, uangnya kurang :Rp" + utang*-1);           
                                  }
                              }
                              else if (bayar == total){
                                  System.out.println("Pembayaran berhasil");    
                              }
                  }
                  System.out.println();
                  System.out.println("======= TERIMAKASIH SELAMAT DATANG KEMBALI =======");
            }
    }
}
                 