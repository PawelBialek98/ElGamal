import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.security.MessageDigest;

public class ElGamal {

    private BigInteger a,A,p,g,r,s,H;

    BigInteger TWO = BigInteger.valueOf(2);

    public void keyGen(){
        BigInteger q;
        Random rnd = new Random();
        do{
            p = BigInteger.probablePrime(1024, rnd);            //p liczba pierwsza
            q = p.subtract(BigInteger.ONE).divide(TWO);
        }while(q.isProbablePrime(100));

        do {
            g = new BigInteger(1023, rnd);                         //g pierwiatek pierwotny g mod p
        }while(!g.modPow(TWO,p).equals(BigInteger.ONE) & !g.modPow(q,p).equals(BigInteger.ONE));

        a = new BigInteger(1023, rnd);

        A = g.modPow(a,p);
    }

    public void generate(byte[] plain){
        BigInteger k;
        Random rnd = new Random();
        k = new BigInteger(1023, rnd);
        r = g.modPow(k,p);
        MessageDigest h=null;
        try {
            h = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e){ System.err.println("Nie ma takiego algorytmu");}
        byte[] hash = h.digest(plain);
        H = new BigInteger(hash);
        BigInteger pom = H.subtract(a.multiply(r));
        s = pom.modInverse(p.subtract(BigInteger.ONE));
    }

    public boolean verify(){
        if(r.compareTo(BigInteger.ONE)!=-1 && r.compareTo(p.subtract(BigInteger.ONE))!=1){
            BigInteger pom = pow(A,r).multiply(pow(r,s));
            BigInteger tmp = pow(g,H);
            if(pom.mod(p).equals(tmp)) return true;
            else return false;
        }
        else return false;
    }

    public BigInteger pow(BigInteger base, BigInteger exp){
        BigInteger result = BigInteger.ONE;
        while (exp.signum() > 0) {
            if (exp.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exp = exp.shiftRight(1);
        }
        return result;
    }

    public BigInteger getA() {
        return a;
    }

    public void saveToFile(byte[] cipheredText, String filePath){
        Path path = Paths.get(filePath);
        try{
            Files.write(path, cipheredText);
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
}
