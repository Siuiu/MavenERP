package com.erp.utils;
import java.util.List;

/**
 * ��ҳ��
 */
public class Page {

    //ÿҳ��ʾ������
    private int pageSize = 5;

    //������
    private int rowCount = 0;

    //��ҳ��
    private int pageCount = 1;

    //��ǰҳ
    private int pageNo = 1;

    //������ʼλ��
    private int startIndex = 1;

    //��������λ��
    private int endIndex = 1;

    //��һҳ
    private int firstPageNo = 1;

    //��һҳ
    private int perPageNo = 1;

    //��һҳ
    private int nextPageNo = 1;

    //���һҳ
    private int lastPageNo = 1;

    //�����  
    private List<?> list;

    public Page(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        startIndex = pageSize * (pageNo - 1);
        endIndex = pageSize ;
    }

    public void setResult(List<?> list, int rowCount) {
        this.list = list;
        this.rowCount = rowCount;
        if(rowCount % pageSize == 0) {
            pageCount = (rowCount / pageSize);
        }else {
            pageCount = (rowCount / pageSize + 1);
        }
        this.lastPageNo = pageCount;
        if(pageNo > 1) {
            perPageNo = (pageNo - 1);
        }
        if(pageNo == lastPageNo) {
            nextPageNo = lastPageNo;
        }else {
            nextPageNo = (pageNo + 1);
        }

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getFirstPageNo() {
        return firstPageNo;
    }

    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    public int getPerPageNo() {
        return perPageNo;
    }

    public void setPerPageNo(int perPageNo) {
        this.perPageNo = perPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getLastPageNo() {
        return lastPageNo;
    }

    public void setLastPageNo(int lastPageNo) {
        this.lastPageNo = lastPageNo;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}


