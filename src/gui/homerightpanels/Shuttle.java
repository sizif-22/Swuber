package gui.homerightpanels;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import functionality.*;
import gui.Frame;

public class Shuttle extends JPanel {

  private Frame frame;
  private User user;

  public Shuttle(Frame frame, User user) throws SQLException {
    this.frame = frame;
    this.user = user;

    setBounds(300, 0, 900, 800);
    setBackground(new Color(55, 55, 55));

    setLayout(null);

    JLabel h1 = new JLabel("Swuber Shuttle");
    h1.setFont(new Font("Arial", Font.BOLD, 50));
    h1.setForeground(Color.WHITE);
    h1.setBounds(50, 100, 900, 40);
    add(h1);

    JLabel h2 = new JLabel("Available Routes");
    h2.setFont(new Font("Arial", Font.PLAIN, 35));
    h2.setForeground(Color.WHITE);
    h2.setBounds(50, 160, 900, 40);
    add(h2);

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(null);
    contentPanel.setBackground(new Color(33, 33, 33));
    contentPanel.setBorder(null);
    // Add Cards here
    int counter = 0;
    String sql = "select s.* , case when c.userId is null then false else true end as isScheduled from ShuttleRide s left outer join scheduledRides c on s.id = c.shuttleId and c.userId = "
        + user.getUserId() + ";";
    System.out.println(sql);
    ResultSet resultSet = frame.db.statement.executeQuery(sql);
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int maxPassengers = resultSet.getInt("maxPassengers");
      float price = resultSet.getFloat("price");
      String startLocation = resultSet.getString("startLocation");
      String endLocation = resultSet.getString("endLocation");
      System.out.println(resultSet.getString("isScheduled"));
      Boolean isScheduled = resultSet.getBoolean("isScheduled");
      System.out.println(id + " is scheduled ? : " + isScheduled);
      contentPanel.add(new BookRideCard(id, startLocation, endLocation, maxPassengers, price, frame, user, counter++ , isScheduled));
    }

    contentPanel.setPreferredSize(new Dimension(820, (165 * counter + 10)));
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setBounds(20, 220, 840, 500);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBorder(null);

    // Add the scroll pane to this panel
    add(scrollPane);
  }
}