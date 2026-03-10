package project.physical;

import project.virtual.PageTableEntry;

public class PhysicalPage {

    private PageTableEntry entry;

    public PhysicalPage(PageTableEntry entry) {
        this.entry = entry;
    }

    public PageTableEntry getEntry() {
        return entry;
    }

    public void setEntry(PageTableEntry entry) {
        this.entry = entry;
    }
}
