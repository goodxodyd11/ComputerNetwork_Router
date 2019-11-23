package ipc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class RouterDlg extends JFrame {

	private JPanel contentPane;
	private JTable routingTable;
	private JTable arpTable;
	private JTable proxyTable;
	private JButton routingAddBtn;
	private JButton routingDeleteBtn;
	private JButton arpDeleteBtn;
	private JButton proxyAddBtn;
	private JButton proxyDeleteBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouterDlg frame = new RouterDlg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RouterDlg() {
		setTitle("Router");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel routingTablePanel = new JPanel();
		routingTablePanel.setBounds(14, 12, 490, 512);
		contentPane.add(routingTablePanel);
		routingTablePanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Static Routing Table");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(14, 12, 462, 47);
		routingTablePanel.add(lblNewLabel);

		routingAddBtn = new JButton("Add");
		routingAddBtn.setBounds(35, 470, 185, 30);
		routingAddBtn.addActionListener(new setAddressListener());
		routingTablePanel.add(routingAddBtn);

		routingDeleteBtn = new JButton("Delete");
		routingDeleteBtn.setBounds(269, 470, 185, 30);
		routingDeleteBtn.addActionListener(new setAddressListener());
		routingTablePanel.add(routingDeleteBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 71, 490, 369);
		routingTablePanel.add(scrollPane);

		routingTable = new JTable();
		routingTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Destination", "NetMask", "Gateway", "Flag", "Interface", "Metric" }));
		routingTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		routingTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		routingTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(routingTable);

		JPanel arpTablePanel = new JPanel();
		arpTablePanel.setBounds(518, 12, 447, 276);
		contentPane.add(arpTablePanel);
		arpTablePanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ARP Cache Table");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(14, 12, 419, 39);
		arpTablePanel.add(lblNewLabel_1);

		arpDeleteBtn = new JButton("Delete");
		arpDeleteBtn.setBounds(128, 234, 185, 30);
		arpTablePanel.add(arpDeleteBtn);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 51, 447, 171);
		arpTablePanel.add(scrollPane_1);

		arpTable = new JTable();
		arpTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "IP Address", "Ethernet Address", "Interface", "Flag" }));
		arpTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		arpTable.getColumnModel().getColumn(1).setPreferredWidth(129);
		scrollPane_1.setViewportView(arpTable);

		JPanel proxyTablePanel = new JPanel();
		proxyTablePanel.setBounds(518, 300, 447, 224);
		contentPane.add(proxyTablePanel);
		proxyTablePanel.setLayout(null);

		JLabel lblProxyArpTable = new JLabel("Proxy ARP Table");
		lblProxyArpTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblProxyArpTable.setFont(new Font("굴림", Font.BOLD, 20));
		lblProxyArpTable.setBounds(14, 12, 419, 39);
		proxyTablePanel.add(lblProxyArpTable);

		proxyAddBtn = new JButton("Add");
		proxyAddBtn.setBounds(14, 182, 185, 30);
		proxyAddBtn.addActionListener(new setAddressListener());
		proxyTablePanel.add(proxyAddBtn);

		proxyDeleteBtn = new JButton("Delete");
		proxyDeleteBtn.setBounds(248, 182, 185, 30);
		proxyTablePanel.add(proxyDeleteBtn);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 62, 447, 103);
		proxyTablePanel.add(scrollPane_2);

		proxyTable = new JTable();
		proxyTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "IP Address", "Ethernet Address", "Interface" }));
		proxyTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		proxyTable.getColumnModel().getColumn(1).setPreferredWidth(125);
		proxyTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		scrollPane_2.setViewportView(proxyTable);
	}

	public class setAddressListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == routingAddBtn) {
				new RouterAddDlg();
			} else if (e.getSource() == proxyAddBtn) {
				new ProxyDlg();
			} else if (e.getSource() == routingDeleteBtn) {
				String indexValue = JOptionPane.showInputDialog(null, "삭제할 Cache의 인덱스를 입력해주세요(Index : 1부터 시작)",
						"Cache Delete", JOptionPane.OK_CANCEL_OPTION);
				int indexValueInteger = 0;
				if (indexValue != null) {
					indexValueInteger = Integer.parseInt(indexValue);
				}
				DefaultTableModel model = (DefaultTableModel) routingTable.getModel();
				model.removeRow(indexValueInteger - 1);
			}
		}

	}

	public class RouterAddDlg extends JFrame {

		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						test frame = new test();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}

		/**
		 * Create the frame.
		 */
		public RouterAddDlg() {
			setTitle("Add Router");
			setBounds(100, 100, 482, 373);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel = new JPanel();
			panel.setBounds(14, 12, 447, 265);
			contentPane.add(panel);
			panel.setLayout(null);

			JLabel lblNewLabel = new JLabel("Destination");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
			lblNewLabel.setBounds(0, 17, 140, 29);
			panel.add(lblNewLabel);

			JLabel lblNetmask = new JLabel("NetMask");
			lblNetmask.setHorizontalAlignment(SwingConstants.CENTER);
			lblNetmask.setFont(new Font("굴림", Font.BOLD, 18));
			lblNetmask.setBounds(0, 58, 140, 29);
			panel.add(lblNetmask);

			JLabel lblGateway = new JLabel("Gateway");
			lblGateway.setHorizontalAlignment(SwingConstants.CENTER);
			lblGateway.setFont(new Font("굴림", Font.BOLD, 18));
			lblGateway.setBounds(0, 99, 140, 29);
			panel.add(lblGateway);

			JLabel lblFlag = new JLabel("Flag");
			lblFlag.setHorizontalAlignment(SwingConstants.CENTER);
			lblFlag.setFont(new Font("굴림", Font.BOLD, 18));
			lblFlag.setBounds(0, 145, 140, 29);
			panel.add(lblFlag);

			JLabel lblInterface = new JLabel("Interface");
			lblInterface.setHorizontalAlignment(SwingConstants.CENTER);
			lblInterface.setFont(new Font("굴림", Font.BOLD, 18));
			lblInterface.setBounds(0, 186, 140, 29);
			panel.add(lblInterface);

			JTextArea destinationText = new JTextArea();
			destinationText.setBounds(142, 17, 291, 29);
			panel.add(destinationText);

			JTextArea netMaskText = new JTextArea();
			netMaskText.setBounds(142, 58, 291, 29);
			panel.add(netMaskText);

			JTextArea gatewayText = new JTextArea();
			gatewayText.setBounds(142, 99, 291, 29);
			panel.add(gatewayText);

			JTextArea interfaceText = new JTextArea();
			interfaceText.setBounds(142, 186, 291, 29);
			panel.add(interfaceText);

			JCheckBox upCheckBox = new JCheckBox("Up");
			upCheckBox.setBounds(142, 148, 54, 27);
			panel.add(upCheckBox);

			JCheckBox gatewayCheckBox = new JCheckBox("Gateway");
			gatewayCheckBox.setBounds(223, 148, 97, 27);
			panel.add(gatewayCheckBox);

			JCheckBox hostCheckBox = new JCheckBox("Host");
			hostCheckBox.setBounds(336, 148, 97, 27);
			panel.add(hostCheckBox);

			JLabel lblMetric = new JLabel("Metric");
			lblMetric.setHorizontalAlignment(SwingConstants.CENTER);
			lblMetric.setFont(new Font("굴림", Font.BOLD, 18));
			lblMetric.setBounds(0, 224, 140, 29);
			panel.add(lblMetric);

			JTextArea MetrictextArea = new JTextArea();
			MetrictextArea.setBounds(142, 224, 291, 29);
			panel.add(MetrictextArea);

			JButton addBtn = new JButton("추가");
			addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String destination = destinationText.getText();
					String netmask = netMaskText.getText();
					String gateway = gatewayText.getText();
					if (gateway.equals("")) {
						gateway = "*";
					}
					String flag = null;
					if (upCheckBox.isSelected()) {
						flag = "U";
					}
					if (gatewayCheckBox.isSelected()) {
						flag += "G";
					}
					if (hostCheckBox.isSelected()) {
						flag += "H";
					}
					String interfaceString = interfaceText.getText();
					String Metric = MetrictextArea.getText();
					DefaultTableModel model = (DefaultTableModel) routingTable.getModel();
					model.addRow(new Object[] { destination, netmask, gateway, flag, interfaceString, Metric });
					destinationText.setText("");
					netMaskText.setText("");
					gatewayText.setText("");
					upCheckBox.setSelected(false);
					gatewayCheckBox.setSelected(false);
					hostCheckBox.setSelected(false);
					interfaceText.setText("");
					MetrictextArea.setText("");
					setVisible(false);

				}
			});
			addBtn.setBounds(81, 289, 105, 27);
			contentPane.add(addBtn);

			JButton cancelBtn = new JButton("취소");
			cancelBtn.setBounds(300, 289, 105, 27);
			contentPane.add(cancelBtn);
			setVisible(true);
		}
	}

	public class ProxyDlg extends JFrame {
		ProxyDlg() {
			setTitle("Proxy ARP Entry 추가");
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel lblNewLabel = new JLabel("Interface");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(28, 44, 86, 18);
			contentPane.add(lblNewLabel);

			JLabel lblIp = new JLabel("IP 주소");
			lblIp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIp.setBounds(28, 100, 86, 18);
			contentPane.add(lblIp);

			JLabel lblEthernet = new JLabel("   Ethernet 주소");
			lblEthernet.setBounds(28, 154, 86, 18);
			contentPane.add(lblEthernet);

			JTextArea DeviceText = new JTextArea();
			DeviceText.setBounds(128, 42, 255, 24);
			contentPane.add(DeviceText);

			JTextArea IPText = new JTextArea();
			IPText.setBounds(128, 98, 255, 24);
			contentPane.add(IPText);

			JTextArea EthernetText = new JTextArea();
			EthernetText.setBounds(128, 152, 255, 24);
			contentPane.add(EthernetText);

			JButton OkButton = new JButton("OK");
			OkButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String interfaceString = DeviceText.getText();
					String ipName = IPText.getText();
					String ethernetName = EthernetText.getText();
					if (interfaceString.equals("") || ipName.equals("") || ethernetName.equals("")) {
						JOptionPane.showMessageDialog(null, "올바른 정보를 입력해주세요");
					} else {
//                        proxyTextArea.append(interfaceString + "       " + ipName + "       " + ethernetName + "\n");
						// byte[] IPArray = getIPByteArray(ipName.split("\\."));
						// byte[] EthernetArray = getMacByteArray(ethernetName);
						DefaultTableModel model = (DefaultTableModel) proxyTable.getModel();
						model.addRow(new Object[] { ipName, ethernetName, interfaceString });
						DeviceText.setText("");
						IPText.setText("");
						EthernetText.setText("");
						setVisible(false);
						// ARPLayer.Add_Proxy(IPArray, EthernetArray);
					}
				}
			});
			OkButton.setBounds(63, 219, 105, 27);
			contentPane.add(OkButton);

			JButton CancelButton = new JButton("Cancel");
			CancelButton.addActionListener(e -> {
				DeviceText.setText("");
				IPText.setText("");
				EthernetText.setText("");
				setVisible(false);
			});

			CancelButton.setBounds(252, 219, 105, 27);
			contentPane.add(CancelButton);

			setVisible(true);
		}
	}
}
