package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.History;

public interface HistoryService {
    Iterable<History> findAll();
    void save(History entry);
}
