import java.math.BigInteger;

public class Verification {

    private BigInteger p,g,A,H,r,s;

    public Verification(BigInteger p, BigInteger g, BigInteger a, BigInteger h, BigInteger r, BigInteger s) {
        this.p = p;
        this.g = g;
        A = a;
        H = h;
        this.r=r;
        this.s=s;
    }

    public boolean verify(){
        if(r.compareTo(BigInteger.ONE)!=-1 && r.compareTo(p.subtract(BigInteger.ONE))!=1){
            BigInteger pom = pow(A,r).multiply(pow(r,s));
            System.out.println("czy ja wgl jestem tutaj ??");
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
            System.out.println("nie zamykaj mnie bo ide");
        }
        return result;
    }
}
