public class File {
    private String name;
    private Object data;
    private int size;  // New field
    private String type;  // New field

    public File(String name, Object data, int size, String type) {
        this.name = name;
        this.data = data;
        this.size = size;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Object getData() {
        return this.data;
    }

    public int getSize() {  // New getter
        return this.size;
    }

    public String getType() {  // New getter
        return this.type;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {  // New setter
        this.size = size;
    }

    public void setType(String type) {  // New setter
        this.type = type;
    }
}
