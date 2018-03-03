package laberinto.generator;

import laberinto.generator.beans.CityMap;
import laberinto.generator.beans.EllersMaze;
import laberinto.generator.beans.Maze;
import laberinto.generator.beans.RecursiveBacktrackMaze;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maze")
public class MazeGeneratorController {

    @RequestMapping("/city-map/generate")
    public Maze generateCityMap(@RequestParam(value="dimension", defaultValue="20") int dimension) {
        System.out.println("generating a new city map..");
        System.out.println("Dimension: " +dimension);
        Maze maze = new CityMap();
        maze.generate(10);
        return maze;
    }

    @RequestMapping("/ellers-algorithm/generate")
    public Maze generateMazeUsingEllersAlgorithm(
            @RequestParam(value="dimension", defaultValue="20") int dimension) {
        System.out.println("generating a beans using ellers method..");
        Maze maze = new EllersMaze();
        maze.generate(10);
        return maze;
    }

    @RequestMapping("/recursive-backtracking/generate")
    public Maze generateMazeUsingRecursiveBackTracking(
            @RequestParam(value="dimension", defaultValue="20") int dimension) {
        System.out.println("generating a beans using recursive back tracking..");
        Maze maze = new RecursiveBacktrackMaze();
        maze.generate(10);
        return maze;
    }
}
