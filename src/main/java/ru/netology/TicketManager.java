package ru.netology;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket tick : repo.findAll()) {
            if (coincidence(tick, from, to)) {
                Ticket[] search = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    search[i] = result[i];
                }
                search[search.length - 1] = tick;
                result = search;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public boolean coincidence(Ticket ticket, String from, String to) {
        if (ticket.getFromAirport().equals(from)) {
            if (ticket.getToAirport().equals(to)) {
                return true;
            }
        }
        return false;
    }
}

