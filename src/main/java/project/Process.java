package project;

import project.exception.PageFaultException;
import project.util.DataUtil;
import project.virtual.PageTable;
import project.virtual.PageTableEntry;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Process {
    private final Random random = new Random();
    private final Kernel kernel;

    private final int id;
    private PageTable pageTable;
    private PageTable workingSet;
    private int counter = 0;

    public Process(int id, PageTable pageTable, Kernel kernel) {
        this.id = id;
        this.pageTable = pageTable;
        this.workingSet = generateWorkingRange();
        this.kernel = kernel;
    }

    public void generateAccess() {
        checkForRefreshWorkingSet();

        PageTableEntry entry;
        if (chance(0.9)) {
            entry = workingSet.getEntry(random.nextInt(workingSet.size()));
        } else {
            entry = pageTable.getEntry(random.nextInt(pageTable.size()));
        }

        boolean isWrite = chance(0.2);

        try {
            Logger.logAccess();
            MMU.access(entry, isWrite);
        } catch (PageFaultException ex) {
            Logger.logPageFault();
            kernel.replacePage(entry, isWrite);
        }
    }

    private boolean chance(double probability) {
        return ThreadLocalRandom.current().nextDouble() < probability;
    }

    private void checkForRefreshWorkingSet() {
        if (counter >= DataUtil.WORKING_SET_SIZE) {
            counter = 0;
            workingSet = generateWorkingRange();
        }
        counter++;
    }

    private PageTable generateWorkingRange() {
        if (pageTable.size() > DataUtil.WORKING_SET_SIZE) {
            PageTable workingSet = new PageTable(DataUtil.WORKING_SET_SIZE);
            for (int i = 0; i < DataUtil.WORKING_SET_SIZE; i++) {
                workingSet.addEntry(
                        pageTable.getEntry(random.nextInt(pageTable.size())),
                        i
                );
            }
            return workingSet;

        } else {
            return pageTable;
        }
    }

}
