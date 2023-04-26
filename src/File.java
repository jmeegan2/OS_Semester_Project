public class File {
    private String name;
    private Object data;

    public File(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}