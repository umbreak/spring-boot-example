package model;

public class Pagination {
    public static class Request{
        public Request() {
        }
        public Request(int currentPage, int pageSize) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
        }

        private int currentPage;
        private int pageSize;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class Response{
        private int currentPage;
        private int pageSize;
        private int pages;
        private long totalElements;

        public Response() {
        }
        public Response(int currentPage, int pageSize, int pages, long totalElements) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.pages = pages;
            this.totalElements = totalElements;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }
    }



}
