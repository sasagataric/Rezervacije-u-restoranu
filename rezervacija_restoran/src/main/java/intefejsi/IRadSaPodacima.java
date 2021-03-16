package intefejsi;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IRadSaPodacima {

	void saveData() throws FileNotFoundException, IOException, ClassNotFoundException;
	void loadData() throws IOException, ClassNotFoundException;
}
