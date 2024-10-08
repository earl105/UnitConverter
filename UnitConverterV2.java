import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Simple Unit Converter.
 *
 * @author Dylan Earl
 *
 */
public final class UnitConverterV2 {

    /*
     * NOTES
     *
     * may need to add asserts to prevent overflow
     *
     * need to change converts clickability depending on if there are units
     * selected.
     *
     */

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private UnitConverterV2() {
    }

    /**
     * draws the window.
     */
    public static void drawWindow() {

        String[] conversions = { "Length", "Angle", "Temperature", "Speed" };
        String[] lengthUnits = { "Inch", "Centimeter", "Millimeter", "Foot" };
        String[] angleUnits = { "Degrees", "Radians" };
        String[] tempUnits = { "Fahrenheit", "Celsius", "Kelvin" };
        String[] speedUnits = { "mph", "km/h" };

        /*
         * create blank window
         */
        final int versionNum = 2;
        JFrame window = new JFrame();
        window.setTitle("Dylan Earl's Unit Converter V" + versionNum);
        final int width = 1000;
        final int height = 200;
        window.setSize(width, height);
        window.setResizable(true);
        final int startingPosition = 50;
        window.setLocation(startingPosition, startingPosition);
        window.setLayout(new BorderLayout());

        /*
         * create convert panel
         */
        JPanel convertPanel = new JPanel();
        final int horiz = 5;
        final int vert = 1;
        convertPanel.setLayout(new GridLayout(vert, horiz));

        /*
         * create conversion dropdown
         */
        JComboBox<String> pickConvert = new JComboBox<String>(conversions);
        pickConvert.setSelectedIndex(0);

        /*
         * create buttons
         */
        JButton convertButton = new JButton("Convert");
        JTextArea input = new JTextArea();
        JTextArea output = new JTextArea();
        output.setEditable(false); //make output not editable

        /*
         * create unit labels
         */
        JComboBox<String> beforeUnit = new JComboBox<String>();
        JComboBox<String> afterUnit = new JComboBox<String>();

        for (String s : lengthUnits) {
            beforeUnit.addItem(s);
            afterUnit.addItem(s);
        }
        afterUnit.setSelectedIndex(1);

        /*
         * Update unit labels when changing pickConvert
         */
        pickConvert.addActionListener(e -> {
            String pickedConversion = (String) pickConvert.getSelectedItem();
            beforeUnit.removeAllItems();
            afterUnit.removeAllItems();
            output.setText("");

            if (pickedConversion.equals("Length")) {
                for (String s : lengthUnits) {
                    beforeUnit.addItem(s);
                    afterUnit.addItem(s);
                }
                output.setText("");
                afterUnit.setSelectedIndex(1);
            } else if (pickedConversion.equals("Angle")) {
                for (String s : angleUnits) {
                    beforeUnit.addItem(s);
                    afterUnit.addItem(s);
                }
                output.setText("");
                afterUnit.setSelectedIndex(1);
            } else if (pickedConversion.equals("Temperature")) {
                for (String s : tempUnits) {
                    beforeUnit.addItem(s);
                    afterUnit.addItem(s);
                }
                output.setText("");
                afterUnit.setSelectedIndex(1);
            } else if (pickedConversion.equals("Speed")) {
                for (String s : speedUnits) {
                    beforeUnit.addItem(s);
                    afterUnit.addItem(s);
                }
                output.setText("");
                afterUnit.setSelectedIndex(1);
            }
        });

        /*
         * Handle Convert button click
         */
        convertButton.addActionListener(e -> {
            try {
                String pickedConversion = (String) pickConvert.getSelectedItem();
                String before = (String) beforeUnit.getSelectedItem();
                String after = (String) afterUnit.getSelectedItem();
                double inputValue = Double.parseDouble(input.getText());

                double convertedValue = 0;
                if (pickedConversion.equals("Length")) {
                    convertedValue = convertLength(inputValue, before, after);
                } else if (pickedConversion.equals("Angle")) {
                    convertedValue = convertAngle(inputValue, before, after);
                } else if (pickedConversion.equals("Temperature")) {
                    convertedValue = convertTemp(inputValue, before, after);
                } else if (pickedConversion.equals("Speed")) {
                    convertedValue = convertSpeed(inputValue, before, after);
                }

                output.setText(String.valueOf(convertedValue));
            } catch (NumberFormatException ex) {
                output.setText("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                output.setText(ex.getMessage());
            }
        });

        /*
         * add shit to panel
         */
        convertPanel.add(input);
        convertPanel.add(beforeUnit);
        convertPanel.add(convertButton);
        convertPanel.add(afterUnit);
        convertPanel.add(output);

        /*
         * set window visibility and add convertPanel to window
         */
        window.add(pickConvert, BorderLayout.NORTH);
        window.add(convertPanel, BorderLayout.CENTER);
        window.setVisible(true);

    }

    /**
     * converts length units.
     *
     * @param input
     * @param beforeUnit
     * @param afterUnit
     * @return converted value
     */
    public static double convertLength(double input, String beforeUnit,
            String afterUnit) {
        if (beforeUnit.equals("Inch") && afterUnit.equals("Centimeter")) {
            return input * 2.54;
        } else if (beforeUnit.equals("Centimeter") && afterUnit.equals("Inch")) {
            return input / 2.54;
        } else if (beforeUnit.equals("Inch") && afterUnit.equals("Millimeter")) {
            return input * 25.4;
        } else if (beforeUnit.equals("Centimeter") && afterUnit.equals("Millimeter")) {
            return input * 10;
        } else if (beforeUnit.equals("Millimeter") && afterUnit.equals("Inch")) {
            return input / 25.4;
        } else if (beforeUnit.equals("Millimeter") && afterUnit.equals("Centimeter")) {
            return input / 10;
        } else if (beforeUnit.equals("Foot") && afterUnit.equals("Inch")) {
            return input * 12;
        } else if (beforeUnit.equals("Inch") && afterUnit.equals("Foot")) {
            return input / 12;
        } else if (beforeUnit.equals("Foot") && afterUnit.equals("Centimeter")) {
            return input * 30.48;
        } else if (beforeUnit.equals("Centimeter") && afterUnit.equals("Foot")) {
            return input / 30.48;
        } else if (beforeUnit.equals("Foot") && afterUnit.equals("Millimeter")) {
            return input * 304.8;
        } else if (beforeUnit.equals("Millimeter") && afterUnit.equals("Foot")) {
            return input / 304.8;
        } else if (beforeUnit.equals(afterUnit)) {
            return input;
        } else {
            throw new IllegalArgumentException(
                    "Unsupported units: " + beforeUnit + " or " + afterUnit);
        }
    }

    /**
     * converts Angle units.
     *
     * @param input
     * @param beforeUnit
     * @param afterUnit
     * @return converted value
     */
    public static double convertAngle(double input, String beforeUnit, String afterUnit) {
        if (beforeUnit.equals(afterUnit)) {
            return input;
        } else if (beforeUnit.equals("Degrees") && afterUnit.equals("Radians")) {
            return Math.toRadians(input);
        } else if (beforeUnit.equals("Radians") && afterUnit.equals("Degrees")) {
            return Math.toDegrees(input);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported units: " + beforeUnit + " or " + afterUnit);
        }
    }

    /**
     * converts temperature units.
     *
     * @param input
     * @param beforeUnit
     * @param afterUnit
     * @return converted value
     */
    public static double convertTemp(double input, String beforeUnit, String afterUnit) {
        if (beforeUnit.equals(afterUnit)) {
            return input;
        } else if (beforeUnit.equals("Fahrenheit") && afterUnit.equals("Celsius")) {
            return (input - 32) * 5 / 9;
        } else if (beforeUnit.equals("Celsius") && afterUnit.equals("Fahrenheit")) {
            return (input * 9 / 5) + 32;
        } else if (beforeUnit.equals("Celsius") && afterUnit.equals("Kelvin")) {
            return input + 273.15;
        } else if (beforeUnit.equals("Kelvin") && afterUnit.equals("Celsius")) {
            return input - 273.15;
        } else if (beforeUnit.equals("Fahrenheit") && afterUnit.equals("Kelvin")) {
            return (input - 32) * 5 / 9 + 273.15;
        } else if (beforeUnit.equals("Kelvin") && afterUnit.equals("Fahrenheit")) {
            return (input - 273.15) * 9 / 5 + 32;
        } else {
            throw new IllegalArgumentException(
                    "Unsupported units: " + beforeUnit + " or " + afterUnit);
        }
    }

    /**
     * converts speed units.
     *
     * @param input
     * @param beforeUnit
     * @param afterUnit
     * @return converted value
     */
    public static double convertSpeed(double input, String beforeUnit, String afterUnit) {
        final double val = 1.60934;

        if (beforeUnit.equals(afterUnit)) {
            return input;
        } else if (beforeUnit.equals("mph") && afterUnit.equals("km/h")) {
            return input * val;
        } else if (beforeUnit.equals("km/h") && afterUnit.equals("mph")) {
            return input / val;
        } else {
            throw new IllegalArgumentException(
                    "Unsupported units: " + beforeUnit + " or " + afterUnit);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        drawWindow();

    }

}
