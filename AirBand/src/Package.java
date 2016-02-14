import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class Package {

	public JFrame frame;
	private JComboBox comboBox;
	private JTextField textField;
	
	boolean isAlive = true;
	
	JCheckBox chckbxNewCheckBox;
	JCheckBox checkBox;
	JCheckBox checkBox_1;
	JCheckBox chckbxNewCheckBox_1;
	JCheckBox chckbxNewCheckBox_2;
	JCheckBox chckbxNewCheckBox_3;
	JCheckBox chckbxNewCheckBox_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Package window = new Package();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Package(){
		initialize();
	}

	
	public void initialize(){
		frame = new JFrame();
		frame.setBackground(Color.BLUE);
		frame.getContentPane().setForeground(Color.PINK);
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 600, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 40));
		textField.setToolTipText("How fast the song goes");
		textField.setBounds(50, 578, 114, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(2);
		
		JLabel lblBpm = DefaultComponentFactory.getInstance().createLabel("BPM");
		lblBpm.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblBpm.setBounds(50, 495, 104, 87);
		frame.getContentPane().add(lblBpm);
		
		JLabel lblAirBand = DefaultComponentFactory.getInstance().createLabel("AIR BAND");
		lblAirBand.setToolTipText("Get rekt Ted");
		lblAirBand.setFont(new Font("Informal Roman", Font.BOLD, 99));
		lblAirBand.setBounds(43, 0, 510, 113);
		frame.getContentPane().add(lblAirBand);
		
		JLabel lblSoundCheck = DefaultComponentFactory.getInstance().createLabel("Sound check");
		lblSoundCheck.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSoundCheck.setBounds(84, 102, 213, 50);
		frame.getContentPane().add(lblSoundCheck);
		
		JLabel lblBass = DefaultComponentFactory.getInstance().createLabel("Piano");
		lblBass.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblBass.setBounds(76, 154, 88, 26);
		frame.getContentPane().add(lblBass);
		
		JLabel lblRhythm = DefaultComponentFactory.getInstance().createLabel("Guitar");
		lblRhythm.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblRhythm.setBounds(76, 190, 181, 39);
		frame.getContentPane().add(lblRhythm);
		
		JLabel lblLead = DefaultComponentFactory.getInstance().createLabel("Bass");
		lblLead.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblLead.setBounds(76, 232, 104, 39);
		frame.getContentPane().add(lblLead);
		
		String[] styles = { "Rock", "Synth", "Funk", "Metal", "Classical"};
		JComboBox comboBox_1 = new JComboBox(styles);
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		comboBox_1.setBounds(353, 578, 200, 50);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblStyle = DefaultComponentFactory.getInstance().createLabel("Style");
		lblStyle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblStyle.setBounds(386, 509, 148, 50);
		frame.getContentPane().add(lblStyle);
		
		JButton btnReady = new JButton("Ready!");
		btnReady.setFont(new Font("Informal Roman", Font.BOLD, 50));
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int a = Integer.parseInt(textField.getText());
					if((a <= 90)||(a >= 150)){
						textField.setText("");
						textField.setBackground(Color.RED);
					}else{
						MidiRoutines.setTempo(a);
						frame.setVisible(false);
						frame.dispose();
					}
				}
				catch(Exception ei){
					textField.setText("");
					textField.setBackground(Color.RED);
				}
			}
		});
		btnReady.setBackground(new Color(124, 252, 0));
		btnReady.setForeground(Color.BLACK);
		btnReady.setBounds(329, 281, 213, 124);
		frame.getContentPane().add(btnReady);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Piano");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewJgoodiesLabel.setBounds(76, 277, 114, 39);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Synth");
		lblNewJgoodiesLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewJgoodiesLabel_1.setBounds(76, 414, 98, 39);
		frame.getContentPane().add(lblNewJgoodiesLabel_1);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Violin");
		lblNewJgoodiesLabel_2.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewJgoodiesLabel_2.setBounds(76, 367, 114, 39);
		frame.getContentPane().add(lblNewJgoodiesLabel_2);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("Flute");
		lblNewJgoodiesLabel_3.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewJgoodiesLabel_3.setBounds(76, 326, 127, 35);
		frame.getContentPane().add(lblNewJgoodiesLabel_3);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setBounds(235, 150, 49, 50);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		checkBox = new JCheckBox("");
		checkBox.setEnabled(false);
		checkBox.setBounds(235, 190, 49, 50);
		frame.getContentPane().add(checkBox);
		
		checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(false);
		checkBox_1.setBounds(235, 232, 49, 50);
		frame.getContentPane().add(checkBox_1);
		
		chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setEnabled(false);
		chckbxNewCheckBox_1.setBounds(235, 418, 49, 35);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("");
		chckbxNewCheckBox_2.setEnabled(false);
		chckbxNewCheckBox_2.setBounds(235, 371, 49, 35);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("");
		chckbxNewCheckBox_3.setEnabled(false);
		chckbxNewCheckBox_3.setBounds(235, 281, 49, 35);
		frame.getContentPane().add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("");
		chckbxNewCheckBox_4.setEnabled(false);
		chckbxNewCheckBox_4.setBounds(235, 326, 49, 35);
		frame.getContentPane().add(chckbxNewCheckBox_4);
		
	}
	public void soundCheckUpdate(int id, boolean s){

		switch(id){
			
		case 1:
			chckbxNewCheckBox.setSelected(s);
			break;
		case 2:	
			checkBox.setSelected(s);
			break;
		case 3:
			checkBox_1.setSelected(s);
			break;
		case 4:
			chckbxNewCheckBox_1.setSelected(s);
			break;
		case 5:
			chckbxNewCheckBox_2.setSelected(s);
			break;
		case 6:
			chckbxNewCheckBox_3.setSelected(s);
			break;
		case 7:
			chckbxNewCheckBox_4.setSelected(s);
			break;
		}
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
