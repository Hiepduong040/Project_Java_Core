package ra.edu.business.model;

public class Pagination {
    private int pagesize;
    private int currentpage;
    private int totalpages;

    public Pagination() {
        this.pagesize = 5;
        this.currentpage = 1;
        this.totalpages = 1;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalRecords) {
        this.totalpages = (int) Math.ceil((double) totalRecords / pagesize);
    }
}