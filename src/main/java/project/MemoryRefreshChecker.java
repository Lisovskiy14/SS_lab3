package project;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.util.DataUtil;

public class MemoryRefreshChecker {
    private final int maxCount;
    private int counter = 0;

    private final PhysicalMemory physicalMemory;

    public MemoryRefreshChecker(PhysicalMemory physicalMemory) {
        this.maxCount = DataUtil.MAX_COUNT_FOR_REFRESH;
        this.physicalMemory = physicalMemory;
    }

    public void checkForRefresh() {
        System.out.println("checkForRefresh " + counter + "/" + maxCount);
        counter++;
        if (counter >= maxCount) {
            refresh();
            counter = 0;
        }
    }

    private void refresh() {
        System.out.println("Refreshing memory...");
        if (physicalMemory == null) {
            return;
        }

        PhysicalPage[] pages = physicalMemory.getArray();
        for (PhysicalPage page : pages) {
            if (page != null) {
                page.getEntry().setReferenced(false);
            }
        }
    }
}
