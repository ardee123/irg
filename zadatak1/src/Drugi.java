import java.util.Scanner;

public class Drugi {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Unesite koeficijente jednadzbe:");
        String []p=sc.nextLine().split(", ");
        int x1=Integer.parseInt(p[0]);
        int y1=Integer.parseInt(p[1]);
        int z1=Integer.parseInt(p[2]);
        int d1=Integer.parseInt(p[3]);
        int x2=Integer.parseInt(p[4]);
        int y2=Integer.parseInt(p[5]);
        int z2=Integer.parseInt(p[6]);
        int d2=Integer.parseInt(p[7]);
        int x3=Integer.parseInt(p[8]);
        int y3=Integer.parseInt(p[9]);
        int z3=Integer.parseInt(p[10]);
        int d3=Integer.parseInt(p[11]);

        int[][]m={{x1,y1,z1}, {x2,y2,z2}, {x3,y3,z3}};
        int[][]mX={{d1,y1,z1}, {d2, y2, z2}, {d3, y3, z3}};
        int[][]mY={{x1,d1,z1}, {x2,d2,z2}, {x3,d3,z3}};
        int[][]mZ={{x1,y1,d1}, {x2,y2,d2}, {x3,y3,d3}};

        int det=det3x3(m);
        int detX=det3x3(mX);
        int detY=det3x3(mY);
        int detZ=det3x3(mZ);

        int x=detX/det;
        int y=detY/det;
        int z=detZ/det;

        System.out.println("[x y z]=["+x+" "+y+" "+z+"]");
    }
    static int det3x3(int [][]m){
        int a=m[0][0]*m[1][1]*m[2][2];
        int b=m[0][1]*m[1][2]*m[2][0];
        int c=m[0][2]*m[1][0]*m[2][1];
        int d=m[2][0]*m[1][1]*m[0][2];
        int e=m[0][0]*m[1][2]*m[2][1];
        int f=m[2][2]*m[1][0]*m[0][1];
        return a+b+c-d-e-f;
    }
}
