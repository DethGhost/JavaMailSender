package org.ua.deth.javamailsender.init;

import java.io.IOException;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
class OpenUrlOnStartUp {

    private static final String url = "http://localhost:8080";
    static final Runtime rt = Runtime.getRuntime();
    private static final String os = System.getProperty("os.name").toLowerCase();

    static void openUrl() throws IOException {
        if (os.indexOf("win") >= 0) { //Start on Windows

            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else if (os.indexOf("mac") >= 0) {//Start on mac

            rt.exec("open" + url);
        } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {// Start on Unix/Linux system
            String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                    "netscape","opera","links","lynx"};

            StringBuffer cmd = new StringBuffer();
            for (int i=0; i<browsers.length; i++)
                cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");

            rt.exec(new String[] { "sh", "-c", cmd.toString() });
        }

    }
}
