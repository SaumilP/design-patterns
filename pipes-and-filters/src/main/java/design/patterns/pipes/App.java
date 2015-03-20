package design.patterns.pipes;

import design.patterns.pipes.models.DataContext;
import design.patterns.pipes.stages.CeasarCipher;
import design.patterns.pipes.stages.FileLoader;
import design.patterns.pipes.stages.Printer;
import design.patterns.pipes.stages.Stage;

/**
 * Pipes-And-Filters Design Pattern Client
 */
public class App {
    public static void main(String[] args) throws Exception {
        Stage loadFile = new FileLoader("SecretMessage.txt");
        DataContext inputContext = new DataContext();

        Pipeline.create()
                .use(loadFile)
                .use(new Printer("FileLoader"))
                .use(new CeasarCipher(13))
                .use(new Printer("Cipher"))
                .use(new CeasarCipher(13))
                .use(new Printer("Decipher"))
                .execute(inputContext);
    }
}
