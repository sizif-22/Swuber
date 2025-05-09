package com.swuber.functionality;

import jakarta.persistence.*;

import java.util.List;

import com.swuber.db.DatabaseConfig;

@Entity
@Table(name = "Card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    private String cardName;
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User owner;

    private static DatabaseConfig dbManager;

    // Default constructor required by JPA
    public Card() {
    }

    public Card(String cardName, String cardNumber, String expirationDate, String cardHolderName, User owner) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.owner = owner;
    }

    public static void setDBManager(DatabaseConfig dbConfig) {
        dbManager = dbConfig;
    }

    public String getCardL4Numbers() {
        if (cardNumber != null && cardNumber.length() >= 4) {
            return "****" + cardNumber.substring(cardNumber.length() - 4);
        } else if (cardNumber != null) {
            return cardNumber;
        } else {
            return "";
        }
    }

    public String getCard() {
        return this.cardName + " " + this.getCardL4Numbers() + " " + this.expirationDate + " " + this.cardHolderName;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        EntityManager em = dbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            this.cardName = cardName;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        EntityManager em = dbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            this.cardNumber = cardNumber;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        EntityManager em = dbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            this.expirationDate = expirationDate;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getCardHolderName() {
        return this.cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        EntityManager em = dbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            this.cardHolderName = cardHolderName;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public User getOwner() {
        return this.owner;
    }

    public int getCardId() {
        return this.cardId;
    }

    public static List<Card> getCards(User user) {
        EntityManager em = dbManager.getEntityManager();
        try {
            TypedQuery<Card> query = em.createQuery(
                    "SELECT c FROM Card c WHERE c.owner = :user", Card.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}