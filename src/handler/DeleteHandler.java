package handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import model.BookTo;
import model.RestClient;

public class DeleteHandler extends AbstractHandler implements IHandler {

	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();

		String deletedIds = "";

		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			for (Iterator<BookTo> iterator = structuredSelection.iterator(); iterator.hasNext();) {
				BookTo book = iterator.next();
				RestClient.delete(book.getId());
				deletedIds += book.getId().toString() + ", ";
			}
			
			deletedIds = deletedIds.substring(0, deletedIds.length() - 2);
			MessageDialog dialog = new MessageDialog(new Shell(), "Book deleted", null,
					"Deleted book(s) with id(s): " + deletedIds, MessageDialog.INFORMATION, new String[] { "OK" }, 0);
			dialog.open();
		}

		return null;
	}

}
