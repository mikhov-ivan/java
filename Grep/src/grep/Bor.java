package grep;

import java.util.ArrayList;

class Link {
    char c;
    Node node;
    
    public Link(char c, Node node) {
        this.c = c;
        this.node = node;
    }
}

class Node {
    ArrayList<Link> sons, go;
    Node parent, suffLink, up;
    char charToParent;
    boolean terminal;
    ArrayList<Integer> terminalPatternNumber;
    
    public Node() {
        this.sons = new ArrayList<>();
        this.go = new ArrayList<>();
        this.terminalPatternNumber = new ArrayList<>();
    }
    
    public Node getSonByChar(char c) {
        for (int i = 0; i < sons.size(); i++) {
            if (sons.get(i).c == c) {
               return sons.get(i).node;
            }
        }
        return null;
    }
    
    public Node getGoByChar(char c) {
        for (int i = 0; i < go.size(); i++) {
            if (go.get(i).c == c) {
               return go.get(i).node;
            }
        }
        return null;
    }
}

public class Bor {
    boolean anyFound = false;
    int numNodes = 0;
    Node root;
    
    public Bor() {
        root = new Node();        
        root.suffLink = null;
        root.up = null;
        root.parent = null;
        root.charToParent = '#';
        root.terminal = false;
        addPattern("\n", 0);
    }
    
    public void addPattern(String p, int num) {
        Node curNode = root;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (curNode.getSonByChar(c) == null) {
                numNodes++;
                curNode.sons.add(new Link(c, new Node()));
                int pos = curNode.sons.size() - 1;
                curNode.sons.get(pos).node.suffLink = null;
                curNode.sons.get(pos).node.up = null;
                curNode.sons.get(pos).node.parent = curNode;
                curNode.sons.get(pos).node.charToParent = c;
                curNode.sons.get(pos).node.terminal = false;
            }
            curNode = curNode.getSonByChar(c);
        }
        curNode.terminal = true;
        curNode.terminalPatternNumber.add(num);
    }
    
    private Node getSuffLink(Node v) {
        if (v.suffLink == null) {
            if (v == root || v.parent == root) {
                v.suffLink = root;
            } else {
                v.suffLink = getGo(getSuffLink(v.parent), v.charToParent);
            }
        }
        return v.suffLink;
    }
    
    Node getGo(Node v, char c) {
        Node g = v.getGoByChar(c), s = v.getSonByChar(c);
        if (g == null) {
            if (s != null) {
                g = s;
            } else {
                g = (v == root) ? v : getGo(getSuffLink(v), c);
            }
        }
        return g;
    }
}