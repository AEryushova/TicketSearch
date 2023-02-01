package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);
    Comparator<Ticket> comparator = new TicketByTravelTimeAscComparator();

    Ticket ticket1 = new Ticket(16, 9000, "KUF", "DME", 95);
    Ticket ticket2 = new Ticket(25, 7500, "SGC", "LED", 190);
    Ticket ticket3 = new Ticket(78, 22000, "VVO", "KGD", 840);
    Ticket ticket4 = new Ticket(8, 7500, "KUF", "DME", 110);
    Ticket ticket5 = new Ticket(41, 6000, "KRR", "MCX", 80);
    Ticket ticket6 = new Ticket(37, 8000, "KUF", "DME", 80);
    Ticket ticket7 = new Ticket(16, 4500, "KRR", "MCX", 80);
    Ticket ticket8 = new Ticket(55, 9000, "KUF", "DME", 100);


    @BeforeEach
    public void save() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        repo.save(ticket6);
        repo.save(ticket7);
        repo.save(ticket8);

    }

    @Test
    public void shouldAllTicketSearchByAirport() {
        Ticket[] expected = {ticket4, ticket6, ticket1, ticket8};
        Ticket[] actual = (manager.findAll("KUF", "DME"));
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAllTicketSearchByTravelTime() {
        Ticket[] expected = {ticket6, ticket1, ticket8, ticket4};
        Ticket[] actual = (manager.findAll("KUF", "DME", comparator));
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNoTicketSearchByAirport() {
        Ticket[] expected = {};
        Ticket[] actual = (manager.findAll("SVX", "TJM"));
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldOneTicketSearchByAirport() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = (manager.findAll("VVO", "KGD"));
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldCoincidenceTheTicketToTheSearchQuery() {
        boolean expected = true;
        boolean actual = manager.coincidence(ticket2, "SGC", "LED");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void noShouldCoincidenceTheTicketToTheSearchQuery() {
        boolean expected = false;
        boolean actual = manager.coincidence(ticket2, "KRR", "MCX");
        Assertions.assertEquals(expected, actual);
    }
}
