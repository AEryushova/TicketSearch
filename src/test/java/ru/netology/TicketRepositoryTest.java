package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TicketRepositoryTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(16, 9000, "KUF", "DME", 90);
    Ticket ticket2 = new Ticket(25, 7500, "SGC", "LED", 190);
    Ticket ticket3 = new Ticket(78, 22000, "VVO", "KGD", 840);
    Ticket ticket4 = new Ticket(41, 6000, "KRR", "MCX", 80);


    @Test
    public void shouldSaveTicket() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        Ticket[] expected = {ticket1, ticket2, ticket3};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAllTicket() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveTicketById() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.removeById(25);
        Ticket[] expected = {ticket1, ticket3, ticket4};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddNewTicketInRepo() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        manager.add(ticket4);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
}