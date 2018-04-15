package pl.adamklimko.kkp.service.impl;

import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.History;
import pl.adamklimko.kkp.repository.HistoryRepository;
import pl.adamklimko.kkp.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

  private final HistoryRepository historyRepository;

  public HistoryServiceImpl(HistoryRepository historyRepository) {
    this.historyRepository = historyRepository;
  }

  @Override
  public Iterable<History> findLast20() {
    return historyRepository.findTop20ByOrderByIdDesc();
  }

  @Override
  public void save(History entry) {
    historyRepository.save(entry);
  }
}
