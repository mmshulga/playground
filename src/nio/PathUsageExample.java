package nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUsageExample {
    public static void main(String[] args) {
        String[] paths = new String[] {
                "/Users/mmshulga/Desktop/",
                "/Users/mmshulga/Desktop/test/",
                "in.pdf",
                "../out.pdf"
        };

        for (int i = 0; i < paths.length; ++i) {
            Path p1 = Paths.get(paths[i]);
            for (int j = 0; j < paths.length; ++j) {
                Path p2 = Paths.get(paths[j]);
                System.out.println("p1 = " + p1);
                System.out.println("p2 = " + p2);
                try {
                    System.out.println("p1.resolve(p2) = " + p1.resolve(p2));
                } catch (Exception e) {
                    System.out.println("Exception thrown in resolve");
                }
                try {
                    System.out.println("p1.relativize(p2) = " + p1.relativize(p2));
                } catch (Exception e) {
                    System.out.println("Exception thrown in relativize");
                }

                System.out.println();
                System.out.println();
            }
        }
    }
}
