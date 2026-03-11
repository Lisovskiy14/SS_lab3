package project;

import project.exception.PageFaultException;
import project.virtual.PageTableEntry;

public class MMU {

    public static void access(PageTableEntry entry, boolean isWrite) {
        if (entry.isPresence()) {
            entry.setReferenced(true);
            if (isWrite) {
                entry.setModified(true);
            }
        } else {
            throw new PageFaultException();
        }
    }
}
