package cn.hauerwu.structure;

public class RBTree<K,V> {
    private enum RBTreeNodeColor{
        RED,
        BLACK
    }

    private final RBTreeNode nilNode = new RBTreeNode(null,null,RBTreeNodeColor.BLACK);

    private class RBTreeNode{
        public RBTreeNode getParent() {
            return parent;
        }

        public void setParent(RBTreeNode parent) {
            this.parent = parent;
        }

        public RBTreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(RBTreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public RBTreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(RBTreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBTreeNodeColor getColor() {
            return color;
        }

        public void setColor(RBTreeNodeColor color) {
            this.color = color;
        }

        public boolean isLeftChild(){
            return getParent() != null && getParent().getLeftChild().equals(this);
        }

        public boolean isRightChild(){
            return getParent() != null && getParent().getRightChild().equals(this);
        }

        public RBTreeNode getGrand(){
            RBTreeNode parent = getParent();
            if(parent == null){
                return null;
            }

            return parent.getParent();
        }

        public RBTreeNode getUncle(){
            RBTreeNode grand = getGrand();
            if(grand == null){
                return null;
            }
            return getParent().isLeftChild()?grand.getRightChild():grand.getLeftChild();
        }

        public RBTreeNode(K key,V value,RBTreeNodeColor color,RBTreeNode parent){
            this.setKey(key);
            this.setValue(value);
            this.setColor(color);
            this.setParent(parent);
            this.setRightChild(nilNode);
            this.setLeftChild(nilNode);
        }

        public RBTreeNode(K key,V value,RBTreeNodeColor color){
            this(key,value,color,nilNode);
        }

        public RBTreeNode(K key,V value){
            this(key,value,RBTreeNodeColor.RED);
        }

        private RBTreeNode parent;
        private RBTreeNode rightChild;
        private RBTreeNode leftChild;
        private K key;
        private V value;
        private RBTreeNodeColor color;
    }

    private RBTreeNode root;
    private int size;

    public int getSize() {
        return size;
    }

    public RBTree(){
        root = nilNode;
        size = 0;
    }

    public void put(K key,V value){
        if(root == nilNode){
            root = new RBTreeNode(key,value,RBTreeNodeColor.BLACK);
            size++;
        }else{
            RBTreeNode node = findNode(key);
            int compareResult = ((Comparable)key).compareTo(node.getKey());
            if(node != nilNode && compareResult == 0){
                node.setValue(value);
            }else{
                RBTreeNode newNode = new RBTreeNode(key,value,RBTreeNodeColor.RED);
                if(compareResult == -1){
                    node.setLeftChild(newNode);
                }else{
                    node.setRightChild(newNode);
                }
                newNode.setParent(node);

                fixInsert(newNode);
                size++;
            }
        }
    }

    public V get(K key){
       RBTreeNode node = findNode(key);

       if(node != nilNode && ((Comparable)key).compareTo(node.getKey()) == 0){
           return node.getValue();
       }

       return null;
    }

    public RBTreeNode remove(K key){
        RBTreeNode z = findNode(key);
        if(z == nilNode || ((Comparable)key).compareTo(z.getKey()) != 0) {
            return nilNode;
        }

        RBTreeNode y = z;
        RBTreeNode x = nilNode;
        RBTreeNodeColor originColorY = y.getColor();
        if(z.getLeftChild() == nilNode){
            x = z.getRightChild();
            transplat(z,z.getRightChild());
        }else if(z.getRightChild() == nilNode){
            x = z.getLeftChild();
            transplat(z,z.getLeftChild());
        }else{
            y = minimum(z.getRightChild());
            originColorY = y.getColor();
            x = y.getRightChild();
            if(y.getParent() == z){
                x.setParent(y);
            }else{
                transplat(y,y.getRightChild());
                y.setRightChild(z.getRightChild());
                y.getRightChild().setParent(y);
            }
            transplat(z,y);
            y.setLeftChild(z.getLeftChild());
            y.getLeftChild().setParent(y);
            y.setColor(z.getColor());
        }

        if(originColorY == RBTreeNodeColor.BLACK){
            fixRemove(x);
        }

        this.size -= 1;

        return z;
    }

    public int getTreeHeight(){
        return getNodeHeight(this.root);
    }

    private int getNodeHeight(RBTreeNode node){
        if(node == nilNode){
            return 0;
        }
        return Math.max(getNodeHeight(node.getLeftChild()),getNodeHeight(node.getRightChild()))+1;
    }

    //???????????????????????????
    //result?????????????????????????????????????????????????????????
    private RBTreeNode findNode(K key){
        RBTreeNode it= nilNode;
        RBTreeNode parent = nilNode;
        int compareResult = 0;
        for(it = this.root;it != nilNode && (compareResult = ((Comparable)key).compareTo(it.getKey()))!=0;){
            parent = it;
            if (compareResult < 0) {
                it = it.getLeftChild();
            }else{
                it = it.getRightChild();
            }
        }

        if(compareResult==0){
            return it;
        }

        return parent;
    }

    //?????????x???????????????
    private void rotateLeft(RBTreeNode x){
        if(x == nilNode || x.getRightChild() == nilNode){
            return;
        }

        RBTreeNode y = x.getRightChild();

        x.setRightChild(y.getLeftChild());
        if(y.getLeftChild() != nilNode){
            y.getLeftChild().setParent(x);
        }

        y.setParent(x.getParent());

        if(y.getParent() ==  nilNode){
            this.root = y;
        }else if(x.isLeftChild()){
            x.getParent().setLeftChild(y);
        }else{
            x.getParent().setRightChild(y);
        }
        y.setLeftChild(x);
        x.setParent(y);
    }

    //?????????x???????????????
    private void rotateRight(RBTreeNode x){
        if(x == nilNode || x.getLeftChild() == nilNode){
            return;
        }

        RBTreeNode y = x.getLeftChild();

        x.setLeftChild(y.getRightChild());
        if(y.getRightChild() != nilNode){
            y.getRightChild().setParent(x);
        }

        y.setParent(x.getParent());
        if(x.getParent()==nilNode){
            this.root = y;
        }else if(x.isLeftChild()){
            x.getParent().setLeftChild(y);
        }else{
            x.getParent().setRightChild(y);
        }
        y.setRightChild(x);
        x.setParent(y);
    }

    //???z????????????????????????????????????
    private void fixInsert(RBTreeNode z){
        while(z.getParent() != nilNode && z.getParent().getColor() == RBTreeNodeColor.RED){
            if(z.getParent().isLeftChild()){
                RBTreeNode y = z.getParent().getParent().getRightChild();
                if(y.getColor() == RBTreeNodeColor.RED){
                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    y.setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);

                    z = z.getParent().getParent();
                }else{
                    if(z.isRightChild()) {
                        z = z.getParent();
                        rotateLeft(z);
                    }
                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    rotateRight(z.getParent().getParent());
                }
            }else{
                RBTreeNode y = z.getParent().getParent().getLeftChild();
                if(y.getColor() == RBTreeNodeColor.RED){
                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    y.setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);

                    z = z.getParent().getParent();
                }else{
                    if(z.isLeftChild()){
                        z = z.getParent();
                        rotateRight(z);
                    }
                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    rotateLeft(z.getParent().getParent());
                }
            }
        }
        this.root.setColor(RBTreeNodeColor.BLACK);
    }

    //???z????????????????????????????????????
    private void fixRemove(RBTreeNode x) {
        RBTreeNode w = nilNode;
        while(x != this.root && x.getColor() == RBTreeNodeColor.BLACK){
            if(x.isLeftChild()){
                w = x.getParent().getRightChild();

                //??????1???x?????????w???????????????????????????2???3???4??????
                if(w.getColor() == RBTreeNodeColor.RED){
                    w.setColor(RBTreeNodeColor.BLACK);
                    x.getParent().setColor(RBTreeNodeColor.RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRightChild();
                }

                //??????2???w??????????????????????????????
                if(w.getLeftChild().getColor() == RBTreeNodeColor.BLACK &&
                        w.getRightChild().getColor() == RBTreeNodeColor.BLACK)
                {
                    w.setColor(RBTreeNodeColor.RED);
                    x = x.getParent();
                }
                //??????3???w??????????????????????????????????????????
                else if(w.getRightChild().getColor() == RBTreeNodeColor.BLACK){
                    w.getLeftChild().setColor(RBTreeNodeColor.BLACK);
                    w.setColor(RBTreeNodeColor.RED);
                    rotateRight(w);
                    w = x.getParent().getRightChild();
                }
                //??????4???w???????????????????????????????????????????????????
                else{
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBTreeNodeColor.BLACK);
                    w.getRightChild().setColor(RBTreeNodeColor.BLACK);
                    rotateLeft(x.getParent());
                    x = this.root;
                }
            }else{
                w = x.getParent().getLeftChild();

                //??????1???x?????????w???????????????????????????2???3???4??????
                if(w.getColor() == RBTreeNodeColor.RED){
                    w.setColor(RBTreeNodeColor.BLACK);
                    x.getParent().setColor(RBTreeNodeColor.RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeftChild();
                }

                //??????2???w??????????????????????????????
                if(w.getLeftChild().getColor() == RBTreeNodeColor.BLACK &&
                        w.getRightChild().getColor() == RBTreeNodeColor.BLACK)
                {
                    w.setColor(RBTreeNodeColor.RED);
                    x = x.getParent();
                }
                //??????3???w??????????????????????????????????????????
                else if(w.getLeftChild().getColor() == RBTreeNodeColor.BLACK){
                    w.getRightChild().setColor(RBTreeNodeColor.BLACK);
                    w.setColor(RBTreeNodeColor.RED);
                    rotateLeft(w);
                    w = x.getParent().getLeftChild();
                }
                //??????4???w???????????????????????????????????????????????????
                else{
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBTreeNodeColor.BLACK);
                    w.getLeftChild().setColor(RBTreeNodeColor.BLACK);
                    rotateRight(x.getParent());
                    x = this.root;
                }
            }
        }
        x.setColor(RBTreeNodeColor.BLACK);
    }

    //??????????????????????????????????????????????????????
    private RBTreeNode minimum(RBTreeNode x){
        while(x.getLeftChild() != nilNode){
            x = x.getLeftChild();
        }
        return x;
    }

    //?????????v????????????u?????????
    private void transplat(RBTreeNode u,RBTreeNode v){
        if(u.getParent() == nilNode){
            this.root = v;
        }else if(u.isLeftChild()){
            u.getParent().setLeftChild(v);
        }else{
            u.getParent().setRightChild(v);
        }

        v.setParent(u.getParent());
    }


}
