import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ProductApp extends JFrame {


    private JTextField txtCustomer;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;


    public ProductApp() {

        setTitle("Product App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));


        JLabel lblCustomer = new JLabel("Customer Name:");
        txtCustomer = new JTextField();

        JLabel lblProduct = new JLabel("Select Product:");

        String[] products = {
                "Cozy Nights Monarch",
                "Restonic Barbados",
                "Sealy Phuket"
        };

        cmbProduct = new JComboBox<>(products);


        JLabel lblPrice = new JLabel("Enter Price:");
        txtPrice = new JTextField();


        btnProcess = new JButton("PROCESS");

        add(lblCustomer);
        add(txtCustomer);

        add(lblProduct);
        add(cmbProduct);

        add(lblPrice);
        add(txtPrice);

        add(new JLabel(""));
        add(btnProcess);


        btnProcess.addActionListener(e -> ProcessProduct());


        CreateMenu();

        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void ProcessProduct() {

        try {


            String customer = txtCustomer.getText().trim();


            String product =
                    cmbProduct.getSelectedItem().toString();


            double price =
                    Double.parseDouble(txtPrice.getText());


            if (customer.isEmpty() || price <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid information."
                );

                return;
            }


            ProductCalculations calculation =
                    new ProductCalculations(price);


            double vat = calculation.GetVat();

            double commission = calculation.GetCommission();

            double total = calculation.GetTotal();


            String message =
                    "Customer Name: " + customer
                            + "\nProduct: " + product
                            + "\nVAT: R " + vat
                            + "\nCOMMISSION: R " + commission
                            + "\nTOTAL: R " + total;

            JOptionPane.showMessageDialog(this, message);


            SaveToFile(
                    customer,
                    product,
                    vat,
                    commission,
                    total
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid product price."
            );
        }
    }


    private void SaveToFile(
            String customer,
            String product,
            double vat,
            double commission,
            double total) {

        try (FileWriter writer =
                     new FileWriter("data.txt", true)) {

            writer.write("DATA LOG\n");

            writer.write("****************************\n");

            writer.write("Customer Name: " + customer + "\n");

            writer.write("Product: " + product + "\n");

            writer.write("VAT: R " + vat + "\n");

            writer.write("COMMISSION: R " + commission + "\n");

            writer.write("TOTAL: R " + total + "\n");

            writer.write("****************************\n\n");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving information to file."
            );
        }
    }


    private void CreateMenu() {


        JMenuBar menuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");


        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem processItem = new JMenuItem("Process");

        JMenuItem clearItem = new JMenuItem("Clear");

        exitItem.addActionListener(e -> {
            System.exit(0);
        });


        processItem.addActionListener(e -> {
            btnProcess.doClick();
        });


        clearItem.addActionListener(e -> {

            txtCustomer.setText("");

            txtPrice.setText("");

            cmbProduct.setSelectedIndex(0);
        });


        fileMenu.add(exitItem);

        toolsMenu.add(processItem);

        toolsMenu.add(clearItem);


        menuBar.add(fileMenu);

        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ProductApp();
        });
    }
}

