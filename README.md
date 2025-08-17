## Project Overview  

This project implements a **2D pathfinding system** on a procedurally generated 10×10 grid, designed for real-time strategy (RTS) game environments. Each cell in the grid represents a node with a defined elevation, affecting the cost of traversal. The project demonstrates advanced use of **graph data structures**, **graph traversal algorithms**, and **weighted pathfinding**.  

Key highlights:  

- Graph-based representation of a 2D grid with nodes and edges.  
- Support for **eight-directional movement** (N, S, E, W, NE, SE, NW, SW).  
- Elevation-based traversal costs, integrating realistic movement difficulty.  
- Implementation of multiple **search algorithms**:  
  - **Breadth-First Search (BFS)** – finds the shortest path ignoring edge weights.  
  - **Depth-First Search (DFS)** – explores paths exhaustively, demonstrating algorithmic depth.  
  - **Dijkstra’s Algorithm** – calculates the least-cost path considering edge weights.  

---

## Implemented Packages and Classes

### 1. core
- **Position** – Represents 2D coordinates with Euclidean distance calculation.  
- **Node** – Represents a single cell in the grid, storing elevation and edges.  
- **Edge** – Represents a connection between nodes with a weight calculated using Euclidean distance and elevation difference.  
- **Graph** – Handles grid construction, node connections, and pathfinding algorithms (BFS, DFS, Dijkstra).  

### 2. gui
- **GraphGUI** – Provides an interactive GUI for visualizing the graph, nodes, and traversal paths.  
- **GraphRender** – Handles rendering of nodes, edges, and animations for pathfinding visualization.  
- **Interactive Controls:**  
  - Toggle display of **elevations**, **polygons**, and **nodes**.  
  - Adjust **animation speed** for traversal visualization.  
  - Select **start node**, **target node**, and **search algorithm**.  

---

## Features Implemented

✅ Fully connected 10×10 grid graph with up to **eight edges per node**.  
✅ Correct **edge weight calculation** considering elevation differences.  
✅ BFS, DFS, and Dijkstra search methods returning ordered paths.  
✅ GUI visualization with real-time animation of path traversal.  
✅ Unit tests for **Position**, **Edge**, and **Graph** classes.  

---

## Running the Project GUI

> **Prerequisites:**  
> - Java JDK installed (version ≥ 20)  
> - JavaFX 20.0.2 library  

**Steps to Run:**  

1. **Download and extract JavaFX SDK** from [GluonHQ](https://gluonhq.com/products/javafx/).  
2. **Set up JavaFX as a user library** in Eclipse:  
   - Window → Preferences → Java → Build Path → User Libraries → New → Name: `JavaFX`  
   - Add all `.jar` files from the `lib` folder of JavaFX SDK.  
3. **Add JavaFX library to project:**  
   - Right-click project → Properties → Java Build Path → Libraries → Modulepath → Add Library → User Library → JavaFX.  
4. **Configure VM arguments for GUI:**  
   - Windows:  
     ```
     --module-path "C:\path\to\javafx-sdk-20\lib" --add-modules javafx.controls,javafx.fxml
     ```  
   - macOS/Linux:  
     ```
     --module-path /path/to/javafx-sdk-20/lib --add-modules javafx.controls,javafx.fxml
     ```  
5. **Run GraphGUI:**  
   - Right-click project → Run As → Java Application → Select `GraphGUI`.  
   - Adjust `GraphRender` constants for **start node**, **target node**, **algorithm**, and **animation speed** as needed.  

---

## Testing

Unit tests are provided for each class and can be run via **JUnit**:  

- **PositionTest** – validates Euclidean distance calculation.  
- **EdgeTest** – validates edge creation and weight computation.  
- **GraphTest** – validates node connections and pathfinding algorithm correctness.  

---

## Outcome

This project demonstrates the ability to:  

- Implement complex **graph data structures** in Java.  
- Apply multiple **search algorithms** in practical scenarios.  
- Integrate **algorithm logic with GUI visualization**, improving interpretability and user experience.  
- Write **modular, maintainable, and testable code**, following professional software engineering standards.  
