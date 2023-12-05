package xxl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import xxl.exceptions.ImportFileException;
import xxl.exceptions.MissingFileAssociationException;
import xxl.exceptions.UnavailableFileException;
import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;

    private String _filename = "";

    private List<User> _users = new ArrayList<User>();

    private User _activeUser = new User("root");

    /**
     * Saves the serialized application's state into the file associated to the current spreadsheet.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
     * @throws MissingFileAssociationException if the current spreadsheet does not have a file.
     * @throws IOException if there is some error while serializing the state of the spreadsheet to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
        if(_filename == null || _filename.equals("")){
            throw new MissingFileAssociationException();
            
        } try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_spreadsheet);
            _spreadsheet.setChanged(false);
        }
    }

    /**
     * Saves the serialized application's state into the specified file. The current spreadsheet is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current spreadsheet does not have a file.
     * @throws IOException if there is some error while serializing the state of the spreadsheet to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
        _filename = filename;
        save();
    }

    /**
     * Loads a file and inicializes everything that is requested to create
     * 
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException, ClassNotFoundException, IOException {
        // FIXME implement serialization method
        _filename = filename;
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){
            _spreadsheet = (Spreadsheet) ois.readObject();
            _spreadsheet.setChanged(false);
        } catch (FileNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Read text input file and create domain entities
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */

    public void importFile(String filename) throws ImportFileException {
        // FIXME open import file and feed entries to new spreadsheet (in a cycle)
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            int lines = Integer.parseInt(reader.readLine().split("=")[1]);
            int columns = Integer.parseInt(reader.readLine().split("=")[1]);
            
            createNewSpreadsheet(lines, columns);

            String line;

            while((line = reader.readLine()) != null){

                String[] fields = line.split("\\|", -1);

                try {
                    //       each entry is inserted with:
                    _spreadsheet.insertContents(fields[0], fields[1]);
                } catch (UnrecognizedEntryException e){
                    e.printStackTrace();
                }
            }

	    // FIXME more exceptions before or after?
        } catch (IOException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(filename, e);
        }
    }

    /**
     * Creates a new spreadsheet with the arguments as limits
     *
     * @param lines number of lines
     * @param columns number of columns
     *
     */

    public void createNewSpreadsheet(int lines, int columns){
        _spreadsheet = new Spreadsheet(lines, columns);
        _filename = "";
    }

    /**
     * Returns the pointer of the spreadsheet in the calculator
     *
     * @param lines number of lines
     * @param columns number of columns
     * @return _spreadsheet pointer to spreadsheet
     */

    public Spreadsheet getSpreadsheet() {
        return _spreadsheet;
    }

    /**
     * Verifies if a spreadsheet has changed
     * 
     * @return boolean
     */

    public boolean changed(){
        return _spreadsheet.changed();
    }

    /**
     * Creates a new user
     * 
     * @param name name of the user
     */

    public void createUser(String name){
        _users.add(new User(name));
    }
    
    /**
     * Change the active user to a new one
     * 
     * @param user new user
     */

    public void changeActiveUser(User user){
        _activeUser = user;
    }
    

}
