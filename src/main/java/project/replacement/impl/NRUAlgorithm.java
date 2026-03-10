package project.replacement.impl;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.replacement.ReplacementAlgorithm;
import project.virtual.PageTableEntry;

public class NRUAlgorithm implements ReplacementAlgorithm {

    @Override
    public void replace(PhysicalMemory physicalMemory, PageTableEntry entry) {
        PhysicalPage[] pages = physicalMemory.getArray();

        PhysicalPage targetPage = null;
        int bestClassFound = 4;

        for (PhysicalPage page : pages) {
            PageTableEntry currentEntry = page.getEntry();

            int pageClass = calculateNRUClass(currentEntry);

            if (pageClass < bestClassFound) {
                bestClassFound = pageClass;
                targetPage = page;

                if (bestClassFound == 0) break;
            }
        }

        if (targetPage != null) {
            PageTableEntry prevEntry = targetPage.getEntry();

            prevEntry.setPresence(false);
            prevEntry.setReferenced(false);
            prevEntry.setModified(false);

            entry.setPhysicalPageNumber(prevEntry.getPhysicalPageNumber());
            prevEntry.setPhysicalPageNumber(-1);

            targetPage.setEntry(entry);
        }
    }

    private int calculateNRUClass(PageTableEntry entry) {
        if (!entry.isReferenced() && !entry.isModified()) return 0;
        if (!entry.isReferenced() && entry.isModified()) return 1;
        if (entry.isReferenced() && !entry.isModified()) return 2;
        return 3;
    }
}
