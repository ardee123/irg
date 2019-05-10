import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tijela {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Vertex> vertexes = new ArrayList<>();
        List<Polygon> polygons = new ArrayList<>();

        File f=new File("kocka.obj.txt");

        FileInputStream fstream=null;
        try {
            fstream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(fstream)))) {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

        String line = null;

            while ((line = br.readLine()) != null) {
                // process the line.
                String [] parts=line.split(" ");

                if(line.startsWith("v")){
                    Vertex v=new Vertex(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                    vertexes.add(v);
                }
                if(line.startsWith("f")){
                    Polygon p = new Polygon(Integer.parseInt(parts[0])-1,Integer.parseInt(parts[1])-1,Integer.parseInt(parts[2])-1);
                    polygons.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Unesite koordinate tocke: ");
        double x=sc.nextInt();
        double y=sc.nextInt();
        double z=sc.nextInt();

        List<Double> A=new ArrayList<>();
        List<Double> B=new ArrayList<>();
        List<Double> C=new ArrayList<>();
        List<Double> D=new ArrayList<>();

        for(Polygon p : polygons) {
            Vertex vertex1 = vertexes.get(p.v1);
            Vertex vertex2 = vertexes.get(p.v1);
            Vertex vertex3 = vertexes.get(p.v1);

            double a = (vertex2.y - vertex1.y) * (vertex3.z - vertex1.z) - (vertex2.z - vertex1.z) * (vertex3.y - vertex1.y);
            double b = (vertex2.z - vertex1.y) * (vertex3.x - vertex1.x) - (vertex2.x - vertex1.x) * (vertex3.z - vertex1.z);
            double c = (vertex2.x - vertex1.x) * (vertex3.y - vertex1.y) - (vertex2.y - vertex1.y) * (vertex3.x - vertex1.x);

            A.add(a);
            B.add(b);
            C.add(c);
            D.add(-vertex1.x * a - vertex1.y * b - vertex1.z * c);

            boolean inside = true;

            for (int i = 0; i < polygons.size(); i++) {
                if ((A.get(i) * x + B.get(i) * y + C.get(i) * z + D.get(i)) > 0) {
                    System.out.println("Tocka je izvan tijela.");
                    inside = false;
                    break;
                }
            }

            if (inside) {
                System.out.println("Tocka je unutar tijela");
            }
        }
    }

    static class Vertex{
        double x;
        double y;
        double z;

        public Vertex(double x, double y, double z){
            this.x=x;
            this.y=y;
            this.z=z;
        }
    }

    static class Polygon{
        int v1;
        int v2;
        int v3;

        public Polygon(int v1, int v2, int v3){
            this.v1=v1;
            this.v2=v2;
            this.v3=v3;
        }
    }
}
