package laberinto.generator;

import laberinto.generator.beans.CityMap;
import laberinto.generator.beans.EllersMaze;
import laberinto.generator.beans.Maze;
import laberinto.generator.beans.RecursiveBacktrackMaze;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MazeGeneratorController {

    @RequestMapping("/city-map/generate")
    public Maze generateCityMap() {
        System.out.println("generating a new city map..");
        // Generate a new beans using a specific algorithm; determined by request
        // parameters (or have an endpoint for each one)
        Maze maze = new CityMap();
        maze.generate();
        return maze;
    }

    @RequestMapping("/ellers-algorithm/generate")
    public Maze generateMazeUsingEllersAlgorithm() {
        System.out.println("generating a beans using ellers method..");
        Maze maze = new EllersMaze();
        maze.generate();
        return maze;
    }

    @RequestMapping("/recursive-backtracking/generate")
    public Maze generateMazeUsingRecursiveBackTracking() {
        System.out.println("generating a beans using recursive back tracking..");
        Maze maze = new RecursiveBacktrackMaze();
        maze.generate();
        return maze;
    }
}
