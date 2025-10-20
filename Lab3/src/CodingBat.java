public class CodingBat {

    public String firstHalf(String str) {
        return str.substring(0,str.length()/2);

    }

    public boolean monkeyTrouble(boolean aSmile, boolean bSmile) {
        if (aSmile && bSmile  || !aSmile && !bSmile) {
            return true;
        }
        return false;

    }

    public int diff21(int n) {
        if(n <= 21){
            return (21-n);
        }
        return 2*(n-21);

    }

    public int countEvens(int[] nums) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                a++;
            }
        }
        return a;
    }

    public static void main(String[] args) {


    }
}
