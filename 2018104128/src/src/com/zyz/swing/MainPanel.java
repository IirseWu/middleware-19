package com.zyz.swing;

import com.zyz.jdbc.SqlMenu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainPanel {
    JFrame frame = new JFrame("��ӭʹ��");

    //��������
    JPanel panel = new JPanel();
    private JLabel title = new JLabel("���������ϵͳ");
    private JLabel infos = new JLabel("�����Ϣ");

    //���
    private JTextField inNumber = new JTextField(14);

    // ���ؼ���panel
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel panel8 = new JPanel();

    //���ֿؼ�
    private JLabel inNumberName = new JLabel("���ϱ���");
    private JButton inBase = new JButton("���");
    private JButton outBase = new JButton("����");
    private JButton search = new JButton("����");
    private JButton modifyPrice = new JButton("�޸ļ۸�");
    private JButton searchOrder = new JButton("������¼");

    // ��ʾ���ݿ�����Ϣ
    private JPanel panel7 = new JPanel();

    public void init(JTable jTable) throws SQLException {
        try {
            String[] dataSet = SqlMenu.selectBaseInfo(inNumber.getText().trim(), "goods_info");
            Object[][] table = SqlMenu.selectData("all", 300);
            String[] head = new String[] {"���ϱ���", "��������", "���Ҳ�Ʒ��", "������λ", "�����", "�������"};
            DefaultTableModel tableModel = new DefaultTableModel(table, head){
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            jTable.setModel(tableModel);
            infos.setText("�����"+dataSet[0]+"����Ʒ, �ϼ�"+dataSet[1]+"Ԫ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //���������
    public MainPanel() throws SQLException {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * ��������ؼ�
         */
        // JLabel�ؼ�
        JTable jTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(jTable);

        // ���������СTimesRoman
        title.setFont(new Font("TimesRoman",Font.BOLD, 44));
        inBase.setFont(new Font("����",Font.BOLD, 14));
        outBase.setFont(new Font("����",Font.BOLD, 14));
        search.setFont(new Font("����",Font.BOLD, 15));
        inNumberName.setFont(new Font("����",Font.BOLD, 14));
        modifyPrice.setFont(new Font("����",Font.BOLD, 13));
        searchOrder.setFont(new Font("����",Font.BOLD, 13));
        infos.setFont(new Font("����",Font.BOLD, 11));


        panel.add(title);
        panel.setBounds(30,20, 600, 80);


        panel1.add(inNumber);
        panel2.add(search);
        panel3.add(inBase);
        panel4.add(outBase);
        panel5.add(modifyPrice);
        panel8.add(searchOrder);

        panel6.setLayout(new BorderLayout());
        panel6.add(infos, BorderLayout.EAST);


        panel1.setBounds(180, 100, 180, 40);
        panel2.setBounds(380, 99, 65, 40);

        panel3.setBounds(150, 140, 65, 40);
        panel4.setBounds(220, 140, 65, 40);
        panel5.setBounds(280, 140, 105, 40);
        panel8.setBounds(370, 140, 105, 40);

        panel6.setBounds(370, 175, 300, 20);
        scrollPane.setBounds(30,200,640,180);


        frame.setLayout(null);
        frame.getContentPane().add(panel);
        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);
        frame.getContentPane().add(panel3);
        frame.getContentPane().add(panel4);
        frame.getContentPane().add(panel5);
        frame.getContentPane().add(panel6);
        frame.getContentPane().add(panel8);
        frame.getContentPane().add(scrollPane);

        // ��ʼ�����
        init(jTable);

        /**
         * ������ť���¼���������
         */

        // ��ⰴť������
        class InBaseButton implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String number = inNumber.getText().trim();
                int total = 0;
                Object[][] table = new Object[1][6];

                // У��������Ƿ�������ݿ���
                try {
                    table = SqlMenu.selectData(number,1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if(number.equals(table[0][0])){
                    // ��ѯ����Ʒ�Ŀ����
                    total = Integer.parseInt((String)table[0][5]);
                    Object obj = JOptionPane.showInputDialog(frame,"���"+table[0][0]+"ʣ������"+table[0][5]+"���������������","",JOptionPane.INFORMATION_MESSAGE);
                    int totalInput = Integer.parseInt((String)obj);

                    if(totalInput < 0){
                        JOptionPane.showMessageDialog(frame, "��������Ƿ��������ԣ�����", "",JOptionPane.ERROR_MESSAGE);
                    }else {
                        int flag = JOptionPane.showConfirmDialog(frame, "ȷ����⣿", "", JOptionPane.ERROR_MESSAGE);
                        if(flag==0){
                            total = total + totalInput;
                            //��Ʒ������
                            try {
                                SqlMenu.updateTotalData(number,total);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            // ��������¼�������ݿ�
                            table[0][5] = totalInput+"";

                            String[] dataSet = (String[]) table[0];
                            try {
                                SqlMenu.insertRecordData(dataSet, "����¼");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    try {
                        init(jTable);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(frame, "��������ȷ�����ϱ��룡����", "",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //���ⰴť�¼�������
        class OutBaseButton implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String number = inNumber.getText().trim();
                int total = 0;
                Object[][] table = new Object[1][6];

                // У��������Ƿ�������ݿ���
                try {
                    table = SqlMenu.selectData(number,1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if(number.equals(table[0][0])){
                    total = Integer.parseInt((String)table[0][5]);
                    Object obj = JOptionPane.showInputDialog(frame,"���"+table[0][0]+"ʣ������"+table[0][5]+"���������������","",JOptionPane.INFORMATION_MESSAGE);
                    int totalInput = Integer.parseInt((String)obj);
                    if(totalInput > total || totalInput < 0){
                        JOptionPane.showMessageDialog(frame, "���������Ƿ��������ԣ�����", "",JOptionPane.ERROR_MESSAGE);
                    }else {
                        int flag = JOptionPane.showConfirmDialog(frame, "ȷ�ϳ��⣿", "", JOptionPane.ERROR_MESSAGE);
                        if(flag == 0) {
                            total = total - totalInput;
                            try {
                                SqlMenu.updateTotalData(number, total);
                                init(jTable);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            // ��������ʷ��¼�������ݿ�
                            table[0][5] = totalInput + "";

                            String[] dataSet = (String[]) table[0];
                            try {
                                SqlMenu.insertRecordData(dataSet, "�����¼");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                }else {
                    JOptionPane.showMessageDialog(frame, "��������ȷ�����ϱ��룡����", "",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // ��ѯ��ť�¼�������
        class SearchButton implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String number = inNumber.getText().trim();
                try {
                    Object[][] table = SqlMenu.selectData(inNumber.getText(), -1);
                    String[] head = new String[] {"���ϱ���", "��������", "���Ҳ�Ʒ��", "������λ", "�����", "�������"};

                    DefaultTableModel tableModel = new DefaultTableModel(table, head){
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    jTable.setModel(tableModel);

                    String[] dataSet = SqlMenu.selectBaseInfo(inNumber.getText().trim(), "goods_info");
                    if(inNumber.getText().trim().equals("")){
                        infos.setText("�����"+dataSet[0]+"��, �ϼ�"+dataSet[1]+"Ԫ");
                    }else {
                        infos.setText("������Ʒ���" + dataSet[0] + "��, �ϼ�" + dataSet[1] + "Ԫ");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }


        }

        //�޸ļ۸�
        class ModifyPriceButton implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String number  = inNumber.getText().trim();
                Object[][] table = new Object[1][6];

                try {
                    table = SqlMenu.selectData(number, 1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if(number.equals(table[0][0])){
                    Object obj = JOptionPane.showInputDialog(frame,"���"+table[0][0]+"��ǰ�۸�:"+table[0][4]+"���������¼۸�","",JOptionPane.INFORMATION_MESSAGE);
                    double priceInput = Double.parseDouble((String)obj);
                    try {
                        SqlMenu.updatePriceData(number, priceInput);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(frame, "��������ȷ�����ϱ��룡����", "",JOptionPane.ERROR_MESSAGE);
                }

                // ��ʼ�����
                try {
                    init(jTable);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // ��ѯ��ʷ������¼
        class SearchOrderButton implements ActionListener{
            // ��ѯ����
            public void selectData(int param, int width, String text) throws SQLException {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();

                c.add(Calendar.MONTH, param);
                Date d = c.getTime();
                String end = sdf.format(d).substring(0, 7)+"-01";

                c.add(Calendar.MONTH, width);
                d = c.getTime();
                String start = sdf.format(d).substring(0,7)+"-01";

                //
                Object table;
                if(text.contains("����")) {
                    table = SqlMenu.selectRecordData(start, end, "�����¼");
                }else {
                    table = SqlMenu.selectRecordData(start, end, "����¼");
                }
                String[] head = new String[] {"��¼��ʶ", "���ϱ���", "��������", "���Ҳ�Ʒ��", "�����", "��/������", "����ʱ��"};

                DefaultTableModel tableModel = new DefaultTableModel((String[][]) table, head){
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                jTable.setModel(tableModel);

                //

                if(width == -12){
                    if(text.contains("����")) {
                        String[] dataSet = SqlMenu.selectRecordBaseInfo(start, end, "�����¼");
                        infos.setText("��ȥһ�굽���� �����¼��" + dataSet[0] + "��, �ϼ�" + dataSet[1] + "Ԫ");
                    }else {
                        String[] dataSet = SqlMenu.selectRecordBaseInfo(start, end, "����¼");
                        infos.setText("��ȥһ�굽���� ����¼��" + dataSet[0] + "��, �ϼ�" + dataSet[1] + "Ԫ");
                    }
                }else {
                    if(text.contains("����")) {
                        String[] dataSet = SqlMenu.selectRecordBaseInfo(start, end, "�����¼");
                        infos.setText(start.substring(0, 4) + "��" + start.substring(5, 7) + "�� �����¼��" + dataSet[0] + "��, �ϼ�" + dataSet[1] + "Ԫ");
                    }else {
                        String[] dataSet = SqlMenu.selectRecordBaseInfo(start, end, "����¼");
                        infos.setText(start.substring(0, 4)+"��" +start.substring(5,7)+ "�� ����¼��" + dataSet[0] + "��, �ϼ�" + dataSet[1] + "Ԫ");
                    }
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] table = new Object[8];
                table[0] = "���������";
                table[1] = "���³�����";
                table[2] = "���������";
                table[3] = "���³�����";
                table[4] = "���������";
                table[5] = "�����³���";
                table[6] = "��һ�����";
                table[7] = "��һ�����";

                Object obj = JOptionPane.showInputDialog(frame,"","", JOptionPane.PLAIN_MESSAGE, null, table,0);
                String text = (String)obj;
                switch (text){
                    case "���³�����":
                    case "���������": {
                        try {
                            selectData(1, -1, text);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    } break;
                    case "���³�����":
                    case "���������": {
                        try {
                            selectData(0, -1, text);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } break;
                    case "�����³���":
                    case "���������": {
                        try {
                            selectData(-1, -1, text);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }; break;
                    case "��һ�����":
                    case "��һ�����": {
                        try {
                            selectData(1, -12, text);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } break;
                    default: break;
                }
            }
        }

        //����ť����¼�������
        InBaseButton ibb = new InBaseButton();
        inBase.addActionListener(ibb);

        OutBaseButton obb = new OutBaseButton();
        outBase.addActionListener(obb);

        SearchButton sb = new SearchButton();
        search.addActionListener(sb);

        ModifyPriceButton mpb = new ModifyPriceButton();
        modifyPrice.addActionListener(mpb);

        SearchOrderButton sob = new SearchOrderButton();
        searchOrder.addActionListener(sob);

        // �������Ϊ�ɼ�
        frame.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        MainPanel mainPanel = new MainPanel();
    }
}
