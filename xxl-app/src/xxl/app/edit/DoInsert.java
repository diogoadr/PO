package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

    DoInsert(Spreadsheet receiver) {
        super(Label.INSERT, receiver);
        addStringField("adress", Prompt.address());
        addStringField("content", Prompt.content());
        
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.insertContent(stringField("adress"), stringField("content"));
        } catch (UnrecognizedEntryException e){
            throw new InvalidCellRangeException(stringField("adress"));
        }
    }

}
