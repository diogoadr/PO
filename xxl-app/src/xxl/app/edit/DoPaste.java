package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;

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
        try{
            _receiver.paste(stringField("adress"));
        } catch (UnrecognizedEntryException e){
            throw new InvalidCellRangeException(stringField("adress"));
        }
    }

}
