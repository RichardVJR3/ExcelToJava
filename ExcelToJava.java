
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;

public class ExcelToJava {
    static ArrayList<Monsters> monsters = new ArrayList<Monsters>();
    static int currentMonsterPart = 0;
    static int currentLevel = 0;
    static String monsterNameTest = null;
    static ArrayList<String> parts = new ArrayList<String>();
    static ArrayList<String> names = new ArrayList<String>();
    static ArrayList<Monsters> monstersWithPart = new ArrayList<>();
    static ArrayList<String> monstersToDisplay = new ArrayList<String>();
    //static JLabel label = new JLabel();
    static JLabel label1 = new JLabel();
    static JLabel label2 = new JLabel();
    static JLabel label3 = new JLabel();
    static JLabel label4 = new JLabel();
    static String name = null;
    static String level = null;
    static String url = null;
    static int counter = 0;

    public static void main(String[] args) throws IOException {
        loadExcel();

        // Gathers all mosnter parts into a seperate ArrayList
        for (int i = 0; i < monsters.size(); i++) {
            if (!parts.contains(monsters.get(i).monsterPart)) {                                       
                parts.add(monsters.get(i).monsterPart);
            }
        }

        for (int i = 0; i < monsters.size(); i++) {
            if (!names.contains(monsters.get(i).name)) {                                       
                names.add(monsters.get(i).name);
            }
        }
        
        System.out.println(names);

        /*
         ********************
         * Create GUI window
         * ******************
         */

         JFrame frame = new JFrame("Monster Hunter SunRise Database");

         // Set how large window is
         frame.setSize(1920, 1080);

        // Make fram resizable
        frame.setResizable(true);

        // Center window screen
        frame.setLocationRelativeTo(null);

        // Creates window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(232,232,223,255));

        // Creates sub panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(null);

        frame.add(textPanel);

        // Set label to display onto window
        JLabel greeting = new JLabel("Current Selection");
        greeting.setSize(1000, 30);
        greeting.setLocation(780, 130);
        greeting.setFont(new Font("Helvetica", 0, 18));
        mainPanel.add(greeting);

        // Create first button
        JButton toLeft = new JButton();
        toLeft.setText("Go left");
        toLeft.setSize(200, 100);
        toLeft.setLocation(50, 80);
        toLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (counter >= 1) {
                    counter = 0;
                    mainPanel.remove(label1);
                    mainPanel.remove(label2);
                    mainPanel.remove(label3);
                    mainPanel.remove(label4);
                }
                monstersToDisplay.clear();
                currentMonsterPart -= 1;
                if (currentMonsterPart <= 0) {
                    currentMonsterPart = 0;
                }
                //System.out.println(currentMonsterPart);
                greeting.setText(parts.get(currentMonsterPart));
                getMonstersForPart(parts.get(currentMonsterPart));
                //Monsters monstersToDisplay = monstersWithPart.get(0);
                //System.out.println(monstersWithPart);
                for (int i = 0; i < monstersWithPart.size(); i++) {
                    Monsters m = monstersWithPart.get(i);
                    name = m.name;
                    level = m.level;
                    url = m.urlMonster;
                    counter++;
                    

                    if (counter == 1) {
                        label1.setText(name + ": " + level);
                        label1.setSize(300, 100);
                        label1.setLocation(20 + i * 500, 600);
                        mainPanel.add(label1);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    } else if (counter == 2) {
                        label2.setText(name + ": " + level);
                        label2.setSize(300, 100);
                        label2.setLocation(20 + i * 500, 600);
                        mainPanel.add(label2);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    } else if (counter == 3) {
                        label3.setText(name + ": " + level);
                        label3.setSize(300, 100);
                        label3.setLocation(20 + i * 500, 600);
                        mainPanel.add(label3);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    } else if (counter == 4) {
                        label4.setText(name + ": " + level);
                        label4.setSize(300, 100);
                        label4.setLocation(20 + i * 500, 600);
                        mainPanel.add(label4);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            }
        });
        mainPanel.add(toLeft);

        // Create second button
        JButton toRight = new JButton();
        toRight.setText("Go right");
        toRight.setSize(120, 100);
        toRight.setLocation(1550, 80);
        toRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (counter >= 1) {
                    counter = 0;
                    mainPanel.remove(label1);
                    mainPanel.remove(label2);
                    mainPanel.remove(label3);
                    mainPanel.remove(label4);
                }

                monstersToDisplay.clear();
                currentMonsterPart += 1;
                if (currentMonsterPart > parts.size()) {
                    currentMonsterPart = parts.size();
                }
                //System.out.println(currentMonsterPart);
                greeting.setText(parts.get(currentMonsterPart));
                getMonstersForPart(parts.get(currentMonsterPart));
                // Generates a set of monsters based on how many are in the monstersWithPart at that time
                for (int i = 0; i < monstersWithPart.size(); i++) {
                    Monsters m = monstersWithPart.get(i);
                    name = m.name;
                    level = m.level;
                    url = m.urlMonster;
                    counter++;
                    

                    if (counter == 1) {
                        label1.setText(name + ": " + level);
                        label1.setSize(300, 100);
                        label1.setLocation(20 + i * 500, 600);
                        mainPanel.add(label1);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    } else if (counter == 2) {
                        label2.setText(name + ": " + level);
                        label2.setSize(300, 100);
                        label2.setLocation(20 + i * 500, 600);
                        mainPanel.add(label2);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    } else if (counter == 3) {
                        label3.setText(name + ": " + level);
                        label3.setSize(300, 100);
                        label3.setLocation(20 + i * 500, 600);
                        mainPanel.add(label3);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    } else if (counter == 4) {
                        label4.setText(name + ": " + level);
                        label4.setSize(300, 100);
                        label4.setLocation(20 + i * 500, 600);
                        mainPanel.add(label4);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            }
        });
        mainPanel.add(toRight);

        // Creates the monster image
        // BufferedImage image = null;
        //URL url = new URL();
        //image = ImageIO.read(url);
        // ImageIcon icon = new ImageIcon(image);
        // JLabel album = new JLabel(icon, JLabel.CENTER);
        // album.setSize(300, 200);
        // album.setLocation(50, 400);
        // mainPanel.add(album);

        // stop program when x button is clicked
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        // Makes frame visible  
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    // ------------------------------- Loads Excel document
    public static void loadExcel() {
        try {
            //POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("ExcelImport.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("ExcelImport.xlsx"));
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start
            // from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols)
                        cols = tmp;
                }
            }

            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    String parts = row.getCell(1).getStringCellValue();
                    String level = row.getCell(2).getStringCellValue();
                    String urlMonster = row.getCell(3).getStringCellValue();

                    Monsters monster = new Monsters(name, parts, level, urlMonster);// Declares the class Monsters, gives it a name and then declares the method inside Monsters
                    monsters.add(monster); // Using the monsters ArrayList<String> above, add the monster method.
                    // for (int c = 0; c < cols; c++) {
                    //     cell = row.getCell(c);
                    //     if (cell != null) {
                    //         //System.out.println(cell.getStringCellValue());
                    //         monster.add(cell.getStringCellValue());                            
                    //     }
                    // }
                }
            }

            for (int i = 0; i < monsters.size(); i++) {
                Monsters m = monsters.get(i);
                m.print();
                System.out.println("-----");
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    // ------------------------------------ 
    static void getMonstersForPart(String part) {
        monstersWithPart.clear();

        // for each monster in the "monsters" list:
            // if monster's part is equal to the passed in part
                // add monster to "monstersWithPart"

        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).monsterPart.equals(part)) {
                monstersWithPart.add(monsters.get(i));
            }
        }
    }
}