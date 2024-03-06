package strategy;

import java.io.File;

public class SaveManagerFile implements SaveAndOpenFile {

	private SaveAndOpenFile saveAndOpenFile;
	
	public SaveManagerFile(SaveAndOpenFile saveAndOpenFile) {
		this.saveAndOpenFile=saveAndOpenFile;
	}
	
	@Override
	public void save(Object o, File f) {
		saveAndOpenFile.save(o, f);
		
	}
	@Override
	public void open(File file) {
		saveAndOpenFile.open(file);
	}

}
