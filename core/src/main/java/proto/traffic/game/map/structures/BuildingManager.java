package proto.traffic.game.map.structures;

import com.badlogic.gdx.utils.Array;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.path.PathNode;
import proto.traffic.game.map.structures.nodes.ExportNode;
import proto.traffic.game.map.structures.nodes.ImportNode;
import proto.traffic.game.map.structures.nodes.ImportNodesComparator;

public class BuildingManager {
    private Array<ExportNode> exportNodes = new Array<>();
    private Array<ImportNode> importNodes = new Array<>();

    private CarManager carManager;

    public BuildingManager(CarManager carManager) {
        this.carManager = carManager;
    }

    private ImportNodesComparator importNodesComparator = new ImportNodesComparator();

    public ImportNode getImportNode (PathNode startNode) {
        importNodes.sort(importNodesComparator);
        for (ImportNode importNode : importNodes) {
            if (carManager.isRouteAccessible(startNode, importNode.getPathNode())) {
                return importNode;
            }
        }

        return null;
    }

    public void render () {
        for (ExportNode exportNode : exportNodes) {
            exportNode.spawnCar();
        }
    }

    public void addExportNode (ExportNode exportNode) {
        exportNodes.add(exportNode);
    }

    public void addImportNode (ImportNode importNode) {
        importNodes.add(importNode);
    }
}
