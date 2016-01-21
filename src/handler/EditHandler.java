package handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import dialog.EditDialog;
import model.BookTo;
import model.RestClient;

public class EditHandler extends AbstractHandler implements IHandler {

	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();

		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			for (Iterator<BookTo> iterator = structuredSelection.iterator(); iterator.hasNext();) {
				BookTo book = iterator.next();
				EditDialog dialog = new EditDialog(new Shell());
				dialog.create();

				dialog.setId(book.getId());
				dialog.setTxtId(book.getId());
				dialog.setBookTitle(book.getTitle());
				dialog.setTxtTitle(book.getTitle());
				dialog.setAuthors(book.getAuthors());
				dialog.setTxtAuthors(book.getAuthors());
				if (dialog.open() == Window.OK) {
					RestClient.put(dialog.getId(), dialog.getTitle(), dialog.getAuthors());
				}
			}
		}

		return null;
	}

}
