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

public class EditDialog extends TitleAreaDialog {

	private Text txtTitle;
	private Text txtAuthors;
	private Label txtId;

	private Long id;
	private String title;
	private String authors;

	public EditDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Edit book from database");
		setMessage("Insert book id, new title and new authors", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createId(container);
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

	private void createId(Composite container) {
		Label lblId = new Label(container, SWT.NONE);
		lblId.setText("Id: ");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;

		txtId = new Label(container, SWT.NONE);
		txtId.setLayoutData(dataFirstName);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {

		if (txtId.getText().isEmpty()) {
			id = null;
		} else {
			id = Long.parseLong(txtId.getText());
		}
		
		title = txtTitle.getText();
		authors = txtAuthors.getText();
	}

	@Override
	protected void okPressed() {
		saveInput();

		if (id == null || title.isEmpty() || authors.isEmpty()) {
			MessageDialog dialog = new MessageDialog(new Shell(), "Error editing book", null,
					"Insert id, title and authors", MessageDialog.ERROR, new String[] { "OK" }, 0);
			dialog.open();
		} else {
			super.okPressed();
		}
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBookTitle(String title) {
		this.title = title;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setTxtId(Long id) {
		txtId.setText(getId().toString());
	}

	public void setTxtTitle(String title) {
		txtTitle.setText(title);
	}

	public void setTxtAuthors(String authors) {
		txtAuthors.setText(authors);
	}

}
