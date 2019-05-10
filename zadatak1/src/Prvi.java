import java.util.Scanner;

public class Prvi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] prviVektor = new int[3];
        int[] drugiVektor = new int[3];

        System.out.println("Unesite prvi vektor:");

        prviVektor[0] = sc.nextInt();
        prviVektor[1] = sc.nextInt();
        prviVektor[2] = sc.nextInt();
        sc.nextLine();

        System.out.println("Unesite drugi vektor: ");

        drugiVektor[0] = sc.nextInt();
        drugiVektor[1] = sc.nextInt();
        drugiVektor[2] = sc.nextInt();

        //Zbroj vektora
        int[] v1 = zbrojVektora(prviVektor, drugiVektor);

        System.out.println("Zbroj vektora: v1="+toStringArr(v1));

        //Skalarni umnozak
        System.out.println("Unesite vektor s za skalarni umnozak: ");
        int[] skal=new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        int[] s=skalarniUmnozak(v1, skal);

        System.out.println("Skalarni umnozak vektora: s="+toStringArr(s));

        System.out.println("Unesite vektor za vektorski umnozak: ");
        int[] vek=new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        int[] v2=vektorskiUmnozak(v1, vek);
        System.out.println("Vektorski umnozak vektora: v2="+toStringArr(v2));

        System.out.println("Normirani vektor v3: "+normiraniVektor(v2));
        System.out.println("Suprotni vektor v4: "+toStringArr(suprotniVektor(v2)));

        int [][]m1={{1,2,3}, {2,1,3}, {4,5,1}};
        int [][]m2={{-1,2,-3}, {5,-2,7}, {-4,-1,3}};

        System.out.println("Zbroj matrica: ");
        int [][]m3=zbrojMatrica(m1,m2);
        printMatrica(m3);

        int [][]t={{-1,2,-3}, {5,-2,7}, {-4,-1,3}};
        t=transponiranaMatrica(t);
        System.out.println("Transponirana matrica: ");
        printMatrica(t);

        int [][]m4=mnozenjeMatrica(m1,t);
        System.out.println("Mnozenje matrica");
        printMatrica(m4);

        int [][]inv={{-1,2,-3}, {5,-2,7}, {-4,-1,3}};
        double [][]i=inverzMatrice(inv);
        double [][]m5=mnozenjeMatricaD(m1, i);
        System.out.println("Mnozenje matrica s inverzom");
        printMatricaD(m5);
    }

    static String toStringArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > 0) {
                sb.append("+");
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    static int [] zbrojVektora(int []v1, int[] v2){
        int [] r=new int[3];
        r[0]=v1[0]+v2[0];
        r[1]=v1[1]+v2[1];
        r[2]=v1[2]+v2[2];
        return r;
    }

    static int [] skalarniUmnozak(int []v1, int []v2){
        int []r=new int[3];
        r[0]=v1[0]*v2[0];
        r[1]=v1[1]*v2[1];
        r[2]=v1[2]*v2[2];
        return r;
    }

    static int [] vektorskiUmnozak(int []v1, int []v2){
        int []r=new int[3];
        r[0]= (v1[1]*v2[2]-v1[2]*v2[1]);
        r[1]=(v1[2]*v2[0]-v1[0]*v2[2]);
        r[2]=(v1[0]*v2[1]-v1[1]*v2[0]);
        return r;
    }

    static double normiraniVektor(int []v){
        double r;
        r=Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
        return r;
    }

    static int [] suprotniVektor(int[]v){
        int []r=new int[3];
        r[0]=-v[0];
        r[1]=-v[1];
        r[2]=-v[2];
        return r;
    }

    static int [][] zbrojMatrica(int [][]m1, int [][]m2){
        int [][]r=new int[3][3];
        for(int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                r[i][j]=m1[i][j]+m2[i][j];

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

    static int [][] transponiranaMatrica(int [][]m){
        int [][]r=new int[3][3];
        for(int j=0; j<3;j++){
            for(int i=0;i<3;i++) {
                r[j][i] = m[i][j];
            }
        }
        return r;
    }

    static int [][] mnozenjeMatrica(int [][]m1, int [][]m2){
        int [][]r=new int[3][3];
        int sum=0,j;
        for(int i=0; i<3;i++){
            for(j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    sum+=m1[i][k]*m2[k][j];
                }
                r[i][j]=sum;
                sum=0;
            }
        }
        return r;
    }
    static double [][] mnozenjeMatricaD(int [][]m1, double [][]m2){
        double [][]r=new double[3][3];
        double sum=0;
        int j;
        for(int i=0; i<3;i++){
            for(j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    sum+=m1[i][k]*m2[k][j];
                }
                r[i][j]=sum;
                sum=0;
            }
        }
        return r;
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

    static double det3x3(int [][]m){
        int a=m[0][0]*m[1][1]*m[2][2];
        int b=m[0][1]*m[1][2]*m[2][0];
        int c=m[0][2]*m[1][0]*m[2][1];
        int d=m[2][0]*m[1][1]*m[0][2];
        int e=m[0][0]*m[1][2]*m[2][1];
        int f=m[2][2]*m[1][0]*m[0][1];
        return a+b+c-d-e-f;
    }
}
