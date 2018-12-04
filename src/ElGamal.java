import java.math.BigInteger;
import java.util.Random;

public class ElGamal {

    public void keyGen(){
        BigInteger p, q, g;
        Random rnd = new Random();
        do{
            p = BigInteger.probablePrime(1024, rnd);            //p liczba pierwsza
            q = p.subtract(BigInteger.ONE).divide(BigInteger.TWO);
        }while(q.isProbablePrime(100));

        do {
            g = new BigInteger(1023, rnd);                         //g pierwiatek pierwotny g mod p
        }while(!g.modPow(BigInteger.TWO,p).equals(BigInteger.ONE) & !g.modPow(q,p).equals(BigInteger.ONE));

        BigInteger a = new BigInteger(1023, rnd);

        BigInteger B = g.modPow(a,p);
    }

}
