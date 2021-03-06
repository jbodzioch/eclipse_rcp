package handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import dialog.AddDialog;
import model.BookTo;
import model.DataProvider;
import model.RestClient;

public class AddHandler extends AbstractHandler implements IHandler {
	
	DataProvider model = DataProvider.getInstance();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		AddDialog dialog = new AddDialog(new Shell());
		dialog.create();
		if (dialog.open() == Window.OK) {
			RestClient.add(dialog.getTitle(), dialog.getAuthors());
			model.setBooks(RestClient.search(""));
		}
		
		return null;
	}

}
