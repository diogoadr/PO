package xxl.app.main;

import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;
// FIXME import classes
import xxl.exceptions.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

    DoOpen(Calculator receiver) {
        super(Label.OPEN, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        try {
            if (_receiver.getSpreadsheet() != null && _receiver.changed() && Form.confirm(Prompt.saveBeforeExit())) {
                DoSave save = new DoSave(_receiver);
                save.execute();
            }

            _receiver.load(Form.requestString(Prompt.openFile()));
            
        } catch (UnavailableFileException | ClassNotFoundException | IOException e){
            throw new FileOpenFailedException(e);
        }

    }

}
