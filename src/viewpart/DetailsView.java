package viewpart;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import model.BookTo;

public class DetailsView extends ViewPart {

	private ISelectionListener listener;

	public void createPartControl(Composite parent) {

		Label viewer = new Label(parent, SWT.BORDER);
		viewer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		listener = new ISelectionListener() {
			public void selectionChanged(IWorkbenchPart part, ISelection sel) {
				if (!(sel instanceof IStructuredSelection))
					return;
				IStructuredSelection ss = (IStructuredSelection) sel;
				Object o = ss.getFirstElement();
				if (o instanceof BookTo)
					viewer.setText(o.toString());
			}
		};
		getSite().getPage().addSelectionListener(listener);
	}

	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
	}

	public DetailsView() {
	}

	@Override
	public void setFocus() {

	}

}
