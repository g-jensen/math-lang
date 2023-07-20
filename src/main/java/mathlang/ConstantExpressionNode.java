package mathlang;

public class ConstantExpressionNode extends BinaryExpressionNode {
    public ConstantExpressionNode(Integer value) {
        super();
        this.value = value;
    }
    public Integer evaluate() {
        return value;
    }
    private Integer value;
}
