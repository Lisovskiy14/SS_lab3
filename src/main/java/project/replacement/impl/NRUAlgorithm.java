package project.replacement.impl;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.replacement.ReplacementAlgorithm;
import project.virtual.PageTableEntry;

import java.util.*;

public class NRUAlgorithm implements ReplacementAlgorithm {
    private final Random random = new Random();

    @Override
    public void replace(PhysicalMemory physicalMemory, PageTableEntry entry) {
        PhysicalPage[] pages = physicalMemory.getArray();

        List<PhysicalPage> targetPages = new ArrayList<>();
        int bestClassFound = 4;

        for (PhysicalPage page : pages) {
            PageTableEntry currentEntry = page.getEntry();

            int pageClass = calculateNRUClass(currentEntry);

            if (pageClass < bestClassFound) {
                bestClassFound = pageClass;
                targetPages.clear();
                targetPages.add(page);
            } else if (pageClass == bestClassFound) {
                targetPages.add(page);
            }
        }

        int targetPageNumber = random.nextInt(targetPages.size());
        PhysicalPage targetPage = targetPages.get(targetPageNumber);


        PageTableEntry prevEntry = targetPage.getEntry();

        prevEntry.setPresence(false);
        prevEntry.setReferenced(false);
        prevEntry.setModified(false);

        entry.setPhysicalPageNumber(prevEntry.getPhysicalPageNumber());
        prevEntry.setPhysicalPageNumber(-1);

        targetPage.setEntry(entry);
    }

    private int calculateNRUClass(PageTableEntry entry) {
        System.out.println("R: " + entry.isReferenced() + "; M: " + entry.isModified());
        if (!entry.isReferenced() && !entry.isModified()) return 0;
        if (!entry.isReferenced() && entry.isModified()) return 1;
        if (entry.isReferenced() && !entry.isModified()) return 2;
        return 3;
    }
}
