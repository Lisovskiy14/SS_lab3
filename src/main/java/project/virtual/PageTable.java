package project.virtual;

import project.util.DataUtil;

public class PageTable {

    private PageTableEntry[] entries;

    public PageTable(int size) {
        this.entries = new PageTableEntry[size];
    }

    public boolean isEmpty() {
        return entries.length == 0;
    }

    public boolean addEntry(PageTableEntry entry, int virtualPageNumber) {
        if (!isValidVirtualPageNumber(virtualPageNumber)) {
            return false;
        }
        entries[virtualPageNumber] = entry;
        return true;
    }

    public PageTableEntry getEntry(int virtualPageNumber) {
        if (!isValidVirtualPageNumber(virtualPageNumber)) {
            return null;
        }
        return entries[virtualPageNumber];
    }

    public int size() {
        return entries.length;
    }

    public int getWorkingRange() {
        return Math.min(DataUtil.WORKING_SET_SIZE, entries.length);
    }

    private boolean isValidVirtualPageNumber(int virtualPageNumber) {
        return virtualPageNumber >= 0 && virtualPageNumber < entries.length;
    }
}
