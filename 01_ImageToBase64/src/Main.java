import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                File file = new File(args[0]);

                ArrayList<File> images = new ArrayList<>();

                if (file.isDirectory()) {
                    images.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles(new ImageFileFilter()))));
                } else if (file.exists()) {
                    images.add(file);
                }

                for (File image : images) {
                    InputStream in = new FileInputStream(image);
                    byte[] data = new byte[in.available()];
                    //noinspection ResultOfMethodCallIgnored
                    in.read(data);
                    in.close();
                    System.out.println(image.getName() + ":");
                    System.out.println(Base64.getEncoder().encodeToString(data));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
