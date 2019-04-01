package jdbcdao;

import jdbcdao.config.ConfigData;
import jdbcdao.runner.TextbookAppRunner;

public class Main {
    public static void main(String[] args) {
        TextbookAppRunner textbookAppRunner = new TextbookAppRunner(ConfigData.url, ConfigData.user, ConfigData.pass, ConfigData.dataTable);
        textbookAppRunner.run();
    }
}
