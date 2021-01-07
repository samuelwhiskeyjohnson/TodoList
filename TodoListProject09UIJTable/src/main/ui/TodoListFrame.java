package main.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TodoListFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel dtm ;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TodoListFrame frame = new TodoListFrame();
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
    public TodoListFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 400);
        contentPane = new JPanel();
        //		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        //		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBounds(0, 0, 150, 400);
        //		panel.setLayout(new GridLayout(2, 1, 0, 0));

        JButton addButton = new JButton("add task");
        addButton.setBounds(25, 10, 100, 50);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //				JOptionPane.showConfirmDialog(null, "add 버튼을 클릭하셨습니다. ");

                dtm.addRow(new Object[] { "duedate", "title", Boolean.FALSE});

                //				JComboBox comboBox = new JComboBox();
                //				comboBox.addItem("Snowboarding");
                //				comboBox.addItem("Rowing");
                //				comboBox.addItem("Chasing toddlers");
                //				comboBox.addItem("Speed reading");
                //				comboBox.addItem("Teaching high school");
                //				comboBox.addItem("None");

                //				TableColumn dateColumn = table.getColumnModel().getColumn(0);
                ////				DatePicker datePicker1 = new DatePicker();
                ////				ColorEditor colorEditor = new ColorEditor();
                //				dateColumn.setCellEditor(new ColorEditor());
                //
                //				TableColumn titleColumn = table.getColumnModel().getColumn(1);
                //				HintTextField titleTextField = new HintTextField("title");
                //				titleColumn.setCellEditor(new DefaultCellEditor(titleTextField));
                //				dtm.addRow(new Object[] { "duedate", titleTextField, Boolean.FALSE});
            }
        });
        panel.add(addButton);

        JButton saveButton = new JButton("save");
        saveButton.setBounds(25, 70, 100, 50);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				JOptionPane.showConfirmDialog(null, "save 버튼을 클릭하셨습니다. ");

                System.out.println("total rows: " + table.getRowCount());
                System.out.println("total cols: " + table.getColumnCount());


                String date = (String)table.getValueAt(0, 0);
                String title = (String)table.getValueAt(0, 1);
                String mark = (String)table.getValueAt(0, 2);
//				boolean mark = Boolean.parseBoolean(table.getValueAt(0, 2) + "");

                System.out.println(date + "\t" + title + "\t" + mark);

            }
        });
        panel.add(saveButton);

        JButton removeButton = new JButton("remove");
        removeButton.setBounds(25, 130, 100, 50);
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // is one row selected?

                JOptionPane.showConfirmDialog(null, table.getSelectedRow() + " row를 선택하셨습니다.");
                JOptionPane.showConfirmDialog(null, table.getSelectedColumn() + " col를 선택하셨습니다.");

                int selectedRow = table.getSelectedRow();
                if(selectedRow >= 0)	{
                    dtm.removeRow(table.getSelectedRow());
                }
            }
        });
        panel.add(removeButton);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);
        FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
        flowLayout_1.setAlignment(FlowLayout.CENTER);
        panel_1.setBounds(150, 0, 450, 400);


        setTitle("TodoList program");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        Object[] title = {"due date","title","check mark"};

        //		dtm = new DefaultTableModel();
        dtm = new DefaultTableModel()	{
            public java.lang.Class<?> getColumnClass(int columnIndex) {

                switch(columnIndex)	{
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                }

                return String.class;
            };
        };

        dtm.setColumnIdentifiers(title);


        dtm.insertRow(0, new Object[] {"11/30/19", "drink", true});





        //		dtm = new DefaultTableModel() {
        //
        //            Class[] types = new Class[]{
        //                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
        //            };
        //
        //            @Override
        //            public Class getColumnClass(int columnIndex) {
        //                return types[columnIndex];
        //            }
        //        };
        //
        //		dtm.setColumnIdentifiers(title);

        //		dtm.getColumnClass(2);


        // if load tasks are existed.. initialize previous tasks in a table.
        table = new JTable(dtm);

        table.setBounds(23, 55, 435, 217);
        //		table.setModel(dtm);




        JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //		JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setForeground(Color.RED);
        table.setRowHeight(30);
        panel_1.add(scroll);



        table.addMouseListener(new MouseAdapter()	{
            public void mouseClicked(MouseEvent me)
            {

                int row = table.rowAtPoint(me.getPoint());

                if(row < 0)	{
                    table.clearSelection();
                }

            }
        });
    }
}
