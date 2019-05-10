import java.util.Scanner;

public class Treci {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Unesite koordinate tocke A:");
        int xA=sc.nextInt();
        int yA=sc.nextInt();
        int zA=sc.nextInt();

        System.out.println("Unesite koordinate tocke B: ");
        int xB=sc.nextInt();
        int yB=sc.nextInt();
        int zB=sc.nextInt();

        System.out.println("Unesite koordinate tocke C: ");
        int xC=sc.nextInt();
        int yC=sc.nextInt();
        int zC=sc.nextInt();

        System.out.println("Unesite koordinate tocke T: ");
        int xT=sc.nextInt();
        int yT=sc.nextInt();
        int zT=sc.nextInt();

        int [][]m={{xA,xB,xC}, {yA, yB, yC}, {zA,zB,zC}};
        int [][]t={{xT}, {yT}, {zT}};

        double [][]inv=inverzMatrice(m);
        double []arr=mnozenje(inv, t);
        System.out.println("Baricentricne koordinate:");
        System.out.println("[x y z]=["+arr[0]+" "+arr[1]+" "+arr[2]+"]");

    }
    static double[][] inverzMatrice(int[][]m){
        double det=det3x3(m);
        System.out.println("Determinanta: "+det);
        int a1=m[1][1]*m[2][2]-m[2][1]*m[1][2];
        int a2=-1*(m[1][0]*m[2][2]-m[1][2]*m[2][0]);
        int a3=m[1][0]*m[2][1]-m[2][0]*m[1][1];
        int a4=-1*(m[0][1]*m[2][2]-m[0][2]*m[2][1]);
        int a5=m[0][0]*m[2][2]-m[0][2]*m[2][0];
        int a6=-1*(m[0][0]*m[2][1]-m[0][1]*m[2][0]);
        int a7=m[0][1]*m[1][2]-m[1][1]*m[0][2];
        int a8=-1*(m[0][0]*m[1][2]-m[1][0]*m[0][2]);
        int a9=m[0][0]*m[1][1]-m[1][0]*m[0][1];

        int [][]kov={{a1,a2,a3}, {a4,a5,a6}, {a7,a8,a9}};
        System.out.println("Kovarijacijska matrica");
        printMatrica(kov);
        int [][]t=transponiranaMatrica(kov);

        System.out.println("Transponirana matrica");
        printMatrica(t);

        double [][]r=new double[3][3];

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                r[i][j]=t[i][j]/det;
        System.out.println("Inverzna: ");
        printMatricaD(r);
        return r;
    }
    static int [][] transponiranaMatrica(int [][]m){
        int [][]r=new int[3][3];
        for(int j=0; j<3;j++){
            for(int i=0;i<3;i++) {
                r[j][i] = m[i][j];
            }
        }
        return r;
    }
    static void printMatrica(int[][]m){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)
                System.out.printf(m[i][j] + " ");
            System.out.println();
        }
    }

    static void printMatricaD(double[][]m){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)
                System.out.printf(m[i][j] + " ");
            System.out.println();
        }
    }
    static double det3x3(int [][]m){
        int a=m[0][0]*m[1][1]*m[2][2];
        int b=m[0][1]*m[1][2]*m[2][0];
        int c=m[0][2]*m[1][0]*m[2][1];
        int d=m[2][0]*m[1][1]*m[0][2];
        int e=m[0][0]*m[1][2]*m[2][1];
        int f=m[2][2]*m[1][0]*m[0][1];
        return a+b+c-d-e-f;
    }

    static double[] mnozenje(double [][]m1, int[][] m2){
        double a=m1[0][0]*m2[0][0] + m1[0][1]*m2[0][0] + m1[0][2]*m2[0][0];
        double b=m1[1][0]*m2[1][0] + m1[1][1]*m2[1][0] + m1[1][2]*m2[1][0];
        double c=m1[2][0]*m2[2][0] + m1[2][1]*m2[2][0] + m1[2][2]*m2[2][0];
        double []arr={a,b,c};
        return arr;
    }
}
