package main.java.gui;

import main.java.LifePhase;
import main.java.Main;

import javax.swing.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel(Main app) {
        JRadioButton babyButton = new JRadioButton("Baby");
        JRadioButton toddlerButton = new JRadioButton("Toddler");
        JRadioButton childButton = new JRadioButton("Child");
        JRadioButton teenButton = new JRadioButton("Teen");
        JRadioButton adultButton = new JRadioButton("Adult");
        JRadioButton elderButton = new JRadioButton("Elder");

        babyButton.addActionListener(e -> app.onLifePhaseChanged(LifePhase.BABY));
        toddlerButton.addActionListener(e -> app.onLifePhaseChanged(LifePhase.TODDLER));
        childButton.addActionListener(e -> app.onLifePhaseChanged(LifePhase.CHILD));
        teenButton.addActionListener(e -> app.onLifePhaseChanged(LifePhase.TEEN));
        adultButton.addActionListener(e -> app.onLifePhaseChanged(LifePhase.ADULT));
        elderButton.addActionListener(e -> app.onLifePhaseChanged(LifePhase.ELDER));

        ButtonGroup group = new ButtonGroup();
        group.add(babyButton);
        group.add(toddlerButton);
        group.add(childButton);
        group.add(teenButton);
        group.add(adultButton);
        group.add(elderButton);

        setBorder(BorderFactory.createTitledBorder("Select Life Phase"));
        add(babyButton);
        add(toddlerButton);
        add(childButton);
        add(teenButton);
        add(adultButton);
        add(elderButton);
    }

}
