import java.io.*;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Files {
    public void saveToFile(byte[] cipheredText, String filePath){
        Path path = Paths.get(filePath);
        try{
            java.nio.file.Files.write(path, cipheredText);
        }
        catch (IOException e) {
            System.out.println("Exception Occurred:");
        }
    }

    public byte[] readFromFile(String filePath){
        File plik = new File(filePath);
        byte[] fileContent = new byte[(int) plik.length()];
        FileInputStream fin = null;
        try{
            fin = new FileInputStream(plik);
            fin.read(fileContent);
        }
        catch (Exception ae){
            System.out.println("Blad " + ae);
        }
        try {
            fin.close();
        }
        catch (Exception ea){
            System.out.println("Blad " + ea);
        }
        return fileContent;
    }

    public void publicKeyToFile(BigInteger p, BigInteger g, BigInteger A){
        try (PrintWriter out = new PrintWriter("C:\\Users\\Łukasz\\Desktop\\key.txt")) {
            out.println(p);
            out.println(g);
            out.println(A);
        }catch (FileNotFoundException e){ System.out.println("Nie ma takiego pliku");}
    }

    public void SignatureToFile(BigInteger r, BigInteger s){
        try (PrintWriter out = new PrintWriter("C:\\Users\\Łukasz\\Desktop\\signature.txt")) {
            out.println(r);
            out.println(s);
        }catch (FileNotFoundException e){ System.out.println("Nie ma takiego pliku");}
    }

    public BigInteger[] readNumbers(String path, int size){
        BigInteger tab[] = new BigInteger[size];
        Scanner in = null;
        try{
            in = new Scanner(new File(path));
        }catch (FileNotFoundException e){ System.out.println("Nie ma takiego pliku");}
        int i=0;
        while(in.hasNextBigInteger()){
            tab[i]=in.nextBigInteger();
            i++;
        }
        return tab;
    }

    public BigInteger hashAFile(byte[] plain){
        MessageDigest h=null;
        try {
            h = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e){ System.err.println("Nie ma takiego algorytmu");}
        byte[] hash = h.digest(plain);
        BigInteger H = new BigInteger(hash);
        return H;
    }

}
