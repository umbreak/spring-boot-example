package hello.utils;

import model.MarketSurveysRequest;
import model.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtils {
    //extremely small for testing purposes
    public final static int DEFAULT_PAGE_SIZE = 2;
    private final static int MAX_PAGE_SIZE = 25;


    public static Pageable createPageRequest(MarketSurveysRequest request){
        int offset = fetchOffsetOrSetDefault(request.getPage());
        int pageSize = fetchPageSizeOrSetDefault(request.getPage());
        return createPageRequest(offset,pageSize);
    }
    private static Pageable createPageRequest(int page, int pageSize) {
        return new PageRequest(page, pageSize);
    }

    private static int fetchOffsetOrSetDefault(Pagination.Request page){
        return page == null ? 0 : Math.max(0,page.getCurrentPage()-1);
    }

    private static int fetchPageSizeOrSetDefault(Pagination.Request page){
        return page == null ? DEFAULT_PAGE_SIZE : Math.min(page.getPageSize(),MAX_PAGE_SIZE);
    }

}
