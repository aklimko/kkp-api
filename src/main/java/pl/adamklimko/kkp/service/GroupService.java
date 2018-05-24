package pl.adamklimko.kkp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.repository.GroupRepository;

@AllArgsConstructor
@Service
public class GroupService {

  private GroupRepository groupRepository;

}
