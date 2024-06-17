package songs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import songs.models.Song;
import songs.services.ISongService;
import songs.services.SongService;

@Controller
@RequestMapping(value = "admin")
public class SongController {

    @Autowired
    private ISongService songService = new SongService();

    @GetMapping(value = "/list")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping(value = "/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping(value = "/create")
    public String create(Song song) {
        songService.save(song);
        return "redirect:/admin/list";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable int id , ModelMap modelMap) {
        modelMap.addAttribute("song", songService.findById(id));
        return "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String edit(Song song) {
        songService.save(song);
        return "redirect:/admin/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        songService.delete(id);
        return "redirect:/admin/list";
    }
}
