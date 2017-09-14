# meshgenerator
The meshgenerator is an implementation of a graph grammar rewriting engine and a 3D graph visualization tool. The project has been created within the Graph Grammars classes held at the AGH University of Science and Technology, Cracow.

## Setup
### Prerequisites
It is necessary to have a `Java 1.8` distribution installed. To download required dependencies, the `Apache Maven` build tool may be used.

## Grammar productions
![productionsV1](https://i.imgur.com/5XGGhN6.png)

## Visualization
### Running
In order to open the visualization app, please compile Java sources and run the `pl.edu.agh.iet.gg.meshgenerator.visualization.GraphVisualizer` class.
### Features
1. Rotation. In order to rotate a 3D view, left-click within the visualization window and drag a mouse in proper direction holding the left mouse button. The same may be obtained by pressing the arrow keys (up/down and left/right). Please note that the rotation strategy may be changed by setting another implementation of the `pl.edu.agh.gg.visualiser.view.rotation.strategy.RotationStrategy` interface in the configuration file. There are two rotation strategies implemented: around the `(0,0,0)` point and around the axes of the 3D coordinates system.
2. Scaling. Use the mouse scroll to zoom in/out the visualization. Note that, by default, the scaling pivot is set to the `(0,0,0)` point. It is possible to change the pivot to one of drawn vertices by left-clinking requested node while holding the control key.
3. Applying the productions. A graph node may be selected by left-clicking it. The `P1` production may be applied on a selected node by clicking the '1' (numeric) key; the `P2` productions may be applied on a selected node by clicking one of the: 'W', 'A', 'S' or 'D' buttons, corresponding to an appropriate production; the `P3` production may be applied on a selected node by clicking the '3' (numeric) key.
4. Visualization clipping. Only selected levels of a visualized graph may be shown by selecting a range in the menu on the left and clicking the 'Repaint' button.
5. Serialization and deserialization.
Please note that the visualizer may be configured by editing the config file available under: `resources/pl/edu/agh/iet/gg/meshgenerator/visualization/config/visualizer.properties`.

