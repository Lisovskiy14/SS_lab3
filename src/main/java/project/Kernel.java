package project;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.replacement.ReplacementAlgorithm;
import project.virtual.PageTableEntry;

public class Kernel {
    private final PhysicalMemory physicalMemory;
    private final ReplacementAlgorithm replacementAlgorithm;

    public Kernel(PhysicalMemory physicalMemory, ReplacementAlgorithm replacementAlgorithm) {
        this.physicalMemory = physicalMemory;
        this.replacementAlgorithm = replacementAlgorithm;
    }

    public void replacePage(PageTableEntry entry, boolean isWrite) {
        entry.setPresence(true);
        entry.setReferenced(true);
        entry.setModified(isWrite);

        if (!physicalMemory.isFull()) {
            int freePageNumber = physicalMemory.getFreePageNumber();
            entry.setPhysicalPageNumber(freePageNumber);
            physicalMemory.addPage(
                    new PhysicalPage(entry),
                    freePageNumber
            );
        } else {
            replacementAlgorithm.replace(physicalMemory, entry);
        }
    }
}
