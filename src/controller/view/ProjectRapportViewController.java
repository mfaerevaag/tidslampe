package controller.view;

import model.Project;
import persistency.Database;
import view.ViewContainer;
import view.state.AbstractViewState;
import view.state.ProjectRapportViewState;
import controller.ControllerCollection;
import controller.action.ChangeViewAction;
import factory.ViewControllerFactory;


public class ProjectRapportViewController extends AbstractViewController {
	private ProjectRapportViewState viewState;
	private Project project;

	public ProjectRapportViewController(Database database,
			ViewContainer viewContainer, 
			ControllerCollection controllers,
			int projectId) {
		super(database, viewContainer, controllers);
		this.project = this.database.project().read(projectId);
	}

	@Override
	public AbstractViewState getViewState() {
		return this.viewState;
	}

	@Override
	public void initialize() {
		this.viewState = new ProjectRapportViewState(this.project);
		
		this.viewState.getBackButton().addActionListener(new ChangeViewAction(this.viewContainer, ViewControllerFactory.CreateProjectMaintainanceViewController(this.project.getId())));
		
	}

}