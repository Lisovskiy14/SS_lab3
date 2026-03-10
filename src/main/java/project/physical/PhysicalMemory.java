package project.physical;

public class PhysicalMemory {
    private final PhysicalPage[] pages;

    public PhysicalMemory(PhysicalPage[] pages) {
        this.pages = pages;
    }

    public boolean isFull() {
        for (PhysicalPage page : pages) {
            if (page == null) {
                return false;
            }
        }
        return true;
    }

    public int getFreePageNumber() {
        for (int i = 0; i < pages.length; i++) {
            if (pages[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return pages.length;
    }

    public void addPage(PhysicalPage page, int physicalPageNumber) {
        pages[physicalPageNumber] = page;
    }

    public PhysicalPage getPage(int physicalPageNumber) {
        return pages[physicalPageNumber];
    }

    public PhysicalPage[] getArray() {
        return pages;
    }

}
