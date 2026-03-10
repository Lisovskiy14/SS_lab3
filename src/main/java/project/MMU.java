package project;

import project.exception.PageFaultException;
import project.virtual.PageTableEntry;

public class MMU {

    public static void access(PageTableEntry entry, boolean isWrite) {
        if (entry.isPresence()) {
            entry.setReferenced(true);
            entry.setModified(isWrite);
        } else {
            throw new PageFaultException();
        }
    }
}
