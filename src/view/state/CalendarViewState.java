package view.state;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import utils.Dialog;

import model.Activity;
import model.Developer;
import model.Project;
import model.TimeEntry;
import model.gui.ActivityListRenderer;
import model.gui.GenericComboBoxModel;
import model.gui.DeveloperListRenderer;
import model.gui.ProjectListRenderer;

import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class CalendarViewState extends AbstractViewState {

	private JButton btnBack;
	private JButton btnAdd;
	private JPanel toolbar;
	private JPanel legend;
	private JPanel day1;
	private JPanel day2;
	private JPanel day3;
	private JPanel day4;
	private JPanel day5;
	private JPanel day6;
	private JPanel day7;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblDay1;
	private JLabel lblDay2;
	private JLabel lblDay3;
	private JLabel lblDay4;
	private JLabel lblDay5;
	private JLabel lblDay6;
	private JLabel lblDay7;
	private JPanel inputPanelTop;
	private JLabel lblNewLabel;
	private JLabel label;
	private JPanel inputPanelBottom;
	private JLabel lblDeveloperName;
	private JLabel lblProject;
	private JLabel lblActivity;
	private JLabel lblDate;
	private JTextField txtDate;
	private JTextField txtEnd;
	private JTextField txtStart;
	private JComboBox<Project> cmbProject;
	private JComboBox<Activity> cmbActivity;
	private JLabel[] dayLabels;
	private JPanel[] dayPanels;
	private JButton btnPrevious;
	private JButton btnNext;

	public CalendarViewState() {
		setMinimumSize(new Dimension(500, 580));
		setMaximumSize(new Dimension(500, 580));

		JPanel weekPanel = new JPanel();
		weekPanel.setPreferredSize(new Dimension(500, 300));
		weekPanel.setMinimumSize(new Dimension(500, 300));
		weekPanel.setMaximumSize(new Dimension(500, 300));
		GridBagLayout gbl_weekPanel = new GridBagLayout();
		gbl_weekPanel.columnWidths = new int[] { 71, 71, 71, 71, 71, 71, 71, 0 };
		gbl_weekPanel.rowHeights = new int[] { 300, 0 };
		gbl_weekPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_weekPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		weekPanel.setLayout(gbl_weekPanel);

		day1 = new JPanel();
		day1.setBackground(Color.GRAY);
		day1.setPreferredSize(new Dimension(70, 300));
		day1.setMinimumSize(new Dimension(70, 300));
		day1.setLayout(null);
		GridBagConstraints gbc_day1 = new GridBagConstraints();
		gbc_day1.fill = GridBagConstraints.BOTH;
		gbc_day1.insets = new Insets(0, 0, 0, 1);
		gbc_day1.gridx = 0;
		gbc_day1.gridy = 0;
		weekPanel.add(day1, gbc_day1);
		
		day2 = new JPanel();
		day2.setBackground(Color.GRAY);
		day2.setPreferredSize(new Dimension(70, 300));
		day2.setMinimumSize(new Dimension(70, 300));
		day2.setLayout(null);
		GridBagConstraints gbc_day2 = new GridBagConstraints();
		gbc_day2.fill = GridBagConstraints.BOTH;
		gbc_day2.insets = new Insets(0, 0, 0, 1);
		gbc_day2.gridx = 1;
		gbc_day2.gridy = 0;
		weekPanel.add(day2, gbc_day2);

		day3 = new JPanel();
		day3.setBackground(Color.GRAY);
		day3.setPreferredSize(new Dimension(70, 300));
		day3.setMinimumSize(new Dimension(70, 300));
		day3.setLayout(null);
		GridBagConstraints gbc_day3 = new GridBagConstraints();
		gbc_day3.fill = GridBagConstraints.BOTH;
		gbc_day3.insets = new Insets(0, 0, 0, 1);
		gbc_day3.gridx = 2;
		gbc_day3.gridy = 0;
		weekPanel.add(day3, gbc_day3);

		day4 = new JPanel();
		day4.setBackground(Color.GRAY);
		day4.setPreferredSize(new Dimension(70, 300));
		day4.setMinimumSize(new Dimension(70, 300));
		day4.setLayout(null);
		GridBagConstraints gbc_day4 = new GridBagConstraints();
		gbc_day4.fill = GridBagConstraints.BOTH;
		gbc_day4.insets = new Insets(0, 0, 0, 1);
		gbc_day4.gridx = 3;
		gbc_day4.gridy = 0;
		weekPanel.add(day4, gbc_day4);

		day5 = new JPanel();
		day5.setBackground(Color.GRAY);
		day5.setPreferredSize(new Dimension(70, 300));
		day5.setMinimumSize(new Dimension(70, 300));
		day5.setLayout(null);
		GridBagConstraints gbc_day5 = new GridBagConstraints();
		gbc_day5.fill = GridBagConstraints.BOTH;
		gbc_day5.insets = new Insets(0, 0, 0, 1);
		gbc_day5.gridx = 4;
		gbc_day5.gridy = 0;
		weekPanel.add(day5, gbc_day5);

		day6 = new JPanel();
		day6.setBackground(Color.GRAY);
		day6.setPreferredSize(new Dimension(70, 300));
		day6.setMinimumSize(new Dimension(70, 300));
		day6.setLayout(null);
		GridBagConstraints gbc_day6 = new GridBagConstraints();
		gbc_day6.fill = GridBagConstraints.BOTH;
		gbc_day6.insets = new Insets(0, 0, 0, 1);
		gbc_day6.gridx = 5;
		gbc_day6.gridy = 0;
		weekPanel.add(day6, gbc_day6);

		day7 = new JPanel();
		day7.setBackground(Color.GRAY);
		day7.setPreferredSize(new Dimension(70, 300));
		day7.setMinimumSize(new Dimension(70, 300));
		day7.setLayout(null);
		GridBagConstraints gbc_day7 = new GridBagConstraints();
		gbc_day7.insets = new Insets(0, 0, 0, 1);
		gbc_day7.fill = GridBagConstraints.BOTH;
		gbc_day7.gridx = 6;
		gbc_day7.gridy = 0;
		weekPanel.add(day7, gbc_day7);

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel topPanel = new JPanel();
		add(topPanel);
		topPanel.setLayout(new GridLayout(0, 1, 0, 0));

		toolbar = new JPanel();
		topPanel.add(toolbar);
		toolbar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.btnBack = new JButton("Back to menu");
		toolbar.add(btnBack);
		
		JPanel header = new JPanel();
		topPanel.add(header);
		header.setBounds(new Rectangle(0, 0, 500, 0));
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.lblDeveloperName = new JLabel();
		lblDeveloperName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		header.add(lblDeveloperName);
		lblDeveloperName.setText("Test");
		add(weekPanel);
		
		legend = new JPanel();
		legend.setPreferredSize(new Dimension(500, 20));
		legend.setMinimumSize(new Dimension(500, 20));
		legend.setMaximumSize(new Dimension(500, 20));
		add(legend);
		GridBagLayout gbl_legend = new GridBagLayout();
		gbl_legend.columnWidths = new int[] {71, 71, 71, 71, 71, 71, 71};
		gbl_legend.rowHeights = new int[] {300, 300, 0};
		gbl_legend.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_legend.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		legend.setLayout(gbl_legend);
		
		panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(70, 70));
		panel_1.setPreferredSize(new Dimension(70, 70));
		panel_1.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 1);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		legend.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay1 = new JLabel("Mon 13/4");
		lblDay1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(lblDay1);
		
		panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(70, 70));
		panel_2.setPreferredSize(new Dimension(70, 70));
		panel_2.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 1);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		legend.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay2 = new JLabel("Mon 13/4");
		lblDay2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay2.setAlignmentX(0.5f);
		panel_2.add(lblDay2);
		
		panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(70, 70));
		panel_4.setPreferredSize(new Dimension(70, 70));
		panel_4.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 0, 1);
		gbc_panel_4.gridx = 2;
		gbc_panel_4.gridy = 0;
		legend.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay3 = new JLabel("Mon 13/4");
		lblDay3.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay3.setAlignmentX(0.5f);
		panel_4.add(lblDay3);
		
		panel_5 = new JPanel();
		panel_5.setMaximumSize(new Dimension(70, 70));
		panel_5.setPreferredSize(new Dimension(70, 70));
		panel_5.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 0, 1);
		gbc_panel_5.gridx = 3;
		gbc_panel_5.gridy = 0;
		legend.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay4 = new JLabel("Mon 13/4");
		lblDay4.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay4.setAlignmentX(0.5f);
		panel_5.add(lblDay4);
		
		panel_6 = new JPanel();
		panel_6.setMaximumSize(new Dimension(70, 70));
		panel_6.setPreferredSize(new Dimension(70, 70));
		panel_6.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 0, 1);
		gbc_panel_6.gridx = 4;
		gbc_panel_6.gridy = 0;
		legend.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay5 = new JLabel("Mon 13/4");
		lblDay5.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay5.setAlignmentX(0.5f);
		panel_6.add(lblDay5);
		
		panel_7 = new JPanel();
		panel_7.setMaximumSize(new Dimension(70, 70));
		panel_7.setPreferredSize(new Dimension(70, 70));
		panel_7.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 0, 1);
		gbc_panel_7.gridx = 5;
		gbc_panel_7.gridy = 0;
		legend.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay6 = new JLabel("Mon 13/4");
		lblDay6.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay6.setAlignmentX(0.5f);
		panel_7.add(lblDay6);
		
		panel_8 = new JPanel();
		panel_8.setMaximumSize(new Dimension(70, 70));
		panel_8.setPreferredSize(new Dimension(70, 70));
		panel_8.setMinimumSize(new Dimension(70, 70));
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 0, 1);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 6;
		gbc_panel_8.gridy = 0;
		legend.add(panel_8, gbc_panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDay7 = new JLabel("Mon 13/4");
		lblDay7.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay7.setAlignmentX(0.5f);
		panel_8.add(lblDay7);
		
		JPanel controls = new JPanel();
		add(controls);
		
		btnPrevious = new JButton("Previous week");
		controls.add(btnPrevious);
		
		btnNext = new JButton("Next week");
		controls.add(btnNext);
		
		inputPanelTop = new JPanel();
		add(inputPanelTop);
		
		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		inputPanelTop.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setColumns(7);
		inputPanelTop.add(txtDate);
		
		label = new JLabel("Start time");
		label.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		inputPanelTop.add(label);
		
		txtStart = new JTextField();
		txtStart.setColumns(5);
		inputPanelTop.add(txtStart);
		
		lblNewLabel = new JLabel("End time");
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		inputPanelTop.add(lblNewLabel);
		
		txtEnd = new JTextField();
		inputPanelTop.add(txtEnd);
		txtEnd.setColumns(5);
		
		inputPanelBottom = new JPanel();
		add(inputPanelBottom);
		
		cmbProject = new JComboBox<Project>();
		cmbProject.setMinimumSize(new Dimension(80, 25));
		cmbProject.setMaximumSize(new Dimension(200, 25));
				
		lblProject = new JLabel("Project");
		lblProject.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		inputPanelBottom.add(lblProject);
		inputPanelBottom.add(cmbProject);
		
		lblActivity = new JLabel("Activity");
		lblActivity.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		inputPanelBottom.add(lblActivity);
		
		cmbActivity = new JComboBox<Activity>();
		cmbActivity.setMaximumSize(new Dimension(150, 25));
		cmbActivity.setMinimumSize(new Dimension(80, 25));
		cmbActivity.setBounds(new Rectangle(0, 0, 80, 0));
		inputPanelBottom.add(cmbActivity);
		
		btnAdd = new JButton("Add");
		
		inputPanelBottom.add(btnAdd);
		
		// Create day label list
		this.dayLabels = new JLabel[] {this.lblDay1, this.lblDay2, this.lblDay3, this.lblDay4, this.lblDay5, this.lblDay6, this.lblDay7};
		this.dayPanels = new JPanel[] {this.day1, this.day2, this.day3, this.day4, this.day5, this.day6, this.day7};
	}
	
	
	// Add an entry to the calendar view
	public void addTimeEntry(final TimeEntry timeEntry, int day) {

		int fromMinute = timeEntry.getStartDate().get(Calendar.HOUR_OF_DAY) * 60 + timeEntry.getStartDate().get(Calendar.MINUTE);

		float minuteHeight =  300f / (24f * 60f);

		JPanel timeEntryPanel = new JPanel();
		timeEntryPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dialog.message("" + timeEntry.getId());
			}
		});
		
		timeEntryPanel.setBackground(Color.ORANGE);
		int durationInMinutes = timeEntry.getDurationInMinutes();
		
		timeEntryPanel.setBounds(0, (int)(minuteHeight * fromMinute), 70, (int)(minuteHeight * (fromMinute + durationInMinutes)));
		timeEntryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JLabel projectLabel = new JLabel("" + timeEntry.getActivity().getProject().getId());
		timeEntryPanel.add(projectLabel);
		
		this.dayPanels[day].add(timeEntryPanel);
	}

	public void clearTimeEntries() {
		for (JPanel dayPanel : this.dayPanels) {
			dayPanel.removeAll();
			dayPanel.revalidate();
			dayPanel.repaint();
		}
	}
	
	// Component getters
	public JComboBox<Project> getProjectComboBox() {
		return this.cmbProject;
	}

	public JLabel[] getDayLabels() {
		return this.dayLabels;
	}

	public JButton getBackButton() {
		return this.btnBack;
	}

	public JButton getAddButton() {
		return this.btnAdd;
	}

	public JButton getNextButton() {
		return this.btnNext;
	}

	public JButton getPrevButton() {
		return this.btnPrevious;
	}
	// Model setters
	public void setProjects(List<Project> projects) {
		this.cmbProject.setModel(new GenericComboBoxModel<Project>(projects));
		this.cmbProject.setRenderer(new ProjectListRenderer());
	}

	public void setActivities(List<Activity> activities) {
		this.cmbActivity.setModel(new GenericComboBoxModel<Activity>(activities));
		this.cmbActivity.setRenderer(new ActivityListRenderer());
	}

	public void setDeveloperName(String name) {
		this.lblDeveloperName.setText(name);
	}

	public void setDateString(String dateString) {
		this.txtDate.setText(dateString);
	}

	public void setStartTime(String startTime) {
		this.txtStart.setText(startTime);
	}

	public void setEndTime(String endTime) {
		this.txtEnd.setText(endTime);
	}

	// Model getters
	public Project getSelectedProject() {
		return (Project) this.cmbProject.getSelectedItem();
	}
	
	public Activity getSelectedActivity() {
		return (Activity) this.cmbActivity.getSelectedItem();
	}

	public String getDateString() {
		return this.txtDate.getText();
	}

	public String getStartTimeString() {
		return this.txtStart.getText();
	}

	public String getEndTimeString() {
		return this.txtEnd.getText();
	}
}