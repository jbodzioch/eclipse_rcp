package dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddDialog extends TitleAreaDialog {

	private Text txtTitle;
	private Text txtAuthors;

	private String title;
	private String authors;

	public AddDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Add new book to database");
		setMessage("Insert title and authors", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createTitle(container);
		createAuthors(container);

		return area;
	}

	private void createTitle(Composite container) {
		Label lbtFirstName = new Label(container, SWT.NONE);
		lbtFirstName.setText("Title: ");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;

		txtTitle = new Text(container, SWT.BORDER);
		txtTitle.setLayoutData(dataFirstName);
	}

	private void createAuthors(Composite container) {
		Label lbtLastName = new Label(container, SWT.NONE);
		lbtLastName.setText("Authors: ");

		GridData dataLastName = new GridData();
		dataLastName.grabExcessHorizontalSpace = true;
		dataLastName.horizontalAlignment = GridData.FILL;
		txtAuthors = new Text(container, SWT.BORDER);
		txtAuthors.setLayoutData(dataLastName);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		title = txtTitle.getText();
		authors = txtAuthors.getText();
	}

	@Override
	protected void okPressed() {
		saveInput();

		if (title.isEmpty() || authors.isEmpty()) {
			MessageDialog dialog = new MessageDialog(new Shell(), "Error adding book", null, "Insert title and authors",
					MessageDialog.ERROR, new String[] { "OK" }, 0);
			dialog.open();
		} else {
			super.okPressed();
		}
	}

	public String getTitle() {
		return title;
	}

	public String getAuthors() {
		return authors;
	}
}
