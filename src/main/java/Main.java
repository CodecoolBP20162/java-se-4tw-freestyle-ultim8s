import com.codecool.ulti.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by peter on 2017.05.22..
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        logger.info("Ulti game started.");
        controller.play();
    }
}
