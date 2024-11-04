class SkipListNode {
    public String element;
    public SkipListNode[] next;
    public SkipListNode(String s,int lanes) {
        element = s;
        next = new SkipListNode[lanes];
    }
}

