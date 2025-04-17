package proto.traffic.game.map.structures;

import com.badlogic.gdx.utils.Array;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.path.PathNode;
import proto.traffic.game.map.structures.nodes.*;

public class BuildingManager {
    private final Array<ExportNode> exportNodes = new Array<>();
    private final Array<ImportNode> importNodes = new Array<>();
    private final Array<ReturnToNode> returnToNodes = new Array<>();
    private final Array<ReturnFromNode> returnFromNodes = new Array<>();

    private final ImportNodesComparator importNodesComparator = new ImportNodesComparator();
    private final ReturnToNodeComparator returnToNodeComparator = new ReturnToNodeComparator();

    private CarManager carManager;

    public BuildingManager(CarManager carManager) {
        this.carManager = carManager;
    }

    public ImportNode getImportNode (PathNode startNode) {
        importNodes.sort(importNodesComparator);
        for (ImportNode importNode : importNodes) {
            if (carManager.isRouteAccessible(startNode, importNode.getPathNode()) && importNode.getNeedsNum() > 0) {
                return importNode;
            }
        }

        return null;
    }

    public ReturnToNode getReturnToNode (PathNode startNode) {
        returnToNodes.sort(returnToNodeComparator);
        for (ReturnToNode returnToNode : returnToNodes) {
            if (carManager.isRouteAccessible(startNode, returnToNode.getPathNode()) && returnToNode.getNeedsNum() > 0) {
                return returnToNode;
            }
        }

        return null;
    }

    public void render () {
        for (ExportNode exportNode : exportNodes) {
            exportNode.spawnCar();
        }
        for (ReturnFromNode returnFromNode : returnFromNodes) {
            returnFromNode.spawnCar();
        }
    }

    public void addExportNode (ExportNode exportNode) {
        exportNodes.add(exportNode);
    }

    public void addImportNode (ImportNode importNode) {
        importNodes.add(importNode);
    }

    public void addReturnToNode (ReturnToNode returnToNode) {
        returnToNodes.add(returnToNode);
    }

    public void  addReturnFromNode (ReturnFromNode returnFromNode) {
        returnFromNodes.add(returnFromNode);
    }

    public CarManager getCarManager() {
        return carManager;
    }
}
