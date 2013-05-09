package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.view.AbstractViewController;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

/**
 * The main window inside which states are rendered
 */

@SuppressWarnings("serial")
public class ViewContainer extends JFrame {
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 620;
	private AbstractViewController currentViewController;
	
	public ViewContainer() {
		super("Tidslampe");
		
		this.setResizable(false);
		this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setIconImage(SpriteHandler.getInstance().get("alien.png").getImage());
		this.setVisible(true);
		this.setFocusable(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbedPane, BorderLayout.NORTH);
		this.requestFocusInWindow();
		//this.addKeyListener(Input.getInstance());
	}

	/**
	 * Changes the active view state
	 * 
	 * @param state
	 *            The new state to change to
	 */
	public void setViewState(AbstractViewController viewController) {
		if (this.currentViewController != null) {
			this.currentViewController.getViewState().dispose();
		}

		viewController.initialize();
		
		this.setContentPane(viewController.getViewState());

		this.pack();
		
		this.currentViewController = viewController;
	}

	public AbstractViewController getCurrentViewController() {
		return currentViewController;
	}
}
