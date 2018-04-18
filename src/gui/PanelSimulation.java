package gui;

import snake.Cell;
import snake.Environment;
import snake.EnvironmentListener;
import snake.SnakeAgent;
import snake.snakeAI.nn.SnakeAIAgent;
import snake.snakeAdhoc.SnakeAdhocAgent;
import snake.snakeRandom.SnakeRandomAgent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PanelSimulation extends JPanel implements EnvironmentListener {

    public static final int PANEL_SIZE = 250;
    public static final int CELL_SIZE = 20;
    public static final int GRID_TO_PANEL_GAP = 20;
    private static final int MAX_ITERATIONS = 500; // todo modify to change the number of iterations
    private static final int SIZE = 10;

    MainFrame mainFrame;
    private PanelParameters panelParameters = new PanelParameters();
    private Environment environment;
    private Image image;
    JPanel environmentPanel = new JPanel();
    final JButton buttonSimulate = new JButton("Simulate");
    String[] typeAgents = {"SnakeRandomAgent","SnakeAdHocAgent","SnakeAI","SnakeRandomAgent and SnakeAdHocAgent"};
    JComboBox comboBoxSelectionTypeAgents = new JComboBox(typeAgents);


    public PanelSimulation(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        environmentPanel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setLayout(new BorderLayout());
        add(environmentPanel, java.awt.BorderLayout.NORTH);
        add(buttonSimulate, java.awt.BorderLayout.SOUTH);
        buttonSimulate.addActionListener(new SimulationPanel_jButtonSimulate_actionAdapter(this));
        add(comboBoxSelectionTypeAgents,BorderLayout.EAST);

        setBorder(BorderFactory.createCompoundBorder(

                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setJButtonSimulateEnabled(boolean enabled) {
        buttonSimulate.setEnabled(enabled);
    }

    public void jButtonSimulate_actionPerformed(ActionEvent e) {
        environment = new Environment(SIZE, MAX_ITERATIONS, comboBoxSelectionTypeAgents.getSelectedIndex());
        environment.addEnvironmentListener(this);

        buildImage(environment);

        SwingWorker worker = new SwingWorker<Void, Void>() {
            public Void doInBackground() {
                environmentUpdated();
                try {
                    environment.simulate();
                }catch(Exception e1) {
                    e1.printStackTrace();
                }return null;
            }
        };
        worker.execute();
    }

   /* public void jButtonSimulate_actionPerformed(ActionEvent e) {

        environment = mainFrame.getProblem().getEnvironment();
        environment.addEnvironmentListener(this);

        buildImage(environment);

        final PanelSimulation simulationPanel = this;

        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                int environmentSimulations = mainFrame.getProblem().getNumEvironmentSimulations();

                for (int i = 0; i < environmentSimulations; i++) {
                    environment.initialize(i);
                    environmentUpdated();
                    environment.simulate();
                }
                return null;
            }

            @Override
            public void done() {
                environment.removeEnvironmentListener(simulationPanel);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException ignore) {
                }

            }
        };
        worker.execute();
    }*/

    public void buildImage(Environment environment) {
        image = new BufferedImage(
                environment.getSize() * CELL_SIZE + 1,
                environment.getSize() * CELL_SIZE + 1,
                BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void environmentUpdated() {
        int n = environment.getSize();
        Graphics g = image.getGraphics();

        //Fill the cells color
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                g.setColor(environment.getCellColor(y, x));
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        //Draw the grid lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= n; i++) {
            g.drawLine(0, i * CELL_SIZE, n * CELL_SIZE, i * CELL_SIZE);
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, n * CELL_SIZE);
        }

        g = environmentPanel.getGraphics();
        g.drawImage(image, GRID_TO_PANEL_GAP, GRID_TO_PANEL_GAP, null);

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
        }
    }
}

//--------------------
class SimulationPanel_jButtonSimulate_actionAdapter implements ActionListener {

    final private PanelSimulation adaptee;

    SimulationPanel_jButtonSimulate_actionAdapter(PanelSimulation adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSimulate_actionPerformed(e);

    }
}

