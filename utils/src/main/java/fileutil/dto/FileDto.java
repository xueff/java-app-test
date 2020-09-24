package fileutil.dto;

public class FileDto {

    private String path;
    private String name;
    private long size;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileDto(String path, String name, long size) {
        this.path = path;
        this.name = name;
        this.size = size;
    }

    public FileDto() {
    }
}
