package project.virtual;

public class PageTableEntry {

    private boolean presence = false;
    private boolean referenced = false;
    private boolean modified = false;
    private int physicalPageNumber;

    // Getters
    public boolean isPresence() {
        return presence;
    }
    public boolean isReferenced() {
        return referenced;
    }
    public boolean isModified() {
        return modified;
    }
    public int getPhysicalPageNumber() {
        return physicalPageNumber;
    }


    //Setters
    public void setPresence(boolean presence) {
        this.presence = presence;
    }
    public void setReferenced(boolean referenced) {
        this.referenced = referenced;
    }
    public void setModified(boolean modified) {
        this.modified = modified;
    }
    public void setPhysicalPageNumber(int physicalPageNumber) {
        this.physicalPageNumber = physicalPageNumber;
    }
}
