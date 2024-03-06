package strategy;

import java.io.File;

public interface SaveAndOpenFile {
	
	void save(Object o,File f);
	void open(File f);
}
