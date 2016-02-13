import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Choice;
import java.awt.BorderLayout;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Checkbox;


public class Package {

	private JFrame frame;
	private JComboBox comboBox;
	private JTextField textField;

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

	/**
	 * Create the application.
	 */
	public Package() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 40));
		textField.setToolTipText("How fast the song goes");
		textField.setBounds(50, 578, 114, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(2);
		/**
		JLabel lblBpm = DefaultComponentFactory.getInstance().createLabel("BPM");
		lblBpm.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblBpm.setBounds(50, 495, 104, 87);
		frame.getContentPane().add(lblBpm);
		
		JLabel lblAirBand = DefaultComponentFactory.getInstance().createLabel("AIR BAND");
		lblAirBand.setToolTipText("Get rekt Ted");
		lblAirBand.setFont(new Font("Tahoma", Font.BOLD, 72));
		lblAirBand.setBounds(98, 0, 380, 113);
		frame.getContentPane().add(lblAirBand);
		
		JLabel lblSoundCheck = DefaultComponentFactory.getInstance().createLabel("Sound check");
		lblSoundCheck.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSoundCheck.setBounds(171, 121, 213, 87);
		frame.getContentPane().add(lblSoundCheck);
		
		JLabel lblBass = DefaultComponentFactory.getInstance().createLabel("Bass");
		lblBass.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblBass.setBounds(76, 229, 181, 26);
		frame.getContentPane().add(lblBass);
		
		JLabel lblRhythm = DefaultComponentFactory.getInstance().createLabel("Rhythm");
		lblRhythm.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblRhythm.setBounds(76, 301, 181, 39);
		frame.getContentPane().add(lblRhythm);
		
		JLabel lblLead = DefaultComponentFactory.getInstance().createLabel("Lead");
		lblLead.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblLead.setBounds(76, 378, 181, 39);
		frame.getContentPane().add(lblLead);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(353, 578, 200, 50);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblStyle = DefaultComponentFactory.getInstance().createLabel("Style");
		lblStyle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblStyle.setBounds(386, 509, 148, 50);
		frame.getContentPane().add(lblStyle);
		*/
		
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
