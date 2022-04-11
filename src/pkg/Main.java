package pkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private JLabel titleLabel;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu menuNew;
	private JMenu menuSave;
	private JMenu menuLoad;
	private JPanel southPanel;
	private JLabel testLabel = new JLabel("test");
	private ImageIcon backgroundImageIcon;

	public void setNew() {
		backgroundImageIcon = new ImageIcon("bg.png");
		southPanel = new JPanel();
		menuBar = new JMenuBar();
		menuNew = new JMenu("NEW");
		menuSave = new JMenu("SAVE");
		menuLoad = new JMenu("LOAD");
		titleLabel = new JLabel("Title");
		textArea = new JTextArea("Test");
		centerPanel = new JPanel();
		scrollPane = new JScrollPane(textArea);
	}

	public void setMenuBar() {
		menuBar.add(menuNew);
		menuBar.add(menuSave);
		menuBar.add(menuLoad);
	}

	public void setComponents() {
		testLabel.setIcon(backgroundImageIcon);
		titleLabel.setForeground(Color.white);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		centerPanel.add(titleLabel, BorderLayout.NORTH);
		centerPanel.setBackground(Color.darkGray);
		centerPanel.setSize(400, 400);
		southPanel.add(testLabel);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		setJMenuBar(menuBar);
	}

	public void setOptions() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("notepad.png");
		setIconImage(img);
		setTitle("¸Þ¸ðÀå");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setLayouts() {
		setLayout(new BorderLayout());
		southPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new BorderLayout());
	}

	public void setEvents() {
		menuSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(Main.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println(selectedFile);
					PrintWriter pw = null;
					try {
						pw = new PrintWriter(selectedFile);
						StringBuffer sb = new StringBuffer(textArea.getText());
						pw.println(sb.toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					} finally {
						pw.close();
					}

				}
			}
		});

		menuLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(Main.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println(selectedFile);
					Scanner sc = null;
					try {
						sc = new Scanner(selectedFile);
						StringBuffer text = new StringBuffer("");
						while (sc.hasNext()) {
							text.append(sc.next());
							System.out.println(text);
							textArea.setText(text.toString());
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					} finally {
						sc.close();
					}

				}
			}
		});
		menuNew.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
			}

		});
	}

	public Main() {
		setNew();
		setMenuBar();
		setLayouts();
		setEvents();
		setComponents();
		setOptions();
	}

	public static void main(String[] args) {
		new Main();
	}
}
