package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
// FIXME import classes

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

    DoPaste(Spreadsheet receiver) {
        super(Label.PASTE, receiver);
        addStringField("adress", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        // FIXME implement command
    }

}
