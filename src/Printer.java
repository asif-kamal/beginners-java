public class Printer {
    // write code here

    private int tonerLevel;
    private int pagesPrinted;
    private final boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        if (tonerLevel < -1 || tonerLevel >= 100) {
            this.tonerLevel = -1;
        }
        this.tonerLevel = tonerLevel;
        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    public int addToner(int tonerAmount) {
        if (tonerAmount > 0 || tonerAmount <= 100) {
            if (tonerAmount + this.tonerLevel > 100) {
                return -1;
            } else {
                this.tonerLevel += tonerAmount;
                return this.tonerLevel;
            }
        } else {
            return -1;
        }
    }

    public int printPages(int pages) {
        int pagesToPrint = pages;

        if (duplex) {
            this.pagesPrinted += pagesToPrint / 2 + pagesToPrint % 2;
        }
        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return this.pagesPrinted;
    }
}