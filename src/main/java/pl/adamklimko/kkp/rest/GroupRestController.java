package pl.adamklimko.kkp.rest;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.dto.GroupCreateDto;
import pl.adamklimko.kkp.model.dto.GroupDto;
import pl.adamklimko.kkp.service.GroupService;

@AllArgsConstructor
@RestController
@RequestMapping("groups")
public class GroupRestController {

  private final GroupService groupService;

  @GetMapping()
  public Page<GroupDto> getGroupsPage() {
    return null;
  }

  @PostMapping()
  public void createGroup(@Valid @RequestBody GroupCreateDto group) {

  }
}
