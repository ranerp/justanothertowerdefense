package com.me.justanothertowerdefense;

import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class AStarPathFinder {

    private final float MOVEMENT_COST = 1f;
    private final float MOVEMENT_COST_DIAGONAL = 1.4f;

    private int nodeIdCounter = 0;

    private class Node implements Comparable {
        public int ID;
        public int x, y;
        public float f, g, h;
        public Node parent;

        public Node(int x, int y) {
            this(x, y, null);
        }

        public Node(int x, int y, Node parent) {
            nodeIdCounter++;
            this.ID = nodeIdCounter;
            this.x = x;
            this.y = y;
            f = g = h = 0;
            this.parent = parent;
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node)o;
            return (int)(f - n.f);
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node)o;

            if(n.x == x &&  n.y == y)
                return true;
            else
                return false;
        }

        @Override
        public int hashCode() {
            int hash = 23;
            hash = hash * 31 + Integer.toString(x).hashCode();
            hash = hash * 31 + Integer.toString(y).hashCode();
            return hash;
        }
    }

    private Node startNode;
    private Node finalNode;

    private int[][] map;

    private PriorityQueue<Node> openList;
    private HashMap<Node, Float> openListF;

    private PriorityQueue<Node> closeList;
    private HashMap<Node, Float> closeListF;

    public AStarPathFinder(int[][] map) {
        this.map = map;
    }

    public List<Vector2> getPathList(int startX, int startY, int finalX, int finalY) {
        openList = new PriorityQueue<Node>();
        openListF = new HashMap<Node, Float>();

        closeList = new PriorityQueue<Node>();
        closeListF = new HashMap<Node, Float>();

        List<Vector2> pathList = new ArrayList<Vector2>();

        startNode = new Node(startX, startY);
        finalNode = new Node(finalX, finalY);
        calculateNodeCost(startNode, startNode);
        openList.offer(startNode);
        openListF.put(startNode, startNode.f);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            List<Node> successors = generateSuccessors(currentNode);
            Iterator<Node> it = successors.iterator();
            while(it.hasNext()) {
                Node successor = it.next();
                calculateNodeCost(successor, currentNode);

               if(successor.x == finalNode.x && successor.y == finalNode.y) {
                   for (Node n = successor; n.parent != null; n = n.parent)
                       pathList.add(new Vector2(n.x, n.y));

                   return pathList;
               }

               if (!(openList.contains(successor) && openListF.get(successor) <= successor.f)
                       &&
                   !(closeList.contains(successor) && closeListF.get(successor) <= successor.f)
                  ) {
                   openList.offer(successor);
                   openListF.put(successor, successor.f);
               }
            }

            closeList.offer(currentNode);
            closeListF.put(currentNode, currentNode.f);
        }
        return pathList;
    }

    public List<Node> generateSuccessors(Node parent) {
        List<Node> successors = new LinkedList<Node>();

        if (parent.x - 1 >= 0 && parent.y - 1 >= 0) {
            if (map[parent.x - 1][parent.y - 1] == TileState.WALKABLE.getState() && map[parent.x][parent.y - 1] == TileState.WALKABLE.getState() && map[parent.x - 1][parent.y] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x - 1, parent.y - 1, parent));
        }
        if (parent.x - 1 >= 0) {
            if(map[parent.x - 1][parent.y] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x - 1, parent.y, parent));
        }
        if (parent.x - 1 >= 0 && parent.y + 1 < map[0].length) {
            if(map[parent.x - 1][parent.y + 1] == TileState.WALKABLE.getState() && map[parent.x - 1][parent.y] == TileState.WALKABLE.getState() && map[parent.x][parent.y + 1] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x - 1, parent.y + 1, parent));
        }
        if (parent.y - 1 >= 0) {
            if(map[parent.x][parent.y - 1] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x, parent.y - 1, parent));
        }
        if (parent.y + 1 < map[0].length) {
            if(map[parent.x][parent.y + 1] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x, parent.y + 1, parent));
        }
        if (parent.x + 1 < map.length && parent.y + 1 < map[0].length) {
            if(map[parent.x + 1][parent.y + 1] == TileState.WALKABLE.getState() && map[parent.x + 1][parent.y] == TileState.WALKABLE.getState() && map[parent.x][parent.y + 1] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x + 1, parent.y + 1, parent));
        }
        if (parent.x + 1 < map.length) {
            if(map[parent.x + 1][parent.y] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x + 1, parent.y, parent));
        }
        if (parent.x + 1 < map.length && parent.y - 1 >= 0) {
            if(map[parent.x + 1][parent.y - 1] == TileState.WALKABLE.getState() && map[parent.x][parent.y - 1] == TileState.WALKABLE.getState() && map[parent.x + 1][parent.y] == TileState.WALKABLE.getState())
                successors.add(new Node(parent.x + 1, parent.y - 1, parent));
        }

        return successors;
    }

    public void setUnwalkable(int x, int y) {
        map[x][y] = TileState.UNWALKABLE.getState();
    }

    public void setWalkable(int x, int y) {
        map[x][y] = TileState.WALKABLE.getState();
    }

    private void calculateNodeCost(Node currentNode, Node parentNode) {
        currentNode.g = g(parentNode, currentNode);
        currentNode.h = h(currentNode, finalNode);
        currentNode.f = f(currentNode.g, currentNode.h);
    }

    private float f(float g, float h) {
        return g + h;
    }

    private float h(Node from, Node to) {
        float dx = Math.abs(from.x - to.x);
        float dy = Math.abs(from.y - to.y);

        return MOVEMENT_COST * (dx + dy) + (MOVEMENT_COST_DIAGONAL - 2 * MOVEMENT_COST) * Math.min(dx, dy);
    }

    private float g(Node from, Node to) {
        if(from.x == to.x && from.y == to.y)
            return 0.0f;
        else if((from.x == to.x && (from.y - 1 == to.y || from.y + 1 == to.y))
                    ||
                (from.y == to.y && (from.x - 1 == to.x || from.y + 1 == to.y))
               )
            return from.g + MOVEMENT_COST;
        else
            return from.g + MOVEMENT_COST_DIAGONAL;
    }
}
