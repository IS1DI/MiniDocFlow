package MiniDFlow.POJO;

import java.util.List;

public class Page<T> {
    private List<T> content;

    private int maxResults;



    private int lastPage;
    private int curPage;
    private int limitPage;

    public Page() {
    }

    public Page(List<T> content, int maxResults, int lastPage) {
        this.content = content;
        this.maxResults = maxResults;
        this.lastPage = lastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getLimitPage() {
        return limitPage;
    }

    public void setLimitPage(int limitPage) {
        this.limitPage = limitPage;
    }

}
