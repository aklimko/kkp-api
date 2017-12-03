package pl.adamklimko.kkp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.History;
import pl.adamklimko.kkp.model.Views;
import pl.adamklimko.kkp.service.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping()
    @JsonView(Views.Basic.class)
    public Iterable<History> getHistory() {
        return historyService.findAll();
    }
}
