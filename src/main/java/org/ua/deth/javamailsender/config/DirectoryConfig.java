package org.ua.deth.javamailsender.config;

import java.io.File;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public class DirectoryConfig {

    private final String dirToCreate = ".mailApp";

    public DirectoryConfig() {
        createAppDirectory();
    }

    private void createAppDirectory() {
        boolean mkdir = false;
        // for windows
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            mkdir = new File(System.getProperty("user.home") + "\\" + dirToCreate).mkdir();
        // for linux/unix
        } else if (System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux")) {
            mkdir = new File(System.getProperty("user.home") + "/" + dirToCreate).mkdir();
        }
        System.out.println(mkdir ? "Created dir: " + System.getProperty("user.home") + dirToCreate : "Dir exist or you don't have permissions");
    }

    public void getHome() {
        System.err.println(System.getProperty("user.home"));
    }
}
