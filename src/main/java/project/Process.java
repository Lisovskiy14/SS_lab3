package project;

import project.exception.PageFaultException;
import project.virtual.PageTable;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Process {
    private final Random random = new Random();
    private final Kernel kernel;

    private final int id;
    private PageTable pageTable;
    private int workingRange;

    public Process(int id, PageTable pageTable, Kernel kernel) {
        this.id = id;
        this.pageTable = pageTable;
        this.workingRange = pageTable.getWorkingRange();
        this.kernel = kernel;
    }

    public void generateAccess() {
        int virtualPageNumber;
        if (chance(0.9)) {
            virtualPageNumber = random.nextInt(workingRange);
        } else {
            virtualPageNumber = random.nextInt(pageTable.size());
        }

        boolean isWrite = chance(0.5);

        try {
            Logger.logAccess();
            MMU.access(pageTable.getEntry(virtualPageNumber), isWrite);
        } catch (PageFaultException ex) {
            Logger.logPageFault();
            kernel.replacePage(pageTable.getEntry(virtualPageNumber), isWrite);
        }
    }

    private boolean chance(double probability) {
        return ThreadLocalRandom.current().nextDouble() < probability;
    }

}
