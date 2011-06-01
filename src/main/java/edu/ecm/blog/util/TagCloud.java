package edu.ecm.blog.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCloud {

    private List<String> tags = new ArrayList<String>();

    public void add(String... tags) {
        if (tags == null) {
            return;
        }

        for (String tag : tags) {
            if (tag != null && !"".equals(tag) && !contains(tag)) {
                this.tags.add(tag);
            }
        }
    }

    public int size() {
        return tags.size();
    }

    public boolean contains(String tag) {
        return tags.contains(tag);
    }

    public void top(int count) {
        if (count <= 0) {
            tags.clear();
        } else {
            tags = tags.subList(0, Math.min(tags.size(), count));
        }
    }

    public void shuffle() {
        Collections.shuffle(tags);
    }
}
