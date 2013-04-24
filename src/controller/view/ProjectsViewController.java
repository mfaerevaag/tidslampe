package controller.view;

import model.Developer;
import model.Project;
import persistency.Database;
import utils.ActionUtils;
import utils.Dialog;
import utils.DialogChoice;
import view.ViewContainer;
import view.state.AbstractViewState;
import view.state.ProjectsViewState;
import controller.ControllerCollection;
import controller.action.ChangeViewAction;
import factory.ViewControllerFactory;

public class ProjectsViewController extends AbstractViewController {

	private ProjectsViewState viewState;

	public ProjectsViewController(Database database, ViewContainer viewContainer, ControllerCollection controllers) {
		super(database, viewContainer, controllers);
	}

	@Override
	public AbstractViewState getViewState() {
		return viewState;
	}

	@Override
	public void initialize() {
		this.viewState = new ProjectsViewState(); 
		
		ActionUtils.addListener(this.viewState.getDeleteButton(), this, "deleteSelectedProject");
		ActionUtils.addListener(this.viewState.getCreateButton(), this, "createNewProject");
		this.viewState.getBackButton().addActionListener(new ChangeViewAction(this.viewContainer, ViewControllerFactory.CreateMenuViewController()));

		this.fillProjectList();
		this.fillManagerList();
	}

	private void fillProjectList() {
		this.viewState.setProjects(this.database.project().readAll());
	}
	
	private void fillManagerList() {
		this.viewState.setManagers(this.database.developer().readAll());
	}
	
	public void createNewProject() {
		String nameInput = this.viewState.getNameInput().trim();
		int hourBudgetInput = this.viewState.getHourBudgetInput();
		int deadlineInput = this.viewState.getDeadlineInput();
		Developer managerInput = this.viewState.getManagerInput();
		
		if (nameInput.length() == 0 ||
				hourBudgetInput < 0 ||
				deadlineInput < 0 ||
				managerInput == null) {
			Dialog.message("You must fill out all fields");
			return;
		}
		
		if (this.database.project().create(nameInput, hourBudgetInput, deadlineInput, managerInput) == null) {
			Dialog.message("Could not create project");
		}
		this.fillProjectList();
	}
	
	public void deleteSelectedProject() {
		Project sel = this.viewState.getSelectedProject();
		if (sel == null) 
			return;
		
		DialogChoice confirm = Dialog.confirm(String.format("Really delete %s?", sel.getName()));
		if (confirm == DialogChoice.Yes) {
			this.database.project().delete(sel.getId());
			this.fillProjectList();
		}
	}

}
