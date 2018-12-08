import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.security.MessageDigest;

public class Signature {

    private BigInteger a,p,g,A,r,s,H;

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
        do {
            k = new BigInteger(1023, rnd);
        }while (k.gcd(p.subtract(BigInteger.ONE)).equals(BigInteger.ONE));
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

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getA() {
        return A;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getS() {
        return s;
    }

    public BigInteger getH() {
        return H;
    }
}
