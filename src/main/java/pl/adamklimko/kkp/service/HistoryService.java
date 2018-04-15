package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.History;

public interface HistoryService {

  Iterable<History> findLast20();

  void save(History entry);
}
