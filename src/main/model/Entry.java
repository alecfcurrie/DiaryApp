package model;

import java.util.Objects;

public abstract class Entry {
    private String title;
    private String desc;

    protected Entry(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return title.equals(entry.title) && desc.equals(entry.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, desc);
    }
}
