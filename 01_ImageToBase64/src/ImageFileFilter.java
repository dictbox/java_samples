import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter {

    private final String[] imgFileExtensions = new String[]{"jpg", "png", "gif"};

    @Override
    public boolean accept(File pathname) {
        for (String extension : imgFileExtensions) {
            if (pathname.getName().endsWith(extension)) return true;
        }
        return false;
    }
}
