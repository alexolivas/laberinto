package laberinto.generator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MazeGeneratorController {

    @RequestMapping("/generate")
    public Maze generate() {
        System.out.println("generating a new maze");
        return new Maze();
    }

}
