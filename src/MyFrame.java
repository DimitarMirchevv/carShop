import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyFrame extends JFrame {

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result= null;

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JLabel carBrandL = new JLabel();
    JLabel carModelL = new JLabel();
    JLabel yearL = new JLabel();
    JLabel horsePowerL = new JLabel();
    JLabel fuelL = new JLabel();
    JLabel priceL = new JLabel();


    JTextField carBrandTF = new JTextField();
    JTextField carModelTF = new JTextField();
    JTextField yearTF = new JTextField();
    JTextField horsePowerTF = new JTextField();
    JTextField priceTF = new JTextField();

    // може и с Enum да го направим да е различно от примера
    String[] item = {"Petrol", "Diesel", "Electric"};
    JComboBox<String> fuelCB = new JComboBox<String>(item);

    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JButton editButton = new JButton("Edit");

    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);

    public MyFrame() {
        this.setSize(600, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        //  upPanel ----------------------------------------------------
        upPanel.setLayout(new GridLayout(5,2));
        upPanel.add(carBrandL);
        upPanel.add(carBrandTF);

        upPanel.add(carModelL);
        upPanel.add(carModelTF);

        upPanel.add(yearL);
        upPanel.add(yearTF);

        upPanel.add(horsePowerL);
        upPanel.add(horsePowerTF);

        upPanel.add(fuelL);
        upPanel.add(fuelCB);

        upPanel.add(priceL);
        upPanel.add(priceTF);

        this.add(upPanel);

        //  midPanel ----------------------------------------------------
        midPanel.add(addButton);
        midPanel.add(deleteButton);
        midPanel.add(editButton);

        this.add(midPanel);

        //  downPanel ----------------------------------------------------
        scrollPane.setPreferredSize(new Dimension(550,220));
        downPanel.add(scrollPane);
        this.add(downPanel);


        addButton.addActionListener(new AddAction());
        this.setVisible(true);
    }

    class AddAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String sql = "insert into person(carBrand, carModel, year, horsepower, fuel, price) values(?,?,?,?,?,?)";
            try {
                conn = DBConn.getConnection();
                state = conn.prepareStatement(sql);
                state.setString(1, carBrandTF.getText());
                state.setString(2, carModelTF.getText());
                state.setInt(3, Integer.parseInt(yearTF.getText()));
                state.setInt(4, Integer.parseInt(horsePowerTF.getText()));
                state.setString(5, fuelCB.getSelectedItem().toString());
                state.setFloat(6, Float.parseFloat(priceTF.getText()));
                state.execute();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
