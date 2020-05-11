package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * 描述:
 * 响应的filter
 *
 * @author sculi
 * @create 2020-05-11 17:32
 */
@Slf4j
public class ResponseFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //请求开始时间
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        long statrtTime = (long) context.get("startTime");
        //请求耗时
        long duration = System.currentTimeMillis() - statrtTime;
        log.info("duration: {} ms",duration);
        //获取返回信息
        InputStream stream = RequestContext.getCurrentContext().getResponseDataStream();
        try {
            String body = IOUtils.toString(stream);
            log.info("response:"+body);
            RequestContext.getCurrentContext().setResponseBody(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
