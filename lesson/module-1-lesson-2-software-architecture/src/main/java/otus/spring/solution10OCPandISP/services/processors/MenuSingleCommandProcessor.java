package otus.spring.solution10OCPandISP.services.processors;


import otus.spring.solution10OCPandISP.services.menu.MenuOption;

public interface MenuSingleCommandProcessor {
    void processCommand();
    MenuOption getProcessedCommandOption();
}
