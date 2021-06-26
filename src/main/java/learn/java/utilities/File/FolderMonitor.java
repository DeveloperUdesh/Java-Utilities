package learn.java.utilities.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;

public class FolderMonitor {
    private static final Logger LOG = LoggerFactory.getLogger(FolderMonitor.class);
    private static final String FOLDER = "";

    public static void main(String[] args) {
        final long interval = 5 * 1000;
        File folder = new File(FOLDER);
        LOG.info("{} files present in the directory : {} ", Objects.requireNonNull(folder.listFiles()).length, folder.getAbsolutePath());
        if (!folder.exists()) {
            throw new RuntimeException(folder.getAbsolutePath() + " is invalid directory\folder");
        }
        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        FileAlterationListener listener = new FileAlterationListener() {
            @Override
            public void onStart(FileAlterationObserver fileAlterationObserver) {

            }

            @Override
            public void onDirectoryCreate(File file) {

            }

            @Override
            public void onDirectoryChange(File file) {

            }

            @Override
            public void onDirectoryDelete(File file) {

            }

            @Override
            public void onFileCreate(File file) {

            }

            @Override
            public void onFileChange(File file) {

            }

            @Override
            public void onFileDelete(File file) {

            }

            @Override
            public void onStop(FileAlterationObserver fileAlterationObserver) {

            }
        };

        observer.addListener(listener);
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

    }
}
